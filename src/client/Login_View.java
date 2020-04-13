package client;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login_View extends Application {
	
	public void start(Stage primaryStage){
		
		RootPane root = new RootPane();
		
		Scene scene = new Scene(root, 300, 300);
		
		primaryStage.setTitle("Tree Manager");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[]args) {
		launch(args);
	}
		
	}

