package server;

public enum CardRank {
Six,
Seven,
Eight,
Nine,
Ten,
Jack,
Queen,
King,
Ace;


public String toString() {
		/*Alle Karten mit einem Zahlenwert werden mit Integer.toString in einen String wert umgewandelt
		 * Six w�re also ordinal 0, also +6 = String -->6
		 */
	    String cardRank = "Ace";
	    int ordinal = this.ordinal();
	    
	    if(ordinal <= 4) {
	    	cardRank = Integer.toString(ordinal+6);
	    }else if(ordinal == 5){
	    	cardRank = "Jack";	
	    }else if(ordinal == 6){
	    	cardRank = "Queen";
	    }else if(ordinal == 7) {
	    	cardRank = "King";
	    }
	    return cardRank;
	}

}
