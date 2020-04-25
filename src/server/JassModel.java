package server;

import java.util.ArrayList;

public class JassModel {

    public static ArrayList<Card> evalHand = new ArrayList<Card>();

    public JassModel() {

    }

    public static ArrayList<Card> checkPlayableCards(ArrayList<Card> hand, CardColor trumpf, CardColor stichColor) {
	boolean stichCard = false;
	boolean trumpfCard = false;

	for (Card c : hand) {
	    c.setPlayable(false);
	}
	for (Card c : hand) {
	    if (c.getCardColor() == stichColor) {
		c.setPlayable(true);
		stichCard = true;
	    }
	}

	for (Card c : hand) {
	    if (c.getCardColor() == trumpf) {
		c.setPlayable(true);
	    }
	}

	for (Card c : hand) {
	    if (!stichCard && !trumpfCard) {
		c.setPlayable(true);
	    }
	}

	return hand;
    }

    public static int[] evaluateStichWinner(ArrayList<Card> cardsOnTable, GameType gameType) {
	int player_ID = 9;
	int points = 0;
	int[] winnerScore = new int[2];

	winnerScore[0] = player_ID;
	winnerScore[1] = points;
	return winnerScore;
    }
    
   
}
