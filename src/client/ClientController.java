package client;

import javafx.application.Platform;

public class ClientController {

private ClientModel clientModel;
private static ClientView clientView;

	

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

	
	
}

///

// Vorlage Lobby Controller muss noch angepasst werden
import chatroom.olmoClient.ServiceLocator;
import chatroom.olmoClient.Model.Chatroom_Model;
import chatroom.olmoClient.Views.Chatroom_View;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Chatroom_Controller extends Basic_Controller<Chatroom_Model, Chatroom_View> {
    ServiceLocator serviceLocator;

    public Chatroom_Controller(Chatroom_Model model, Chatroom_View view) {
	super(model, view);

	setRooms();
	setChatter();
	setMessages();

	updateRooms();

	view.controls.btnQuit.setOnAction(e -> Platform.exit());
	view.controls.btnCancel.setOnAction(e -> clearForms());
	view.controls.btnLeave.setOnAction(e -> leaveRoom());
	view.controls.btnSend.setOnAction(e -> msgSend());

	view.roomList.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
		    choiceRoom();
		}
	    }
	});
	view.chatterList.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
		    choiceChatter();
		}
	    }
	});

    }

    public void setRooms() {
	view.setRoom(Chatroom_Model.getRooms());
    }

    public void setChatter() {
	view.setChatter(model.getChatter());
    }

    public void setMessages() {
	view.setMessages(model.getActMsg());
    }

    public void updateRooms() {
	try {
	    model.listChatrooms();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void updateChatter() {
	try {
	    model.listChatters();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void choiceRoom() {
	try {
	    String selectedItem = view.roomList.getSelectionModel().getSelectedItem().toString();
	    model.enterRoom(selectedItem);
	    view.lblMainRoom.setText(Chatroom_Model.getMainRoom());
	    updateChatter();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void leaveRoom() {
	try {
	    model.leaveRoom();
	    view.lblMainRoom.setText("--");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void choiceChatter() {
	try {
	    String selectedItem = view.chatterList.getSelectionModel().getSelectedItem().toString();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void msgSend() {
	try {
	    model.sendMessage(view.controls.txtMsg.getText());
	    clearForms();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void goBack() {
	view.stop();
	// Start_Controller.showStart();
    }

    private void clearForms() {
	view.controls.txtMsg.setText("");
    }
}