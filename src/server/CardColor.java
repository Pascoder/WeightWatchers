package server;

public enum CardColor {
Rose,
Eichel,
Schilten,
Schellen;


public String toString() {
    String cardColor = "";
    switch (this) {
    case Rose: cardColor = "rose"; break;
    case Eichel: cardColor = "eichel"; break;
    case Schilten: cardColor = "schilten"; break;
    case Schellen: cardColor = "schellen"; break;
    }
    return cardColor;
}

}




