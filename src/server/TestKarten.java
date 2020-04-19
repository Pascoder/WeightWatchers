package server;

public class TestKarten {

	public static void main(String[] args) {
		Card card = new Card(CardColor.C, CardRank.Ace);
		System.out.println(card);
		CardDeck cd =  new CardDeck();
		System.out.println(cd);

	}

}
