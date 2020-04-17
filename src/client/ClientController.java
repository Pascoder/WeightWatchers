package client;

public class ClientController {

private ClientModel clientModel;
private ClientView clientView;

	

	public ClientController(ClientModel clientModel, ClientView clientView) {
		this.clientModel = clientModel;
		this.clientView = clientView;
		
		Login_View view = clientView.getLoginView();
		view.getLoginButton().setOnAction(e -> clientModel.login(view.getUsernameField().getText(), view.getPasswordField().getText()));
		
	}

}

