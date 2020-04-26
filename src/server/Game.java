package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.*;

public class Game {
    private ServiceLocator sl;
    private Logger l;
    // private CardDeck cardDeck;
    private ArrayList<Card> cardsOnTable;
    private ArrayList<Player> playersOnGame;
    private Team team1;
    private Team team2;
    private int[] moveOrder;
    private CardColor trumpf;
    private CardColor stichColor;
    private int round;
    private int move;
    private int gameID;
    private int lastWinner_ID;
    private int lastWinnerTeam_ID;
    private int lastWinner_points;
    private String name;
    private GameType gametype;

    // Wird aufgerufen, wenn ein User in der Lobby ein neues Spiel erzeugt.
    Game(int gameID, String name) {
	this.sl = ServiceLocator.getServiceLocator();
	this.l = sl.getLogger();
	this.playersOnGame = new ArrayList<>();
	this.cardsOnTable = new ArrayList<>();
	this.moveOrder = new int[4];
	this.gameID = gameID;
	this.name = name;
	this.round = 0;
	this.move = 0;
	this.lastWinner_ID = 0;
	this.trumpf = null;
	this.gametype = GameType.Schieber;
	// sl.getLogger().info("neues Game erzeugt|Game_ID: "+this.gameID+"|Name:
	// "+this.name);
    }

    // Wählt ein Spieler in der Lobby ein Spiel aus, wird er dem Game hinzugefügt.

    public void addPlayer(Player player) {
	if (playersOnGame.size() < 4) {
	    this.playersOnGame.add(player);
	    player.setActualGame(this.gameID);
	    if (playersOnGame.size() == 4) { // bei 4 Spielern wird das Game gestartet
		startGame();
		// sl.getLogger().info(
		// "4 Spieler vorhanden, game wird gestartet|Game_ID: " + this.gameID + "|Name:
		// " + this.name);
	    }
	}
    }

    // Hinzufügen 1. und 2. Spieler dem Team 1 und 3. und 4. Spieler Team2.
    private void generateTeams() {
	this.team1 = new Team(this.gameID, 1);
	this.team1.addTeamMember(searchPlayer(moveOrder[0]));
	this.team1.addTeamMember(searchPlayer(moveOrder[2]));
	this.team2 = new Team(this.gameID, 2);
	this.team2.addTeamMember(searchPlayer(moveOrder[1]));
	this.team2.addTeamMember(searchPlayer(moveOrder[3]));
    }

    // Sucht den Spieler nach ID
    public Player searchPlayer(int person_id) {
	Player player = null;
	for (Player p : playersOnGame) {
	    if (p.getPlayer_id() == person_id)
		player = p;
	}
	return (player);
    }

    // Erstellt die Reihenfolge der Spieler/Tischordnung mittels PlayerID
    // Ergänzung mittels Shuffle als Option
    private void generateMoveOrder() {
	for (int i = 0; i < 4; i++) {
	    moveOrder[i] = this.playersOnGame.get(i).getPlayer_id();
	}
    }

    // Verschiebt die Spieler zum 1. Spieler nach dem letzten Gewinner
    private void shiftMoveOrder() {
	int i1 = -1;
	int i2 = 0;
	for (int i : this.moveOrder) {
	    i1++;
	    if (i == this.lastWinner_ID) {
		i2 = i1;
	    }
	}
	int[] oldorder = Arrays.copyOf(this.moveOrder, 8);
	{
	    for (int i = 0; i < 4; i++) {
		oldorder[i + 4] = this.moveOrder[i];
	    }
	}
	this.moveOrder = Arrays.copyOfRange(oldorder, i2, i2 + 4);
    }

    // Sobald 2 Teams/4Spieler im Spiel vorhanden sind, wird das Spiel vorbereitet
    // und gestartet.
    private void startGame() {
	generateMoveOrder();
	generateTeams();
	setPlayersOffMove();
	if(round == 0) {
	spreadCards();
	}
	//NextRound
	for(int i = 0; i< playersOnGame.size();i++) {
		ServerModel.sayGameStarted(name, playersOnGame.get(i).getName());
		
	}
	ServerModel.updateClients(2, playersOnGame.get(1).getName());
		//Muss nur 1 mal gemacht werden, es werden sowieso alle Clients updated

    }

    // Kartenverteilen: Nach dem Mischen der Karten müssen diese auf die Spieler
    // verteilt werden.
    void spreadCards() {
	for(Player p : this.playersOnGame) {
	    p.clearStichCards();
	}
	CardDeck deck = new CardDeck();
	playersOnGame.get(0).setHand(new ArrayList<>(deck.getDeck().subList(0,9)));
	playersOnGame.get(1).setHand(new ArrayList<>(deck.getDeck().subList(9,18)));
	playersOnGame.get(2).setHand(new ArrayList<>(deck.getDeck().subList(18,27)));
	playersOnGame.get(3).setHand(new ArrayList<>(deck.getDeck().subList(27,36)));
    }

    // Starten nächste Runde sobald ein Spieler auf start next Round klickt
    public void nextRound() {
	if (this.move == 0) {
	    this.cardsOnTable.clear();
	    setPlayerOnMove();
	}
	if (this.round == 0) {
	    this.team1.nextStich();
	    this.team2.nextStich();
	    spreadCards();

	}
    }

    // Setzt den Spieler der an der Reihe ist auf true.
    private void setPlayerOnMove() {
	for (int i = 0; i < 4; i++) {
	    searchPlayer(moveOrder[i]).setonMove(false);
	}
	searchPlayer(moveOrder[this.move]).setonMove(true);
	setPlayableCards();
    }

    // Setzt alle Spieler auf inaktiv
    private void setPlayersOffMove() {
	for (Player p : this.playersOnGame) {
	    p.setonMove(false);
	}
    }

    // Erster Spieler der am Zug ist muss Trumpf definieren.
    private void setTrumpf(Card card) {
	this.trumpf = card.getCardColor();

    }

    // String Aufschlüsselung in Karte
    public Card stringToCard(String crd) {
	String[] strCrd = crd.split("\\|");
	Card card = new Card(CardColor.valueOf(strCrd[0].substring(0, 1)), CardRank.valueOf(strCrd[0].substring(1)));
	card.setPlayable(Boolean.parseBoolean(strCrd[1]));
	return card;
    }

    // Wandelt kompletten String in int und Card um
    public void playedCardfromClient(String Game_ID, String Player_ID, String card) {
	int game_ID = Integer.parseInt(Game_ID);
	int player_ID = Integer.parseInt(Player_ID);
	Card card2 = stringToCard(card);
	playCard(game_ID, player_ID, card2);
    }

    // Wandelt nur KartenString um
    public void playedCardfromClient_2(int Game_ID, int Player_ID, String card) {
	Card card2 = stringToCard(card);
	playCard(Game_ID, Player_ID, card2);
    }

    // Spieler spielt eine Karte
    public void playCard(int game_ID, int player_ID, Card card) {
	if (game_ID == this.gameID && searchPlayer(player_ID).getonMove()) {
	    if (this.trumpf != null) {
		normalMove(game_ID, player_ID, card);
	    } else {
		setTrumpf(card);
	    }
	}
	setPlayerOnMove();
    }

    private void normalMove(int game_ID, int player_ID, Card card) {
	searchPlayer(player_ID).removeCardFromHand(card);
	card.setPlayer_ID(player_ID);
	this.cardsOnTable.add(card);
	if (this.stichColor == null) {
	    this.stichColor = card.getCardColor();
	}
	countMove();

    }

    // Zählt die Züge einer Runde
    private void countMove() {
	if (this.move < 3) {
	    this.move++;
	} else {
	    this.move = 0;
	    setPlayersOffMove();
	    evaluateStichWinner();
	    this.stichColor = null;
	    countRound();
	}
	System.out.println(move);
    }

    // Zählt die Runden
    private void countRound() {
	if (this.round < 8) {
	    this.round++;
	} else {
	    this.round = 0;
	    evaluateStapleWinner();
	    this.trumpf = null;
	}

    }

    private void evaluateStichWinner() {
	int[] winnerScore = JassModel.evaluateStichWinner(this.cardsOnTable, this.gametype, this.round);
	for (Player p : this.team1.getTeamMembers())
	    if (p.getPlayer_id() == winnerScore[0]) {
		this.team1.setTeamPoints(winnerScore[1]);
		this.team1.setTeamWins(+1);
	    }
	this.team2.setTeamPoints(winnerScore[0]);
	this.team2.setTeamWins(+1);
	this.lastWinner_ID = winnerScore[0];
	searchPlayer(this.lastWinner_ID).addStichCards(cardsOnTable);
	shiftMoveOrder();

    }

    private void evaluateStapleWinner() {
	// TODO Auto-generated method stub
	if (team1.getTeamPoints() > team2.getTeamPoints()) {
	    this.lastWinnerTeam_ID = team1.getTeam_id();
	}else {
	
	    this.lastWinnerTeam_ID = team2.getTeam_id();
	}

	System.out.println("Rundengewinner: Team " + this.lastWinnerTeam_ID);

    }

    // Spielbare Karten für Spieler definieren
    public void setPlayableCards() {
	for (Player p : this.playersOnGame) {
	    if (p.getonMove() && this.move == 0) {
		allCardsPlayable();
	    }
	    p.setHand(JassModel.checkPlayableCards(p.getHand(), trumpf, stichColor));
	}
	cardsNotOnMovePlayers();
    }

    private void allCardsPlayable() {
	for (Player p : this.playersOnGame) {
	    if (p.getonMove())
		for (Card c : p.getHand()) {
		    c.setPlayable(true);
		}
	}
    }

    private void cardsNotOnMovePlayers() {
	for (Player p : this.playersOnGame) {
	    if (!(p.getonMove()))
		for (Card c : p.getHand()) {
		    c.setPlayable(false);
		}
	}
    }

    // Methode benötigt für Message_GAMEUPDATE
    public String GameAsString() {
	String gameString = "";

	for (Player p : playersOnGame) {
	    gameString += p.toString() + "$";
	}

	return gameString;
    }

    public ArrayList getCardsOnTable() {
	return this.cardsOnTable;
    }

    public Card getLastCard() {
	return cardsOnTable.get(cardsOnTable.size() - 1);
    }

    public CardColor getLastColor() {
	return getLastCard().getCardColor();
    }

    public CardRank getLastRank() {
	return getLastCard().getCardRank();
    }

    public String toString() {
	return this.gameID + "";
    }

    public int getGameID() {
	return this.gameID;
    }

    public String getName() {
	return name;
    }

    public ArrayList<Player> getPlayersOnGame() {
	return playersOnGame;
    }

    public String getLastWinner_ID() {
	return this.lastWinner_ID + "|";
    }

    public String getLastWinnerPoints() {
	return this.lastWinner_points + "|";
    }

}
