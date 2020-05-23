package server;

// 2020 Oliver Mosimann, Testklasse zum testen einzelner Methoden der Game-Logik

public class Game_Tests {

    public static void main(String[] args) {

	int game_ID = 1;
	String name = "Game1";
	Player player1 = new Player(1, "player1", "1231");
	Player player2 = new Player(9, "player2", "1232");
	Player player3 = new Player(3, "player3", "1233");
	Player player4 = new Player(45, "player4", "1234");
	Player player5 = new Player(60, "player4", "1235");
	Player player6 = new Player(65, "player4", "1236");

	ServiceLocator sl = ServiceLocator.getServiceLocator();

	// sl.setLogger(configureLogger());
	// sl.setConfiguration(new Configuration());

	Game game = new Game(game_ID, name);

	// Spieler game hinzufügen
	game.addPlayer(player1);
	game.addPlayer(player2);
	game.addPlayer(player3);
	game.addPlayer(player4);
	// game.addPlayer(player5);
	// game.addPlayer(player6);

	game.nextRound();

	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	String card = game.searchPlayer(1).getHand().get(2).toString();
	System.out.println("String Karte:_" + card);
	game.playCard(1, 1, game.stringToCard(card));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(2));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(3));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(4));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 45, game.searchPlayer(45).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig ---------------------------------------------");
	game.nextRound();
	System.out.println("-----");
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("------------------------------------------------------------");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 45, game.searchPlayer(45).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println("-----");
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 45, game.searchPlayer(45).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println("-----");
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	System.out.println("Runde fertig");
	game.nextRound();

	game.playCard(1, 45, game.searchPlayer(45).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 45, game.searchPlayer(45).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 45, game.searchPlayer(45).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 45, game.searchPlayer(45).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(1));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 45, game.searchPlayer(45).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.nextRound();
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 45, game.searchPlayer(45).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 1, game.searchPlayer(1).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 9, game.searchPlayer(9).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 3, game.searchPlayer(3).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());

	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));
	System.out.println("-----");
	game.playCard(1, 45, game.searchPlayer(45).getHand().get(0));
	System.out.println("CardsOnTAble:" + game.getCardsOnTable());
	System.out.println("Runde fertig");
	game.nextRound();
	System.out.println(game.searchPlayer(1));
	System.out.println(game.searchPlayer(9));
	System.out.println(game.searchPlayer(3));
	System.out.println(game.searchPlayer(45));

	System.out.println(game.getLastWinnerPoints());
	System.out.println((game.getLastWinner_ID()));

	// Kartenverteilen Testen
	// game.spreadCards();

    }
}
