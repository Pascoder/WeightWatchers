package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class ClientMVC extends Application{
	private ClientModel clientModel;
	private ClientView clientView;
	private ClientController clientController;

	public static void main(String[] args){
		launch(args);

	}
	public void start (Stage stage) throws Exception{
		this.clientModel = new ClientModel();
		this.clientView = new ClientView(stage, clientModel);
		this.clientController = new ClientController(clientModel, clientView);
		clientView.start();
		
	}
	public void stop () {
		clientView.stop();
	}

}
