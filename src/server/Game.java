package server;

import java.util.ArrayList;

public class Game {
    
    private ArrayList<Team> teams;
    private ArrayList<Player> joinedGame = new ArrayList<>();
    private CardDeck cardDeck;
    private ArrayList<Card> cardsOnTable;
    private CardColor trumpf;
    private int round;
    private int move;
    private int gameID;
    private String name;
    
    
    Game(int gameID, String name){
	this.gameID = gameID;
	this.name = name;
    }
    
    private void startGame() {
	
    }
    
    private void generateTeams() {
	
    }

    private void spreadCards() {
	
    }
    
    private void setPlayerOnMove() {
	
    }
    
    private void setTrumpf() {
	
    }
    //Wird ben�tigt, wenn Spieler in der Lobby sich f�r ein Game entscheiden, jedoch noch kein Team besitzen
    public void joinGame(int gameID, Player p1) {
    	if(this.gameID == gameID) {
    		this.joinedGame.add(p1); 
    	}
    	
    }
    public String toString() {
    	return this.gameID+"";
    }
    public int getGameID() {
    	return this.gameID;
    }
}
