package client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Test_View {

	private Stage stage;
	private ClientModel model;
	
	private Button btn_login;
	private Button btn_sayHello;
	private TextField txtf_username;
	private TextField txtf_password;
    
    


    public Test_View(Stage stage, ClientModel model) {
	
	this.stage = stage;
	this.model = model;
	
	btn_sayHello = new Button("SayHello");
	btn_login = new Button("Login");
	txtf_username = new TextField("(Username)");
	txtf_password = new TextField("(Password)");


	GridPane root = new GridPane();
	Label l1 = new Label("Login_View");
	root.add(l1, 0, 0);
	
	root.add(btn_login, 1, 0);
	root.add(btn_sayHello, 1, 1);
	root.add(txtf_username, 2, 0);
	
	root.add(txtf_password, 3, 0);
	Scene scene = new Scene(root);
	stage.setScene(scene);
	
	

	
	
    }




	public Button getBtn_login() {
		return btn_login;
	}




	public void setBtn_login(Button btn_login) {
		this.btn_login = btn_login;
	}




	public TextField getTxtf_username() {
		return txtf_username;
	}




	public void setTxtf_username(TextField txtf_username) {
		this.txtf_username = txtf_username;
	}




	public TextField getTxtf_password() {
		return txtf_password;
	}




	public void setTxtf_password(TextField txtf_password) {
		this.txtf_password = txtf_password;
	}




	public Button getBtn_sayHello() {
		return btn_sayHello;
	}




	public void setBtn_sayHello(Button btn_sayHello) {
		this.btn_sayHello = btn_sayHello;
	}
	
	
}
