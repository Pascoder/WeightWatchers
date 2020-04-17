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

