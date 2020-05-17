package server;


//Klasse von Frank Mauchle

	//Spalte 1 = NormalRank, Spalte 2 = Trumpf, Spalte 3 = NormalScore, Spalte 4 = TrumpfScore
public enum CardRank {
Six(1,1,0,0,11),
Seven(2,2,0,0,11),
Eight(3,3,0,0,8),
Nine(4,8,0,14,0),
Ten(5,4,10,10,10),
Jack(6,9,2,20,2),
Queen(7,5,3,3,3),
King(8,6,4,4,4),
Ace(9,7,11,11,0);

    private final int rank;
    private final int trumpfRank;
    private final int score;
    private final int trumpfScore;
    private final int bottomUpScore;    
    

    CardRank(int rank, int score) {
	    this(rank, rank, score, score, score);
	}

	CardRank(int rank, int trumpfRank, int score, int trumpfScore, int bottomUpScore) {
	    this.rank = rank;
	    this.trumpfRank = trumpfRank;
	    this.score = score;
	    this.trumpfScore = trumpfScore;
	    this.bottomUpScore = bottomUpScore;
	}
    
    
	 public int getScore() {
	        return score;
	    }

	    public int getRank() {
	        return rank;
	    }

	    public int getTrumpfRank() {
	        return trumpfRank;
	    }

	    public int getTrumpfScore() {
	        return trumpfScore;
	    }

	    public int getBottomUpScore() {
	        return bottomUpScore;
	    }

    
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
