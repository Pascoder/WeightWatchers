package server;

import java.util.ArrayList;
import java.util.Collections;

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

    public static int[] evaluateStichWinner(ArrayList<Card> cardsOnTable, GameType gameType, TrumpfType trumpfType,
	    int round, CardColor trumpf, CardColor stich) {
	int points = evaluateScore(cardsOnTable, gameType, trumpfType, trumpf);
	Card winnerCard = evluateWinnerCard(cardsOnTable, trumpfType, trumpf, stich);
	

	int[] winnerScore = new int[2];

	winnerScore[0] = winnerCard.getPlayer_ID();
	winnerScore[1] = points;
	return winnerScore;
    }

    private static Card evluateWinnerCard(ArrayList<Card> cardsOnTable, TrumpfType trumpfType, CardColor trumpf, CardColor stich) {	
    ArrayList<Card> trumpfCards = new ArrayList<>();
	ArrayList<Card> stichCards = new ArrayList<>();
	ArrayList<Card> otherCards = new ArrayList<>();
	Card winnerCard = null;	
	for(Card c : cardsOnTable) {
	    if(c.getCardColor() == trumpf) {
		trumpfCards.add(c);
	    }else {
	    if(c.getCardColor() == stich) {
	    stichCards.add(c);
	    }else {
	    otherCards.add(c);
	    }
	    }
	    }
	System.out.println("CARDS ON TABLE: "+cardsOnTable);
	System.out.println("TRUMPF CARDS: "+trumpfCards);
	System.out.println("STICH CARDS: "+stichCards);
	System.out.println("OTHER CARDS: "+otherCards);
	
	if(trumpfCards.size()>0) {
	    Collections.sort(trumpfCards, Card.trumpfRankComparator);
	    winnerCard = trumpfCards.get(0);
	}else {
	    
	    if(stichCards.size()>0) {
	    Collections.sort(stichCards, Card.normalRankComparator);
		winnerCard = stichCards.get(0);
	    }else {
	    Collections.sort(otherCards, Card.normalRankComparator);
		winnerCard = otherCards.get(0);
	    }
	}
	
	return winnerCard;
	
    }

    

    static int evaluateScore(ArrayList<Card> cardsOnTable, GameType gameType, TrumpfType trumpfType, CardColor trumpf) {
	int score = 0;
	if (gameType == GameType.Schieber)
	    for (Card c : cardsOnTable) {
		if (c.getCardColor() == trumpf) {
		    score += c.getCardRank().getTrumpfScore();
		}else {
			score += c.getCardRank().getScore();
		}
		
	    }
	System.out.println("TRUMPFSCORE: "+score);
	return score;
    }

    static int evaluateWinner(ArrayList<Card> cardsOnTable, GameType gameType) {
	int player_ID = -1;

	return player_ID;
    }

    
    
}
