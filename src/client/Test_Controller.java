package client;

public class Test_Controller {
	
	private ClientModel cm;
	private Test_View tv;
	
	
	
	public Test_Controller(ClientModel clientModel, Test_View testView) {
		 
			this.cm = clientModel;
			this.tv = testView;
			
			tv.getBtn_sayHello().setOnAction(event -> clientModel.sayHello(tv.getTxtf_username().getText()));
			tv.getBtn_login().setOnAction(event -> clientModel.login(tv.getTxtf_username().getText(), tv.getTxtf_password().getText()));
		
		}



	
	

}
