package client;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login_View  {
	private Stage stage;
	private ClientModel model;

    
    


    public Login_View(Stage stage, ClientModel model) {
	
	this.stage = stage;
	this.model = model;
	
	GridPane root = new GridPane();
	Label l1 = new Label("Login_View");
	root.add(l1, 0, 0);
	Scene scene = new Scene(root);
	stage.setScene(scene);
	
	

	
	
    }



    
    

}
