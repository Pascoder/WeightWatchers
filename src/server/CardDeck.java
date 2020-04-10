package server;

import java.util.ArrayList;
import java.util.Collections;

import javafx.beans.property.SimpleIntegerProperty;

public class CardDeck {

private final ArrayList <Card> cards = new ArrayList<>();
private static CardRank[] ranks = CardRank.values();
private static CardColor[] colors = CardColor.values();


public CardDeck() {
	shuffle();
}


private void shuffle() {
	cards.clear();
	
	for(int a = 0; a < colors.length;a++){
		for(int b = 0; b < ranks.length;b++) {
			Card card = new Card(colors[a],ranks[b]);
			cards.add(card);
		}
	}
	Collections.shuffle(cards);
}
//Kartendeck wird zur�ckgegeben mit Franz�sichen und Schweizer Karten
public ArrayList <Card> getDeck(){
	return this.cards;
}
public void setDeckLanguage(String language) {

	for(int i = 0; i< cards.size();i++) {
		cards.get(i).getCardColor().setLanguage(language);
	}
	
		
}
public String toString() {
	String tostring = "";
	for(int i = 0; i<cards.size(); i++) {
		tostring+="\n"+this.cards.get(i)+"";
	}
	return tostring;
}
}
