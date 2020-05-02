package server;

import java.util.Comparator;

public class Card implements Comparable<Card>{

    private final CardColor cardColor;
    private final CardRank cardRank;
    private boolean cardPlayable;
    private int player_ID;

    public Card(CardColor cardColor, CardRank cardRank) {
	this.cardColor = cardColor;
	this.cardRank = cardRank;
	this.cardPlayable = false;
	this.player_ID = 0;

    }
    
 

    public CardColor getCardColor() {
	return cardColor;
    }

    public CardRank getCardRank() {
	return cardRank;
    }

    public int getOrdinal() {
	return cardRank.ordinal();
    }

    public void setPlayable(boolean playable) {
	this.cardPlayable = playable;
    }

    public boolean getPlayable() {
	return this.cardPlayable;
    }

    public void setPlayer_ID(int p) {
	this.player_ID = p;
    }

    public int getPlayer_ID() {
	return this.player_ID;
    }

    @Override
    public String toString() {
	return cardColor.name() + cardRank.name() + "|" + cardPlayable;
    }
    
    public int compareTo(Card c) {
    	int cf = 1;
    	if(this.getCardRank() == c.getCardRank() && this.getCardColor() == c.getCardColor())
    		cf=0;
    	
    	
    	return cf;
    }

    public static Comparator<Card> normalRankComparator = new Comparator<Card>() {
	@Override
	public int compare(Card c1, Card c2) {
	    return (c2.getCardRank().getRank() < c1.getCardRank().getRank() ? -1
		    : (c2.getCardRank().getRank() == c1.getCardRank().getRank() ? 0 : 1));
	}
    };

    public static Comparator<Card> trumpfRankComparator = new Comparator<Card>() {
	@Override
	public int compare(Card c1, Card c2) {
	    return (c2.getCardRank().getTrumpfRank() < c1.getCardRank().getTrumpfRank() ? -1
		    : (c2.getCardRank().getTrumpfRank() == c1.getCardRank().getTrumpfRank() ? 0 : 1));
	}
    };

}
