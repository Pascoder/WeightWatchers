package client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Optional;

import client.ServiceLocator_JC;


import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import server.Player;

public class ClientController {

private static ClientModel clientModel;
private static ClientView clientView;
ServiceLocator_JC serviceLocator;


	

	public ClientController(ClientModel clientModel, ClientView clientView) {
		ClientController.clientModel = clientModel;
		ClientController.clientView = clientView;
		
		Login_View view = clientView.getLoginView();
		Lobby_View lobbyview = clientView.getLobbyView();
		Game_View gameview = clientView.getGameView();
		
		
		view.getLoginButton().setOnAction(e -> clientModel.sayLogin(view.getUsernameField().getText(), view.getPasswordField().getText()));
		view.getRegisterButton().setOnAction(e -> clientModel.sayRegister(view.getUsernameField().getText(),view.getPasswordField().getText()));
		lobbyview.getCreateGameButton().setOnAction((e) -> {clientModel.sayCreateGame(lobbyview.getTextField().getText());
		lobbyview.getTextField().clear();
		});
		clientView.getLobbyStage().setOnCloseRequest(c->{
			clientModel.sayGoodBye("Lobby1");
		});
		lobbyview.getLeaveLobbyButton().setOnAction(c -> {
			clientModel.sayGoodBye("Lobby1");
			clientView.getLobbyStage().hide();
		});
		lobbyview.getLeaveGameButton().setOnAction(c->{
			clientModel.sayGoodBye("Lobby2");
			ObservableList<String> selectedGame = FXCollections.observableArrayList();
			lobbyview.setSelectedGame(selectedGame);
		});
		lobbyview.getCreateGameButton().disableProperty().bind(lobbyview.getTextField().textProperty().isEmpty());
	
		
		clientView.getLobbyView().gamesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent e) {
			try {
			if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
			    String selectedItem = clientView.getLobbyView().gamesList.getSelectionModel().getSelectedItem().toString();
			    clientModel.sayJoinGame(selectedItem.substring(2));
			    ObservableList<String> selectedGame = FXCollections.observableArrayList();
			    selectedGame.add(selectedItem);
			    lobbyview.setSelectedGame(selectedGame);
			    
			    
			}}
			catch (Exception b) {
			    b.printStackTrace();
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
	
	Platform.runLater(new Runnable(){

		@Override
		public void run() {
			ObservableList<String> newplayers = FXCollections.observableArrayList(players);
			clientView.getLobbyView().setPlayerOn(newplayers);
			
	
		}
	});
}


public static void loadGames(String [] games) {
	
	Platform.runLater(new Runnable(){

		@Override
		public void run() {
	    	ObservableList<String> gameList = FXCollections.observableArrayList(games);
	    	clientView.getLobbyView().setGames(gameList);
		}
	});

}



//public static void joinGame(String [] joinedgames) {
//	clientView.getLobbyView().selectedGameList.clear();
//	for(String s : joinedgames) {
//		clientView.getLobbyView().selectedGameList.appendText(s+"\n");
//	}
//}

//public void joinGame() {
//	try {
//	    String selectedItem = clientView.getLobbyView().gamesList.getSelectionModel().getSelectedItem().toString();
//	    clientModel.sayJoinGame(selectedItem);
//	    //view.lblMainRoom.setText(Chatroom_Model.getMainRoom());
//	    //updateChatter();
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//}



public static void loadPlayersonGame(Player [] playersOnGame, String client, String trumpf, String teamscore) {
	
	Platform.runLater(new Runnable(){
		
		public void run() {
	//lädt die Karten des aktuellen Spielers in eine ArrayList
	ArrayList <String> cardList = new ArrayList <String>();
	String [] teams = teamscore.split("\\$");
	
	for(Player p : playersOnGame) {
		if(p.getName().equals(client)) {
			cardList = p.getHandAsStrings(); 
			clientView.getGameView().getOnTurn().setText("On move: "+p.getonMove());
			clientView.getGameView().setTitle("Game of "+p.getName());
				if(p.getonMove() == true) {
					//Trumpf ausw�hlen aber nur wenn 1. Runde
					clientView.getGameView().getOnTurn().setTextFill(Color.web("red"));
				}else {
					clientView.getGameView().getOnTurn().setTextFill(Color.web("black"));
				}
		
		}

	}
	//Set Team Points
	for(int i = 0; i< teams.length;i++) {
		String team[] = teams[i].split("\\|");
		for(int c = 0; c<team.length;c++) {
			if(team[c].equals(client)) {
				clientView.getGameView().setPoints(team[2]);
			}
		}
	}
	
	JassImage img = new JassImage();
	String lang = "_CH";//TODO Zugriff auf Configuration herstellen CardLanguage holen
	String[] cards;
	
	clientView.getGameView().makeButtonsVisible(cardList.size());
	for(int i = 0; i < cardList.size();i++) {
		cards = cardList.get(i).split("\\$");
		
		ImageView c = img.getCardImage(cards[0]+lang);
		c.setFitHeight(100);
		c.setFitWidth(80);
		
		clientView.getGameView().setGraphic(i+1,c,Boolean.parseBoolean(cards[1]));
	}
	
	
			//Logik um die Reihenfolge am Tisch für alle richtig zu setzen
			if(playersOnGame[0].getName().equals(client)) {
				clientView.getGameView().p4_name.setText(playersOnGame[0].getName());
				clientView.getGameView().p1_name.setText(playersOnGame[1].getName());
				clientView.getGameView().p2_name.setText(playersOnGame[2].getName());
				clientView.getGameView().p3_name.setText(playersOnGame[3].getName());
				
				if(trumpf.equals("null")) {
				clientView.getGameView().setTrumpf("Trumpf: Bitte Trumpf mit klick auf Karte W�hlen");
				clientView.getGameView().getTrumpf().setTextFill(Color.web("red"));
				}
				}
				
			if(!trumpf.equals("null")) {
				clientView.getGameView().setTrumpf("Trumpf: "+trumpf);
				clientView.getGameView().getTrumpf().setTextFill(Color.web("black"));
			}
				
			
			if(playersOnGame[1].getName().equals(client)) {
				clientView.getGameView().p4_name.setText(playersOnGame[1].getName());
				clientView.getGameView().p1_name.setText(playersOnGame[2].getName());
				clientView.getGameView().p2_name.setText(playersOnGame[3].getName());
				clientView.getGameView().p3_name.setText(playersOnGame[0].getName());
				if(!trumpf.equals("null")) {
					clientView.getGameView().setTrumpf("Trumpf: "+trumpf);
				}
			}
			if(playersOnGame[2].getName().equals(client)) {
				clientView.getGameView().p4_name.setText(playersOnGame[2].getName());
				clientView.getGameView().p1_name.setText(playersOnGame[3].getName());
				clientView.getGameView().p2_name.setText(playersOnGame[0].getName());
				clientView.getGameView().p3_name.setText(playersOnGame[1].getName());
				if(!trumpf.equals("null")) {
					clientView.getGameView().setTrumpf("Trumpf: "+trumpf);
				}
			}
			if(playersOnGame[3].getName().equals(client)) {
				clientView.getGameView().p4_name.setText(playersOnGame[3].getName()); 
				clientView.getGameView().p1_name.setText(playersOnGame[0].getName());
				clientView.getGameView().p2_name.setText(playersOnGame[1].getName());
				clientView.getGameView().p3_name.setText(playersOnGame[2].getName());
				if(!trumpf.equals("null")) {
					clientView.getGameView().setTrumpf("Trumpf: "+trumpf);
				}
			}
		}
	});
	
}



public static void loadCardsOnTable(String cardsontable) {
	System.out.println("CLIENT CARDSONTABLE: "+cardsontable);
	Platform.runLater(new Runnable(){

		@Override
		public void run() {
			
	
	JassImage img = new JassImage();
	String lang = "_CH";//TODO Zugriff auf Configuration herstellen CardLanguage holen
	String [] cards = cardsontable.split("\\$");
	
	
		for(int i = 0; i < cards.length;i++) {
			String [] card = cards[i].split("\\|");
			Image b = img.getCardImage(card[0]+lang).getImage();
			clientView.getGameView().placeCardtoTable(i+1, b);
			}
		
	}
	
	

		
});
	
	
	}
	

public static void showCards(String[] spreadCards, String client) {
	// TODO Auto-generated method stub
	for(String card : spreadCards) {
		System.out.println(card);
	}
	
}

public static void emptyTable() {
	Platform.runLater(new Runnable(){

		@Override
		public void run() {
		
				clientView.getGameView().removeCardsonTable();
			
			

		}
});
	
}

public static void showWinnerTeam(String winnerteamid) {
	Platform.runLater(new Runnable(){

		@Override
		public void run() {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Spiel vorbei!");
	alert.setContentText("Winnerteam: "+ winnerteamid + "/n" + "Neues Spiel?");
	alert.showAndWait();
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){
	    System.out.println("User möchte spielen");
	} else {
	    System.out.println("Nicht spielen");
	}
		}
	});
}

public static void showStapelWinner() {
	Platform.runLater(new Runnable(){

		@Override
		public void run() {
	
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Stapel fertig!");
	alert.setContentText("Stapel fertig. Weiterspielen? -> OK / Beenden? -> Cancel");
	alert.showAndWait();
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){
		clientModel.sayNextStaple();
	    
	} else if(result.get() == ButtonType.CANCEL){
//	    clientModel.sayExitGame();
	}
		}

		
	});

}





}







 

//	setGames();
//	setPlayers();
//	setMessages();
//
//	updateGames();
//
//	view.controls.btnQuit.setOnAction(e -> Platform.exit());
//	view.controls.btnCancel.setOnAction(e -> clearForms());
//	view.controls.btnLeave.setOnAction(e -> leaveGame());
//	view.controls.btnSend.setOnAction(e -> msgSend());
//
//	view.GameList.setOnMouseClicked(new EventHandler<MouseEvent>() {
//	    @Override
//	    public void handle(MouseEvent e) {
//		if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
//		    choiceGame();
//		}
//	    }
//	});
//	view.playerList.setOnMouseClicked(new EventHandler<MouseEvent>() {
//	    @Override
//	    public void handle(MouseEvent e) {
//		if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
//		    choiceChatter();
//		}
//	    }
//	});
//
//    }
//
//    public void setGames() {
//	view.setGame(ChatGame_Model.getGames());
//    }
//
//    public void setPlayer() {
//	view.setChatter(model.getPlayer());
//    }
//
//    public void setMessages() {
//	view.setMessages(model.getActMsg());
//    }
//
//    public void updateGames() {
//	try {
//	    model.listGames();
//	} catch (Exception e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
//    }
//
//    public void updatePlayer() {
//	try {
//	    model.listChatters();
//	} catch (Exception e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
//    }
//
//    private void choiceGame() {
//	try {
//	    String selectedItem = view.gameList.getSelectionModel().getSelectedItem().toString();
//	    model.enterGame(selectedItem);
//	    view.lblMainGame.setText(ChatGame_Model.getMainGame());
//	    updatePlayer();
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//    }
//
//    private void leaveGame() {
//	try {
//	    model.leaveGame();
//	    view.lblMainGame.setText("--");
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//    }
//
//    private void choiceChatter() {
//	try {
//	    String selectedItem = view.chatterList.getSelectionModel().getSelectedItem().toString();
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//    }
//
//    private void msgSend() {
//	try {
//	    model.sendMessage(view.controls.txtMsg.getText());
//	    clearForms();
//	} catch (Exception e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
//    }
//
//    private void goBack() {
//	view.stop();
//	// Start_Controller.showStart();
//    }
//
//    private void clearForms() {
//	view.controls.txtMsg.setText("");
//    }
//}