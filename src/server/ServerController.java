package server;

import java.util.ArrayList;

public class ServerController {
final private GameView view;
private Game game;
private CardDeck deck;

public ServerController(Game game, GameView view) {
	this.view = view;
	this.game = game;
	
	//Karte Spielen 
	this.view.deal.setOnAction(c->{
		Card correctCard = null;
		String karte = view.txtDealCard.getText();
		String playerID = view.turnof.getText();//zeigt welcher Spieler Momentan am Zug ist
		int pID = Integer.parseInt(playerID);
		ArrayList<Card> cards =deck.getDeck();
		for(int i = 0; i< cards.size();i++) {
			if(karte.equals(cards.get(i))) {
				correctCard = cards.get(i);
			}
		}
		//GameID wird aus dem Game geholt, playerID aus dem Spieler der am Zug ist
		game.playCard(game.getGameID(), pID, correctCard);
		
		
	});
}
	
}
