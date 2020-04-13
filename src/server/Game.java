package server;

import java.util.ArrayList;

public class Game { 
    
    private ArrayList<Team> teams;
   
    private CardDeck cardDeck;
    private ArrayList<Card> cardsOnTable;
    private ArrayList<Player> players;
    private CardColor trumpf;
    private int countTeam;
    private int countPlayer;
    private int round;
    private int move;
    private int gameID;
    private String name;
    
    //Wird aufgerufen, wenn ein User in der Lobby ein neues Spiel erzeugt.
    Game(int gameID, String name){
	this.teams = new ArrayList<>();
	this.countTeam = 0;
	this.gameID = gameID;
	this.name = name;
	this.move = 0;
    }
    
    // In der Lobby wählt ein Spieler ein Game aus und wird danach automatisch einem Team hinzugefügt. 
    //1. und 2. Spieler dem Team 1 und 3. und 4 Spieler Team2.  
    private void generateTeams() {
	
    }
    
    public void addPlayer(Player player) {
    	boolean ok = false;
    	if(players.size()<4) {
    		this.players.add(player);
    	}
    }
    
    
    
    
    
    //Sobald 2 Teams/4Spieler im Spiel vorhanden sind, muss Spiel automatisch gestartet werden.
    private void startGame() {
	
	generateTeams();
	//show Table
	
    }
    
   
    
    //Kartenverteilen: Nach dem Mischen der Karten müssen die auf die Spieler verteilt werden.
    private void spreadCards() {
	
    }
    
    //Muss definieren, welcher Spieler an der Reihe ist.
    private void setPlayerOnMove() {
	
	
	Player.setonMove = true;
    }
    
    //Erster Spieler der am Zug ist muss Trumpf definieren.
    private void setTrumpf() {
	
    }
    
    //Spieler spielt eine Karte
    public void playCard(Card) {
	
	countMove();
	setPlayerOnMove();
	
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
   
    	
  
    public String toString() {
    	return this.gameID+"";
    }
    
    //Getter
    public int getGameID() {
    	return this.gameID;
    }
}
