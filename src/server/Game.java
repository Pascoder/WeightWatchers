package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.*;

import messages.Message_GAMEUPDATE;

public class Game {
    private ServiceLocator sl;
    private Logger l;

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
    private TrumpfType trumpftype;
    private GameType gametype;
    private boolean gameFinish, stichFinish;
	private boolean stapelFinish;

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
	this.trumpftype = TrumpfType.TRUMPF;
	this.gametype = GameType.Schieber;
	this.gameFinish = false;
	this.stichFinish = false;
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
	nextRound();
	}
	//NextRound
	for(int i = 0; i< playersOnGame.size();i++) {
		ServerModel.sayGameStarted(name, playersOnGame.get(i).getName());
		
	}

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
	if (this.move == 0 && !(this.gameFinish)) {
	    this.cardsOnTable.clear();
	    
	}
	if (this.round == 0 && !(this.gameFinish)) {
	    this.team1.nextStich();
	    this.team2.nextStich();
	    spreadCards();
	    this.stapelFinish = false;

	}
	setPlayerOnMove();
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
    
    public static Card singlecardStringtoCard(String s) {
    	Card card = new Card(CardColor.valueOf(s.substring(0, 1)), CardRank.valueOf(s.substring(1)));
    	
    	return card;
    }

    // String Aufschlüsselung in Karte
    public static Card stringToCard(String crd) {
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
	Card card2 = singlecardStringtoCard(card);
	playCard(Game_ID, Player_ID, card2);
    }

    // Spieler spielt eine Karte
    public void playCard(int game_ID, int player_ID, Card card) {
    	System.out.println("PLAY CARD: "+game_ID+player_ID+card.toString());
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
    	System.out.println("NORMAL MOVE: "+game_ID+player_ID+card.toString());
	card.setPlayable(true);
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
		this.stichFinish = false;
	    this.move++;
	} else {
		this.stichFinish = true;
	    this.move = 0;
	    setPlayersOffMove();
	    evaluateStichWinner();
	    this.stichColor = null;
	    countRound();
//	    //Nachricht an Clients---> darf nicht hier sein, sondern Teil vom GameUpdate
//	    for(Player p : playersOnGame) {
//	    	ServerModel.informClients(p.getName(), name);
//	    }	   
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
	    gamefinish();
	    this.trumpf = null;
	}

    }

    private void gamefinish() {
	if(team1.getTeamPoints() > 1000) {
	    this.gameFinish = true;
	}
	
    }

    private void evaluateStichWinner() {
	int[] winnerScore = JassModel.evaluateStichWinner(this.cardsOnTable, this.gametype, this.trumpftype, this.round, this.trumpf);
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
	
	System.out.println("Stichgewinner: "+ winnerScore[0] + " Punkte: "+ winnerScore[1]);

    }

    private void evaluateStapleWinner() {
    	this.stapelFinish = true;
	if (team1.getTeamPoints() > team2.getTeamPoints()) {
	    this.lastWinnerTeam_ID = team1.getTeam_id();
	}else {
	
	    this.lastWinnerTeam_ID = team2.getTeam_id();
	}

	System.out.println("Stapelgewinner: Team " + this.lastWinnerTeam_ID);

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

    public ArrayList<Card> getCardsOnTable() {
	return this.cardsOnTable;
    }
    
    public String getCardsOnTableAsString() {
    	String result = "";
    	
    	if(cardsOnTable.isEmpty())return result;
    	
    	for (Card c : cardsOnTable) {
    		result += c.toString() + "$";
    	}
    	return result;
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

	public CardColor getTrumpf() {
		return trumpf;
	}

	public void setTrumpf(CardColor trumpf) {
		this.trumpf = trumpf;
	}

	public boolean isStichFinish() {
		return stichFinish;
	}

	public void setStichFinish(boolean stichFinish) {
		this.stichFinish = stichFinish;
	}

	public boolean isGameFinish() {
		return gameFinish;
	}

	public void setGameFinish(boolean gameFinish) {
		this.gameFinish = gameFinish;
	}

	public int getLastWinnerTeam_ID() {
		return lastWinnerTeam_ID;
	}

	public void setLastWinnerTeam_ID(int lastWinnerTeam_ID) {
		this.lastWinnerTeam_ID = lastWinnerTeam_ID;
	}

	public boolean isStapelFinish() {
		return stapelFinish;
	}

	public void setStapelFinish(boolean stapelFinish) {
		this.stapelFinish = stapelFinish;
	}

}
