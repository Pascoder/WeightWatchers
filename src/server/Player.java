package server;

import java.util.ArrayList;

public class Player {

private int player_id;
private String password; //kommt nachher eventuell in DB
private ArrayList <Card> hand = new ArrayList <>();
private String name;
private boolean onMove;
private Weis weis;
private int actualGame;




//Player wird durch Login erstellt und enthält die Client_id
public Player(int player_id, String name, String password) {

	
	this.player_id = player_id;
	this.name = name;
	this.password = password;
	
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
    for(int i = 0; i > hand.size(); i++) {
	if(hand.get(i) == card) {
	    hand.remove(i);
	}
    }
}

public void setonMove(boolean onMove) {
	this.onMove = onMove;
}
public void setWeis(Weis weis) {
	this.weis = weis;
}
public Weis getWeis() {
	return this.weis;
}

//Aktuelles Spiel in dem sich der Spieler befindet
public void setActualGame(int gameID) {
	this.actualGame = gameID;
}

public int getActualGame() {
	return this.actualGame;
}

public String toString() {
	String cards = "";
	for(int i = 0; i< hand.size();i++) {
		cards+="\n"+hand.get(i);
	}
	return "Player_id: "+this.player_id+"\n"+"Name: "+this.name+"\n"+"onMove: "+this.onMove+"\n"+"Cards: "+cards+"\n"+"Weis: "+this.weis+"\n";
}



}
