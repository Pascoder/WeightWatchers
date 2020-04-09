package server;

public class Card implements Comparable<Card> {
   
    private final CardColor cardColor;
    private final CardRank cardRank;
    
    public Card(CardColor cardColor, CardRank cardRank) {
        this.cardColor = cardColor;
        this.cardRank = cardRank;
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
    
    @Override
    public String toString() {
        return cardRank.ordinal()+"/" + cardRank.toString() + cardColor.toString();
    }
    
    @Override
	public int compareTo(Card otherCard) {
		if ( this.cardRank.ordinal() > otherCard.cardRank.ordinal())
			return 1;
		else
			if (this.cardRank.ordinal() < otherCard.cardRank.ordinal())
				return -1;
			else
				return 0;
	}
}
