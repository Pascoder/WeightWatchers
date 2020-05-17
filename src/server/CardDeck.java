package server;

import java.util.ArrayList;
import java.util.Collections;

import javafx.beans.property.SimpleIntegerProperty;

//Klasse von Frank Mauchle

public class CardDeck {
private final ArrayList <Card> cards = new ArrayList<>();


public CardDeck() {
	shuffle();	
}

private void shuffle() {
	cards.clear(); 
	for(CardColor color: CardColor.values()){
		for(CardRank rank: CardRank.values()) {
			Card card = new Card(color,rank);
			cards.add(card);
		}
	}
	Collections.shuffle(cards);
}

//Kartendeck wird zurueckgegeben
public ArrayList <Card> getDeck(){
	return this.cards;
}

public String toString() {
	String tostring = "";
	for(int i = 0; i<cards.size(); i++) {
		tostring+="\n"+this.cards.get(i)+"";
	}
	return tostring;
}
}
