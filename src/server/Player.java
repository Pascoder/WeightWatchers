package server;

import java.util.ArrayList;

public class Player {

private int player_id;
private ArrayList <Card> hand = new ArrayList <>();
private String name;
private boolean onMove;


public Player(int player_id, String name) {
	
	this.player_id = player_id;
	this.name = name;
	
}

public int getPlayer_id() {
	return this.player_id;
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

public void setonMove(boolean onMove) {
	this.onMove = onMove;
}

public String toString() {
	String cards = "";
	for(int i = 0; i< hand.size();i++) {
		cards+="\n"+hand.get(i);
	}
	return "Player_id: "+this.player_id+"\n"+"Name: "+this.name+"\n"+"onMove: "+this.onMove+"\n"+"Cards: "+cards;
}

}
