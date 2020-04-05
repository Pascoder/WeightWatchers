package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class ClientMVC extends Application{

	public static void main(String[] args){
		launch(args);

	}
	public void start (Stage stage) {
		ClientModel clientModel = new ClientModel();
		ClientView clientView = new ClientView(stage, clientModel);
		ClientController clientController = new ClientController(clientModel, clientView);
		clientView.start();
	}

}
