package client;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import server.Lobby;
import server.Player;

public class TESTCLIENTCONTROLLER  {
	
	private ClientModel clientModel;
	private static TESTCLIENTVIEW clientView;
	ServiceLocator_JC serviceLocator;
	
	public TESTCLIENTCONTROLLER(ClientModel clientModel, TESTCLIENTVIEW clientView) {
	
	
	this.clientModel = clientModel;
	TESTCLIENTCONTROLLER.clientView = clientView;
	
	Login_View view = clientView.getLoginView();
	Lobby_View lobbyview = clientView.getLobbyView();
	Game_View gameview = clientView.getGameView();
	
	
	view.getLoginButton().setOnAction(e -> testStart());
	
	
	
//	view.getLoginButton().setOnAction(e -> clientModel.sayLogin(view.getUsernameField().getText(), view.getPasswordField().getText()));
	view.getRegisterButton().setOnAction(e -> clientModel.sayRegister(view.getUsernameField().getText(),view.getPasswordField().getText()));
	lobbyview.getCreateGameButton().setOnAction(e -> clientModel.sayCreateGame(lobbyview.getTextField().getText()));
//	lobbyview.getJoinButton().setOnAction(c -> clientModel.sayJoinGame(lobbyview.getTextField().getText()));
	
	clientView.getLobbyView().gamesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
		    String selectedItem = clientView.getLobbyView().gamesList.getSelectionModel().getSelectedItem().toString();
		    clientModel.sayJoinGame(selectedItem.substring(2));
		}
	    }
	});

	
	
	
	
	gameview.getToggleButton(1).setOnAction((c)-> { 
		
		String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame()+"";   
		String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id()+"";
		ArrayList <String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
		 String [] card = hand.get(0).split("\\$");
		clientModel.sayMove(gameID, playerID, card[0]);
		//gameview.getToggleButton(1).setVisible(false);
	});
	
	gameview.getToggleButton(2).setOnAction((c)-> { 
		String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame()+"";   
		String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id()+"";
		ArrayList <String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
		 String [] card = hand.get(1).split("\\$");
		clientModel.sayMove(gameID, playerID, card[0]);
		//gameview.getToggleButton(2).setVisible(false);
	});
	
	gameview.getToggleButton(3).setOnAction((c)-> { 
		String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame()+"";   
		String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id()+"";
		ArrayList <String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
		 String [] card = hand.get(2).split("\\$");
		clientModel.sayMove(gameID, playerID, card[0]);
		//gameview.getToggleButton(3).setVisible(false);
	});
	
	gameview.getToggleButton(4).setOnAction((c)-> { 
		String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame()+"";   
		String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id()+"";
		ArrayList <String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
		 String [] card = hand.get(3).split("\\$");
		clientModel.sayMove(gameID, playerID, card[0]);
		//gameview.getToggleButton(4).setVisible(false);
	});
	
	gameview.getToggleButton(5).setOnAction((c)-> { 
		String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame()+"";   
		String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id()+"";
		ArrayList <String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
		 String [] card = hand.get(4).split("\\$");
		clientModel.sayMove(gameID, playerID, card[0]);
		//gameview.getToggleButton(5).setVisible(false);
	});
	
	gameview.getToggleButton(6).setOnAction((c)-> { 
		String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame()+"";   
		String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id()+"";
		ArrayList <String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
		 String [] card = hand.get(5).split("\\$");
		clientModel.sayMove(gameID, playerID, card[0]);
		//gameview.getToggleButton(6).setVisible(false);
	});
	
	gameview.getToggleButton(7).setOnAction((c)-> { 
		String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame()+"";   
		String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id()+"";
		ArrayList <String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
		 String [] card = hand.get(6).split("\\$");
		clientModel.sayMove(gameID, playerID, card[0]);
		//gameview.getToggleButton(7).setVisible(false);
	});
	
	gameview.getToggleButton(8).setOnAction((c)-> { 
		String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame()+"";   
		String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id()+"";
		ArrayList <String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
		 String [] card = hand.get(7).split("\\$");
		clientModel.sayMove(gameID, playerID, card[0]);
		//gameview.getToggleButton(8).setVisible(false);
	});
	
	gameview.getToggleButton(9).setOnAction((c)-> { 
		String gameID = clientModel.getPlayer(gameview.getPlayerName()).getActualGame()+"";   
		String playerID = clientModel.getPlayer(gameview.getPlayerName()).getPlayer_id()+"";
		ArrayList <String> hand = clientModel.getPlayer(gameview.getPlayerName()).getHandAsStrings();
		 String [] card = hand.get(8).split("\\$");
		clientModel.sayMove(gameID, playerID, card[0]);
		//gameview.getToggleButton(9).setVisible(false);
	});
	
	
		
		
		
	

	
	
	
}

private Object testStart() {
	Random rand = new Random();
	clientModel.sayLogin("" + rand.nextInt(), "1234");
	clientModel.sayJoinGame("game");
	switchview(2);
	
		return null;
	}

public static void updateLoginInfoLabel(String info) {
	Platform.runLater(new Runnable(){
		@Override
		public void run() {
			clientView.getLoginView().getRegisterLabel().setText(info);
		}
	});
}


public static void switchview(int view) {
	Platform.runLater(new Runnable(){

		@Override
		public void run() {
			clientView.switchView(view);
		}
	});
	
}





public static void loadPlayersOnline (String [] players) {
//ObservableList<String> newplayers = FXCollections.observableArrayList();

	clientView.getLobbyView().playerOnList.clear();
		for(String s : players) {
			clientView.getLobbyView().playerOnList.appendText(s+"\n");
		}


}


public static void loadGames(String [] games) {
	ObservableList<String> gameList = FXCollections.observableArrayList();
	
//clientView.getLobbyView().gamesList.clear();
for(String s : games) {
    gameList.add(s);
}
clientView.getLobbyView().setGames(gameList);
}



//public static void joinGame(String [] joinedgames) {
//clientView.getLobbyView().selectedGameList.clear();
//for(String s : joinedgames) {
//	clientView.getLobbyView().selectedGameList.appendText(s+"\n");
//}
//}

//public void joinGame() {
//try {
//    String selectedItem = clientView.getLobbyView().gamesList.getSelectionModel().getSelectedItem().toString();
//    clientModel.sayJoinGame(selectedItem);
//    //view.lblMainRoom.setText(Chatroom_Model.getMainRoom());
//    //updateChatter();
//} catch (Exception e) {
//    e.printStackTrace();
//}
//}



public static void loadPlayersonGame(Player [] playersOnGame, String client) {
Platform.runLater(new Runnable(){
	
	public void run() {
//lädt die Karten des aktuellen Spielers in eine ArrayList
ArrayList <String> cardList = new ArrayList <String>();

for(Player p : playersOnGame) {
	if(p.getName().equals(client)) {
		cardList = p.getHandAsStrings(); 
		
		
		clientView.getGameView().getOnTurn().setText("On move: "+p.getonMove());
		
	}
	
}

JassImage img = new JassImage();
String lang = "_CH";//TODO Zugriff auf Configuration herstellen CardLanguage holen
String[] cards;


for(int i = 0; i < cardList.size();i++) {
	cards = cardList.get(i).split("\\$");
	ImageView c = img.getCardImage(cards[0]+lang);
	c.setFitHeight(100);
	c.setFitWidth(80);
	clientView.getGameView().setGraphic(i+1,c);
}


		//Logik um die Reihenfolge am Tisch für alle richtig zu setzen
		if(playersOnGame[0].getName().equals(client)) {
			clientView.getGameView().p4_name.setText(playersOnGame[0].getName());
			clientView.getGameView().p1_name.setText(playersOnGame[1].getName());
			clientView.getGameView().p2_name.setText(playersOnGame[2].getName());
			clientView.getGameView().p3_name.setText(playersOnGame[3].getName());

			}
		
		if(playersOnGame[1].getName().equals(client)) {
			clientView.getGameView().p4_name.setText(playersOnGame[1].getName());
			clientView.getGameView().p1_name.setText(playersOnGame[2].getName());
			clientView.getGameView().p2_name.setText(playersOnGame[3].getName());
			clientView.getGameView().p3_name.setText(playersOnGame[0].getName());
		}
		if(playersOnGame[2].getName().equals(client)) {
			clientView.getGameView().p4_name.setText(playersOnGame[2].getName());
			clientView.getGameView().p1_name.setText(playersOnGame[3].getName());
			clientView.getGameView().p2_name.setText(playersOnGame[0].getName());
			clientView.getGameView().p3_name.setText(playersOnGame[1].getName());
		}
		if(playersOnGame[3].getName().equals(client)) {
			clientView.getGameView().p4_name.setText(playersOnGame[3].getName()); 
			clientView.getGameView().p1_name.setText(playersOnGame[0].getName());
			clientView.getGameView().p2_name.setText(playersOnGame[1].getName());
			clientView.getGameView().p3_name.setText(playersOnGame[2].getName());
		}
	}
});

}



public static void loadCardsOnTable(String cardsontable) {
JassImage img = new JassImage();
String lang = "_CH";//TODO Zugriff auf Configuration herstellen CardLanguage holen
String [] cards = cardsontable.split("\\$");

/*for(int i = 0; i < cards.length;i++) {
	ImageView c = img.getCardImage(cards[0]+lang);
	c.setFitHeight(80);
	c.setFitWidth(60);
	clientView.getGameView().placeCardtoTable(i+1, c);

	}*/

}


public static void showCards(String[] spreadCards, String client) {
// TODO Auto-generated method stub
for(String card : spreadCards) {
	System.out.println(card);
}

}

}








