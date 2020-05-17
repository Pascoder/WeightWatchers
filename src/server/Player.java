package server;

import java.util.ArrayList;

//Klasse von Frank Mauchle
public class Player {

private int player_id;
private String password; //kommt nachher eventuell in DB
private ArrayList <Card> hand = new ArrayList <>();
private ArrayList <String> handAsStrings = new ArrayList <>();
private ArrayList <Card> stichCards = new ArrayList <>();
private String name;
private boolean onMove;
private Weis weis;
private int actualGame;


//Player wird durch Login erstellt und enth�lt die Client_id
public Player(int player_id, String name, String password) {

	
	this.player_id = player_id;
	this.name = name;
	this.password = password;
	this.actualGame = -1;
	
}

public Player(int player_id, String name, int actualGame, boolean onMove, Weis weis, ArrayList<String>cards ) {
	this.player_id = player_id;
	this.name = name;
	this.actualGame = actualGame;
	this.onMove = onMove;
	this.weis = weis;
	this.handAsStrings = cards;
}

public int getPlayer_id() {
	return this.player_id;
}

public String getPassword() {
	return this.password;
}

public ArrayList <Card> getHand() {
	return this.hand;
}

public String getName() {
	return this.name;
}

public boolean getonMove() {
	return this.onMove;
}

public void setHand(ArrayList <Card> hand) {
	this.hand = hand;
}
public void removeCardFromHand(Card card) {
    for(int i = 0; i < hand.size(); i++) {
	if(hand.get(i).compareTo(card)==0) {
	    hand.remove(i);
	}
    }
}

public void setonMove(boolean onMove) {
	this.onMove = onMove;
	if(this.onMove == false) {
	    setCardsToUnPlay();
	}
}
public void setWeis(Weis weis) {
	this.weis = weis;
}
public Weis getWeis() {
	return this.weis;
}

private void setCardsToUnPlay() {
    for(int i = 0; i > hand.size(); i++) {
	hand.get(i).setPlayable(false);
    }
}

public void addStichCards(ArrayList<Card> cardsOnTable) {
    this.stichCards.addAll(cardsOnTable);
}

public ArrayList<Card> getStichCards(){
    return this.stichCards;
}
public void clearStichCards() {
    this.stichCards.clear();
}

//Aktuelles Spiel in dem sich der Spieler befindet
public void setActualGame(int gameID) {
    	if (this.actualGame == -1) {
    	    this.actualGame = gameID;
    	}
}

public int getActualGame() {
	return this.actualGame;
}

public ArrayList<String> getHandAsStrings() {
	
	return handAsStrings;
}

public String toString() {
	String cards = "";
	for(int i = 0; i< hand.size();i++) {
		cards+=hand.get(i)+"|";
	}
	// player_ID|name|actualGame|onMove|weis|cards|	

	return this.player_id+"|"+this.name+"|"+this.actualGame+"|"+this.onMove+"|"+this.weis+"|"+cards;




}


}
