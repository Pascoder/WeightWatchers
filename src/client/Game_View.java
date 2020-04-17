package client;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game_View {

    
    ListView messageList;

    private Label player1, player2, player3, Player4; 
    private Label lblround, lblpoints, lblPointsOppenent, lblGameType, lblTrumpf;
    public int pointsX, pointY; 
    private Menu_Game_View menuGV;
    private Stage stage;
  	private ClientModel model;
    private Translator_JC t;
    private ServiceLocator_JC sl;
    private Image trumpfImage; 
    

    public Game_View(Stage stage, ClientModel model) { 
    	this.stage = stage;
		this.model = model;
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();

//	lblName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
//	btnSignIn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
//	txtName.setMinWidth(150);
//	txtName.setPrefWidth(150);

   

	// ControlBar
	ControlBar_Game_View controls = new ControlBar_Game_View();
	controls.getStyleClass().add("controls");
	controls.setId("controls");

	// BorderPane
	GridPane root = new GridPane();
//	root.getStyleClass().add("root"); // Class for styling
	



	
	Scene scene = new Scene(root);
	stage.setScene(scene);

	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	stage.setTitle("Private Chat");
    }
	
    }

