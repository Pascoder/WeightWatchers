package client;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login_View extends GridPane {
	
	
	private TextField usernameField, passwordField; 
	private Button loginButton, registerButton; 
	private ChoiceBox Language;
	private Stage stage;
	private ClientModel model;
	
	public Login_View(Stage stage, ClientModel model ) {
		this.stage = stage;
		this.model = model;
		this.stage.setTitle("Login");
		Text title = new Text("Welcome to Jassen");
		this.add(title, 3, 0);
		
		Language english = new Language("En", "English");
	    Language german = new Language("DE", "Deutsch");
		FlowPane rootFlow = new FlowPane();
		ObservableList<Language> languages = FXCollections.observableArrayList(english, german);
		Label languageLabel = new Label ("Select Language");
	    ChoiceBox<Language> choiceBox = new ChoiceBox<Language>(languages);
	    rootFlow.setPadding(new Insets(10));
        rootFlow.getChildren().addAll(languageLabel, choiceBox);
        rootFlow.setPadding(new Insets(10));
        rootFlow.setHgap(10);
        this.add(choiceBox, 3, 1);
        this.add(languageLabel, 3, 2);
        
        Label usernameLabel = new Label("Username");
        this.add(usernameLabel, 2, 6);
        this.usernameField = new TextField();
        this.add(this.usernameField, 3, 6);
        
        Label passwordLabel = new Label("Password");
        this.add(passwordLabel, 2, 8);
        this.passwordField = new TextField();
        this.add(this.passwordField, 3, 8);
        
        this.loginButton = new Button("Login");
		this.add(loginButton, 3, 12);
        

		this.registerButton = new Button("Register");
		this.add(registerButton, 3, 15);
		
		this.setStyle("-fx-background-color: lightgreen");
		this.setAlignment(Pos.CENTER);
		this.setHgap(20);
		this.setVgap(10);
		
		Scene scene = new Scene(this,300,300);
		this.stage.setScene(scene);
		this.stage.show();
	}
		
	}

