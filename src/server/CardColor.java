package server;

public enum CardColor {
//Schweizer oder Französische (optional) Karten sind möglich
Rose,
Eichel,
Schilten,
Schellen;
/*Spades,
Hearts,
Diamonds,
Clubs;*/

public String toString() {
    String cardColor = "";
    switch (this) {
   /* case Clubs: suit = "clubs"; break;
    case Diamonds: suit = "diamonds"; break;
    case Hearts: suit = "hearts"; break;
    case Spades: suit = "spades"; break;*/
    case Rose: cardColor = "rose"; break;
    case Eichel: cardColor = "eichel"; break;
    case Schilten: cardColor = "schilten"; break;
    case Schellen: cardColor = "schellen"; break;
    }
    return cardColor;
}

}




