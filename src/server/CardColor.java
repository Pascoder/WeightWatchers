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
    String suit = "";
    switch (this) {
   /* case Clubs: suit = "clubs"; break;
    case Diamonds: suit = "diamonds"; break;
    case Hearts: suit = "hearts"; break;
    case Spades: suit = "spades"; break;*/
    case Rose: suit = "rose"; break;
    case Eichel: suit = "eichel"; break;
    case Schilten: suit = "schilten"; break;
    case Schellen: suit = "schellen"; break;
    }
    return suit;
}

}




