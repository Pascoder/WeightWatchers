package server;

public enum CardColor {
Rose ("Rose","Spades"),
Eichel ("Eichel","Diamonds"),
Schilten ("Schilten","Clubs"),
Schellen ("Schellen","Hearts");

private String swissCards;
private String frenchCards;
private String language = "CH";

//Es ist somit möglich mit einer Karte die Französische und Schweizer Version zu haben
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
public void setLanguage(String language) {
	this.language = language;
}

public String toString() {
	String cardColor = "";
	if(this.language.equals("CH")) {
    switch (this) {
    case Rose: cardColor = "Rose"; break;
    case Eichel: cardColor = "Eichel"; break;
    case Schilten: cardColor = "Schilten"; break;
    case Schellen: cardColor = "Schellen"; break;
    }
	}else {
	switch (this) {
	case Rose: cardColor = "Spades"; break;
	case Eichel: cardColor = "Diamonds"; break;
	case Schilten: cardColor = "Clubs"; break;
	case Schellen: cardColor = "Hearts"; break;
	    }
	}
    return cardColor;
}

}




