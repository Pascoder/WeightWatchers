package server;

import java.util.ArrayList;

public class Game { 
    
    private ArrayList<Team> teams;
   
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
   
    	
    
    public String toString() {
    	return this.gameID+"";
    }
    public int getGameID() {
    	return this.gameID;
    }
}
