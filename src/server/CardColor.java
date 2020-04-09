package server;

public enum CardColor {
Rose ("Rose","Spades"),
Eichel ("Eichel","Diamonds"),
Schilten ("Schilten","Clubs"),
Schellen ("Schellen","Hearts");

private String swissCards;
private String frenchCards;


private CardColor(String swissCards, String frenchCards) {
	this.swissCards = swissCards;
	this.frenchCards = frenchCards;
	
}

public String getSwissCards() {
	return swissCards;
}
public String getFrenchCards() {
	return frenchCards;
}
public String toString() {
    String cardColor = "";
    switch (this) {
    case Rose: cardColor = "Rose"; break;
    case Eichel: cardColor = "Eichel"; break;
    case Schilten: cardColor = "Schilten"; break;
    case Schellen: cardColor = "Schellen"; break;

    }
    return cardColor;
}

}




