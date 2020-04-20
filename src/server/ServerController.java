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
			if(karte == cards.get(i).toString()) { //Bin mir nicht sicher ob das so l�uft
				correctCard = cards.get(i);
			}
		}
		//GameID wird aus dem Game geholt, playerID aus dem Spieler der am Zug ist
		game.playCard(game.getGameID(), pID, correctCard);
	});
	
	
	
	//Dieser Button soll das Deck mischen und die Karten den Spielern verteilen
	this.view.startgame.setOnAction(c->{
		
	});
	
	//Dieser Button setzt den Trumpf durch eingabe ins TextFeld
	this.view.settrumpf.setOnAction(c->{
		String trumpf = view.txtDealCard.getText();
		
		view.trumpf.setText("Trumpf: "+trumpf);
	});
}
	
}