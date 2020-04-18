package client;

import javafx.application.Platform;
import client.ServiceLocator_JC;
//import chatGame.olmoClient.Model.ChatGame_Model;
//import chatGame.olmoClient.Views.ChatGame_View;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ClientController {

private ClientModel clientModel;
private static ClientView clientView;
ServiceLocator_JC serviceLocator;
	

	public ClientController(ClientModel clientModel, ClientView clientView) {
		this.clientModel = clientModel;
		this.clientView = clientView;
		Login_View view = clientView.getLoginView();
		
		
		view.getLoginButton().setOnAction(e -> clientModel.login(view.getUsernameField().getText(), view.getPasswordField().getText()));
		view.getRegisterButton().setOnAction(e -> clientModel.register(view.getUsernameField().getText(),view.getPasswordField().getText()));
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