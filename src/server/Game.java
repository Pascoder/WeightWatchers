package server;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Game { 
    private ServiceLocator sl;
    private Logger l;
    private CardDeck cardDeck;
    private ArrayList<Card> cardsOnTable;
    private ArrayList<Player> playersOnGame;
    private Team team1;
    private Team team2;
    private int[] moveOrder;
    private CardColor trumpf;
    private int round;
    private int move;
    private int gameID;
    private String name;
    
    //Wird aufgerufen, wenn ein User in der Lobby ein neues Spiel erzeugt.
    Game(int gameID, String name){
	this.sl = ServiceLocator.getServiceLocator();
	this.l = sl.getLogger();
	this.playersOnGame= new ArrayList<>();
	this.cardsOnTable = new ArrayList<>();
	this.moveOrder = new int[4];
	this.gameID = gameID;
	this.name = name;
	this.round = 0;
	this.move = 0;
	sl.getLogger().info("neues Game erzeugt|Game_ID: "+this.gameID+"|Name: "+this.name);	
    }
    
    
    //Wählt ein Spieler in der Lobby ein Spiel aus, wird er dem Game hinzugefügt.
    public void addPlayer(Player player) {
    	if(playersOnGame.size()<4) {
    		this.playersOnGame.add(player);
    		if(playersOnGame.size()==3) { //bei 4 Spielern wird das Game gestartet
    		    startGame();
    		    sl.getLogger().info("4 Spieler vorhanden, game wird gestartet|Game_ID: "+this.gameID +"|Name: "+this.name);
    		}
    	}
    }
    
    //Hinzufügen 1. und 2. Spieler dem Team 1 und 3. und 4. Spieler Team2.  
    private void generateTeams() {
	this.team1 = new Team(this.gameID, 1);
	this.team1.addTeamMember(searchPlayer(moveOrder[0]));
	this.team1.addTeamMember(searchPlayer(moveOrder[2]));
	this.team2 = new Team(this.gameID, 2);
	this.team2.addTeamMember(searchPlayer(moveOrder[1]));
	this.team2.addTeamMember(searchPlayer(moveOrder[3]));	
    }
    
    //Sucht den Spieler nach ID
    public Player searchPlayer(int person_id) {
	Player player = null;
	for(Player p :playersOnGame) {
		if(p.getPlayer_id() == person_id) 
			player = p;
		}
	return (player);
	}
    //Erstellt die Reihenfolge der Spieler/Tischordnung mittels PlayerID
    //Ergänzung mittels Shuffle als Option
    private void generateMoveOrder() {
	for(int i = 0; i<4; i++) {
	    moveOrder[i] = this.playersOnGame.get(i).getPlayer_id();
	}
    }
      
    //Sobald 2 Teams/4Spieler im Spiel vorhanden sind, wird das Spiel vorbereitet und gestartet.
    private void startGame() {	
	generateMoveOrder();
	generateTeams();
	updateClients();
	sl.getLogger().info("Teams gebildet, Spielerreihenfolge festgelegt|Game_ID: "+this.gameID +"|Name: "+this.name);
    }
     
    
    //Kartenverteilen: Nach dem Mischen der Karten müssen diese auf die Spieler verteilt werden.
    private void spreadCards() {
	//TODO
    }
    
    //Starten nächste Runde sobald ein Spieler auf start next Round klickt
    public void nextRound() {
	if(this.move == 0) {
	    this.cardsOnTable.clear();
	    setPlayerOnMove();
	}
	
    }
    
    //Setzt den Spieler der an der Reihe ist auf true.
    private void setPlayerOnMove() {
	searchPlayer(moveOrder[this.move]).setonMove(true);
    }
    
    //Erster Spieler der am Zug ist muss Trumpf definieren.
    private void setTrumpf() {
	
	updateClients();
    }
    
    //Spieler spielt eine Karte
    public void playCard(int Game_ID, int Player_ID, Card card) {
	if(Game_ID == this.gameID) {
	    if(this.move < 4) {
		searchPlayer(Player_ID).removeCardFromHand(card);
		this.cardsOnTable.add(card);
		countMove();
		setPlayerOnMove();
		updateClients();
	    }
	}
    }
    
    //Zählt die Züge einer Runde
    private void countMove() {
	if (this.move<4){
	    this.move++;
	}
	else {
	    this.move = 0;
	}
    }
   
    //Erzwingt Update der Spieleransichten/Table
    private void updateClients() {
	//TODO Table Objekt an alle Clients verteilen
    }
    	
  
    public String toString() {
    	return this.gameID+"";
    }
    
    //Getter
    public int getGameID() {
    	return this.gameID;
    }
}
