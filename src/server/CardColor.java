package server;

public enum CardColor {
Rose ("CH"),
Eichel ("CH"),
Schilten ("CH"),
Schellen ("CH"),
Clubs("FR"),
Diamonds("FR"),
Hearts("FR"),
Spades("FR"); 

private String language;


private CardColor(String language) {
	this.language = language;
	
}
public String toString() {
    String cardColor = "";
    switch (this) {
    case Rose: cardColor = "rose"; break;
    case Eichel: cardColor = "eichel"; break;
    case Schilten: cardColor = "schilten"; break;
    case Schellen: cardColor = "schellen"; break;
    case Clubs: cardColor = "clubs"; break;
    case Diamonds: cardColor = "diamonds"; break;
    case Hearts: cardColor = "hearts"; break;
    case Spades: cardColor = "spades"; break;
 
    }
    return cardColor;
}

}




