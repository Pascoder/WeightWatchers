package client;


import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login_View extends GridPane {
	
	private TextField usernameField, registerField; 
	private PasswordField passwordField;
	private Button loginButton, registerButton; 
	private ChoiceBox Language;
	private Stage stage;
	private ClientModel model;
	private Label registerLabel, usernameLabel, passwordLabel;
	private Menu_Login_View MenuLgVi;
	private Image image;
	private Translator_JC t;
    private ServiceLocator_JC sl;
	
	public Login_View(Stage stage, ClientModel model ) {
		this.stage = stage;
		this.model = model;
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		this.stage.setTitle("Login Jass Game");
		Text title = new Text("Welcome to Jassen");
		this.add(title, 3, 0);
		
		this.MenuLgVi = new Menu_Login_View();
		this.add(MenuLgVi, 0, 0, 1, 4);
		
        this.usernameLabel = new Label("Username");
        this.add(usernameLabel, 2, 6);
        this.usernameField = new TextField();
        this.add(this.usernameField, 3, 6);
        
        this.passwordLabel = new Label("Password");
        this.add(passwordLabel, 2, 8);
        this.passwordField = new PasswordField();
        this.add(this.passwordField, 3, 8);
        
        this.loginButton = new Button("Login");
		this.add(loginButton, 3, 10);
		
		this.setStyle("-fx-background-color: lightgreen");

		registerLabel = new Label("Nicht registiert?");
		this.add(registerLabel, 3, 11);
       
		this.registerButton = new Button("Register");
		this.add(registerButton, 3, 12);
		
		// olmo: Auskommentiert, weil Fehler beim Laden Image
		//this.image = new Image(this.getClass().getClassLoader().getResourceAsStream("client/"+ "Schweizer_Jasskarten.jpg"));

		
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(250);
		imageView.setFitWidth(250);
		this.add(imageView, 3, 15);
		
		
		
		this.setStyle("-fx-background-color: #D8D8D8");
		this.setAlignment(Pos.CENTER);
		this.setHgap(20);
		this.setVgap(10);
		
		Scene scene = new Scene(this,650,600);
		this.stage.setScene(scene);
		this.stage.show();
	}

	public TextField getUsernameField() {
		return usernameField;
	}

	public void setUsernameField(TextField usernameField) {
		this.usernameField = usernameField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public Button getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(Button loginButton) {
		this.loginButton = loginButton;
	}

	public Button getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(Button registerButton) {
		this.registerButton = registerButton;
	}

	public TextField getRegisterField() {
		return registerField;
	}

	public void setRegisterField(TextField registerField) {
		this.registerField = registerField;
	}

	public Label getRegisterLabel() {
		return registerLabel;
	}

	public void setRegisterLabel(Label registerLabel) {
		this.registerLabel = registerLabel;
	}

	
		
	}

