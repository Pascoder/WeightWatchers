package server;

import java.util.ArrayList;
import java.util.Collections;

//Klasse von Frank Mauchle

public class CardDeck {
	private final ArrayList<Card> cards = new ArrayList<>();

//Beim aufruff des Konstruktors werden Karten durch Methode suffle() zuerst erstellt und dann sofort gemischelt
	public CardDeck() {
		shuffle();
	}

	private void shuffle() {
		cards.clear();
		for (CardColor color : CardColor.values()) {
			for (CardRank rank : CardRank.values()) {
				Card card = new Card(color, rank);
				cards.add(card);
			}
		}
		Collections.shuffle(cards);
	}

//Kartendeck wird zurueckgegeben
	public ArrayList<Card> getDeck() {
		return this.cards;
	}

	public String toString() {
		String tostring = "";
		for (int i = 0; i < cards.size(); i++) {
			tostring += "\n" + this.cards.get(i) + "";
		}
		return tostring;
	}
}
