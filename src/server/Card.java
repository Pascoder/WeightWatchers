package server;

public class Card implements Comparable<Card> {
   
    private final  CardColor cardColor;
    private final  CardRank cardRank;
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
        return  cardColor.name()+ cardRank.name()+ "|"+cardPlayable;
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
