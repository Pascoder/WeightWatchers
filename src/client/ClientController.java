package client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import client.ServiceLocator_JC;
//import chatGame.olmoClient.Model.ChatGame_Model;
//import chatGame.olmoClient.Views.ChatGame_View;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import server.Lobby;

public class ClientController {

private ClientModel clientModel;
private static ClientView clientView;
ServiceLocator_JC serviceLocator;
	

	public ClientController(ClientModel clientModel, ClientView clientView) {
		this.clientModel = clientModel;
		this.clientView = clientView;
		Login_View view = clientView.getLoginView();
		Lobby_View lobbyview = clientView.getLobbyView();
		Game_View gameview = clientView.getGameView();
		
		
		view.getLoginButton().setOnAction(e -> clientModel.sayLogin(view.getUsernameField().getText(), view.getPasswordField().getText()));
		view.getRegisterButton().setOnAction(e -> clientModel.sayRegister(view.getUsernameField().getText(),view.getPasswordField().getText()));
		lobbyview.getCreateGameButton().setOnAction(e -> clientModel.sayCreateGame(lobbyview.getTextField().getText()));
		lobbyview.getJoinButton().setOnAction(c -> clientModel.sayJoinGame(lobbyview.getTextField().getText()));
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

	
	//System.out.println("ObservableList:"+newplayers.get(0));
	
	
	/*Platform.runLater(new Runnable(){

		@Override
		public void run() {
			clientView.getLobbyView().setPlayerOn(newplayers);
		}
	});*/
	
}

public static void loadGames(String [] games) {
	clientView.getLobbyView().gamesList.clear();
	for(String s : games) {
		clientView.getLobbyView().gamesList.appendText(s+"\n");
	}
}

public static void joinGame(String [] joinedgames) {
	clientView.getLobbyView().selectedGameList.clear();
	for(String s : joinedgames) {
		clientView.getLobbyView().selectedGameList.appendText(s+"\n");
	}
}

public static void loadPlayersonGame(String [] playersOnGame, String client) {
	//System.out.println("ClientController: "+client);
	boolean p1 = false;
	boolean p2 = false;
	boolean p3 = false;
	for(String player:playersOnGame) {
		if(client.equals(player)) {
			clientView.getGameView().p4_name.setText(player);
			
		}else {
			if(p1 == false) {
				clientView.getGameView().p1_name.setText(player);
				p1 = true;
				}else {
					if(p2 == false) {
						clientView.getGameView().p2_name.setText(player);
						p2 = true;
					}else {
						if(p3 == false);
						clientView.getGameView().p3_name.setText(player);
						p3 = true;
					}	
			}
			
		}
		
			
	}
	
	
	
	
	
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