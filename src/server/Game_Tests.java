package server;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game_Tests{

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
    
    
    //sl.setLogger(configureLogger());
    //sl.setConfiguration(new Configuration());
    
    
    Game game = new Game(game_ID, name);
    
    //Spieler game hinzufügen
    game.addPlayer(player1);
    game.addPlayer(player2);
    game.addPlayer(player3);
    game.addPlayer(player4);
    //game.addPlayer(player5);
    //game.addPlayer(player6);
    
    
    
  //Kartenverteilen Testen
   // game.spreadCards();
    

}


private Logger configureLogger() {
	Logger rootLogger = Logger.getLogger("");
rootLogger.setLevel(Level.FINEST);
// By default there is one handler: the console
Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
defaultHandlers[0].setLevel(Level.INFO);
// Add our logger
Logger ourLogger = Logger.getLogger("ServerLogger");
ourLogger.setLevel(Level.FINEST);
// Add a file handler, putting the rotating files in the tmp directory
try {
Handler logHandler = new FileHandler("%t/"+"ServerLogger"+ "_%u" + "_%g" + ".log",1000000, 9);
logHandler.setLevel(Level.FINEST);
ourLogger.addHandler(logHandler);
} catch (Exception e) { // If we are unable to create log files
throw new RuntimeException("Unable to initialize log files: "+ e.toString());
    }
return ourLogger;
}



}


//import java.util.ArrayList;
//import static org.junit.Assert.*;
//
//
//public class Game_Tests {
//    
//    Game testGame = new Game(1, "Testgame");
//    	
//	// We define the hands using abbreviations. The code at the bottom
//	// of this class can translate one of these strings into a card.
//	//
//	// Another method takes a set of five cards, and translates the whole hand
//	//
//	// Yet another method does this for a whole set of hands
//	private static String[][] highCards = {
//			{ "2S", "9C", "3H", "5D", "7H" },
//			{ "7S", "5C", "AH", "JD", "6H" },
//			{ "2S", "3S", "4S", "5S", "7S" },
//			{ "AS", "KC", "QH", "JD", "TH" }
//			};
//	
//	private static String[][] pairs = {
//			{ "2S", "2C", "3H", "5D", "7H" },
//			{ "2S", "AC", "3H", "5D", "AH" },
//			{ "3S", "2C", "3H", "KD", "QH" },
//			{ "9S", "2C", "2H", "5D", "7H" }
//			};
//
//	private static String[][] twoPairs = {
//			{ "2S", "2C", "7H", "5D", "7H" },
//			{ "2S", "AC", "5H", "5D", "AH" },
//			{ "3S", "2C", "3H", "2D", "QH" },
//			{ "9S", "2C", "2H", "5D", "5H" },
//			{ "2C", "JH", "JC", "QD", "QS" }
//			};
//	private static String[][] straights = {
//    			{ "2S", "3C", "4H", "5D", "6H" },
//    			{ "5S", "6C", "7H", "8D", "9H" },
//    			{ "TS", "JC", "QH", "KD", "AH" },
//    			{ "3S", "4C", "5H", "6D", "7H" }
//    			};
//
//	private static String[][] fulls = {
//			{ "2S", "2C", "4H", "2D", "4D" },
//			{ "KD", "6C", "KS", "6H", "KH" },
//			{ "TS", "TC", "TH", "2D", "2H" },
//			{ "4D", "4C", "4H", "3S", "3H" }
//			};
//
//	private static String[][] flushs = {
//			{ "4S", "2S", "KS", "JS", "TS" },
//			{ "KD", "8D", "4D", "JD", "6D" },
//			{ "7H", "TH", "8H", "AH", "9H" },
//			{ "3C", "4C", "8C", "JC", "KC" }
//			};
//
//	private static String[][] straightflushs = {
//			{ "2S", "3S", "4S", "5S", "6S" },
//			{ "5C", "6C", "7C", "8C", "9C" },
//			{ "TS", "JS", "QS", "KS", "AS" },
//			{ "3D", "4D", "5D", "6D", "7D" }
//			};
//
//	private static String[][] threeSomes = {
//			{ "2S", "2C", "2D", "KH", "QD" },
//			{ "6H", "6C", "6S", "7D", "KH" },
//			{ "TS", "TC", "TH", "3D", "2H" },
//			{ "4D", "4C", "4H", "KS", "AH" }
//			};
//
//	private static String[][] fourOfAKinds = {
//			{ "2S", "2C", "2D", "QD", "2H" },
//			{ "6H", "6C", "6S", "6D", "KH" },
//			{ "TS", "TC", "TH", "TD", "2H" },
//            		{ "4D", "4C", "4H", "4S", "AH" }
//			};
//	
//	// This is where we store the translated hands
//	ArrayList<ArrayList<Card>> highCardHands;
//	ArrayList<ArrayList<Card>> pairHands;
//	ArrayList<ArrayList<Card>> twoPairHands;
//	ArrayList<ArrayList<Card>> straightHands;
//	ArrayList<ArrayList<Card>> fullHands;
//	ArrayList<ArrayList<Card>> flushHands;
//	ArrayList<ArrayList<Card>> straightFlushHands;
//	ArrayList<ArrayList<Card>> threeSomeHands;
//	ArrayList<ArrayList<Card>> fourOfKindHands;
//	
//	/**
//	 * The makeHands method is called before each test method,
//	 * and prepares the translated hands. We recreate these for
//	 * each test method, in case the test method damages the data.
//	 */
//	@Before
//	
//
//	
//	public void makeHands() {
//		highCardHands = makeHands(highCards);
//		pairHands = makeHands(pairs);
//		twoPairHands = makeHands(twoPairs);
//		straightHands = makeHands(straights);
//		fullHands = makeHands(fulls);
//		flushHands = makeHands(flushs);
//		straightFlushHands = makeHands(straightflushs);
//		threeSomeHands = makeHands(threeSomes);
//		fourOfKindHands = makeHands(fourOfAKinds);
//	}
//
//	
//	/**
//	 * This is a test method for the isOnePair method in HandType.
//	 * We expect all HighCard hands to be false, all OnePair hands to
//	 * be true, all TwoPair hands to be true, etc.
//	 */
//	@Test
//	
//	
//	public void testIsOnePair() {
//		for (ArrayList<Card> hand : highCardHands) {
//			assertFalse(testHand.isOnePair(hand));
//		}
//		for (ArrayList<Card> hand : pairHands) {
//			assertTrue(testHand.isOnePair(hand));
//		}
//		for (ArrayList<Card> hand : twoPairHands) {
//			assertTrue(testHand.isOnePair(hand)); // Two-pair contains a pair
//		}
//	}
//
//	/**
//	 * This is the test method for the isTwoPair in HandType.
//	 */
//	@Test
//	public void testIsTwoPair() {
//		for (ArrayList<Card> hand : highCardHands) {
//			assertFalse(testHand.isTwoPair(hand));
//		}
//		for (ArrayList<Card> hand : pairHands) {
//			assertFalse(testHand.isTwoPair(hand));
//		}
//		for (ArrayList<Card> hand : twoPairHands) {
//			assertTrue(testHand.isTwoPair(hand));
//		}
//	}
//	@Test
//	public void testIsStraight() {
//		for (ArrayList<Card> hand : threeSomeHands) {
//			assertFalse(testHand.isStraight(hand));
//		}
//		for (ArrayList<Card> hand : pairHands) {
//			assertFalse(testHand.isStraight(hand));
//		}
//		for (ArrayList<Card> hand : twoPairHands) {
//			assertFalse(testHand.isStraight(hand));
//		}
//		for(ArrayList<Card> hand : straightHands) {
//			assertTrue(testHand.isStraight(hand));
//		}
//		
//	}
//	
//	@Test
//	public void testIsFullHouse() {
//		for (ArrayList<Card> hand : highCardHands) {
//			assertFalse(testHand.isFullHouse(hand));
//		}
//		for (ArrayList<Card> hand : pairHands) {
//			assertFalse(testHand.isFullHouse(hand));
//		}
//		for (ArrayList<Card> hand : twoPairHands) {
//			assertFalse(testHand.isFullHouse(hand));
//		}
//		for(ArrayList<Card> hand : straightHands) {
//			assertFalse(testHand.isFullHouse(hand));
//		}
//		for(ArrayList<Card> hand : fullHands) {
//			assertTrue(testHand.isFullHouse(hand));
//		}
//	}
//	
//	@Test
//	public void testIsFlush() {
//		for (ArrayList<Card> hand : pairHands) {
//			assertFalse(testHand.isFlush(hand));
//		}
//		for (ArrayList<Card> hand : twoPairHands) {
//			assertFalse(testHand.isFlush(hand));
//		}
//		for(ArrayList<Card> hand : straightHands) {
//			assertFalse(testHand.isFlush(hand));
//		}
//		for(ArrayList<Card> hand : fullHands) {
//			assertFalse(testHand.isFlush(hand));
//		}
//		for(ArrayList<Card> hand : flushHands) {
//			assertTrue(testHand.isFlush(hand));
//		}
//	}
//	
//	@Test
//	public void testIsStraightFlush() {
//		for (ArrayList<Card> hand : highCardHands) {
//			assertFalse(testHand.isStraightFlush(hand));
//		}
//		for (ArrayList<Card> hand : pairHands) {
//			assertFalse(testHand.isStraightFlush(hand));
//		}
//		for (ArrayList<Card> hand : twoPairHands) {
//			assertFalse(testHand.isStraightFlush(hand));
//		}
//		for(ArrayList<Card> hand : straightFlushHands) {
//			assertTrue(testHand.isStraightFlush(hand));
//		}
//		
//	}
//	
//	@Test
//	public void testIsThreeOfAKind() {
//		for (ArrayList<Card> hand : highCardHands) {
//			assertFalse(testHand.isThreeOfAKind(hand));
//		}
//		for (ArrayList<Card> hand : pairHands) {
//			assertFalse(testHand.isThreeOfAKind(hand));
//		}
//		for (ArrayList<Card> hand : twoPairHands) {
//			assertFalse(testHand.isStraightFlush(hand));
//		}
//		for(ArrayList<Card> hand : straightHands) {
//			assertFalse(testHand.isThreeOfAKind(hand));
//		}
//		for(ArrayList<Card> hand : fullHands) {
//			assertTrue(testHand.isThreeOfAKind(hand));
//		}
//		for(ArrayList<Card> hand : threeSomeHands) {
//			assertTrue(testHand.isThreeOfAKind(hand));
//		}
//	}
//	
//	@Test
//	public void testIsFourOfAKind() {
//		for (ArrayList<Card> hand : highCardHands) {
//			assertFalse(testHand.isFourOfAKind(hand));
//		}
//		for (ArrayList<Card> hand : pairHands) {
//			assertFalse(testHand.isFourOfAKind(hand));
//		}
//		for (ArrayList<Card> hand : twoPairHands) {
//			assertFalse(testHand.isFourOfAKind(hand));
//		}
//		for(ArrayList<Card> hand : straightHands) {
//			assertFalse(testHand.isFourOfAKind(hand));
//		}
//		for(ArrayList<Card> hand : fourOfKindHands) {
//			assertTrue(testHand.isFourOfAKind(hand));
//		}
//	}
//	
//	/**
//	 * Make an ArrayList of hands from an array of string-arrays
//	 */
//	private ArrayList<ArrayList<Card>> makeHands(String[][] handsIn) {
//		ArrayList<ArrayList<Card>> handsOut = new ArrayList<>();
//		for (String[] hand : handsIn) {
//			handsOut.add(makeHand(hand));
//		}
//		return handsOut;
//	}
//	
//	/**
//	 * Make a hand (ArrayList<Card>) from an array of 5 strings
//	 */
//	private ArrayList<Card> makeHand(String[] inStrings) {
//		ArrayList<Card> hand = new ArrayList<>();
//		for (String in : inStrings) {
//			hand.add(makeCard(in));
//		}
//		return hand;
//	}
//	
//	/**
//	 * Create a card from a 2-character String.
//	 * First character is the rank (2-9, T, J, Q, K, A) 
//	 * Second character is the suit (C, D, H, S)
//	 * 
//	 * No validation or error handling!
//	 */
//	private Card makeCard(String in) {
//		char r = in.charAt(0);
//		Card.Rank rank = null;
//		if (r <= '9') rank = Card.Rank.values()[r-'0' - 2];
//		else if (r == 'T') rank = Card.Rank.Ten;
//		else if (r == 'J') rank = Card.Rank.Jack;
//		else if (r == 'Q') rank = Card.Rank.Queen;
//		else if (r == 'K') rank = Card.Rank.King;
//		else if (r == 'A') rank = Card.Rank.Ace;
//		
//		char s = in.charAt(1);
//		Card.Suit suit = null;
//		if (s == 'C') suit = Card.Suit.Clubs;
//		if (s == 'D') suit = Card.Suit.Diamonds;
//		if (s == 'H') suit = Card.Suit.Hearts;
//		if (s == 'S') suit = Card.Suit.Spades;
//
//		return new Card(suit, rank);
//	}
//}
