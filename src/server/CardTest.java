package server;

public class CardTest {

	public static void main(String[] args) {
		Card card = new Card(CardColor.Rose,CardRank.Six);
	
		System.out.println(card.getCardColor().getSwissCards());
		System.out.println(card.getCardColor().getFrenchCards());

	}

}
