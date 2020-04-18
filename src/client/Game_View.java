package client;


import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game_View {
 
    private Label lblRound, lblPoints, lblPointsOppenent, lblGameType, lblTrumpf, lblTitle;
    private Label player1, player2, player3, player4;
    public int pointsX, pointY, round; 
    private Stage stage;
  	private ClientModel model;
    private Translator_JC t;
    private ServiceLocator_JC sl;
    private MyPlayerBox myPlayerB; 
    private PlayerBox1 playerB1, playerB2, playerB3; 
    private BorderPane playerGrid; 
    private BorderPane playedCardsPane; 
    private PlayedCard pc1, pc2, pc3, pc4; 
    public Menu_Game_View gameMenu; 

    public Game_View(Stage stage, ClientModel model) { 
    	this.stage = stage;
		this.model = model;
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		// Spieler erstellen 
		this.myPlayerB = new MyPlayerBox();
		this.playerB1 = new PlayerBox1();
		this.playerB2 = new PlayerBox1();
		this.playerB3 = new PlayerBox1();
		
		//BorderPane für gespielte Karten
		this.playedCardsPane = new BorderPane();
		this.pc1 = new PlayedCard();
		this.pc2 = new PlayedCard();
		this.pc3 = new PlayedCard();
		this.pc4 = new PlayedCard();
		
		playerGrid = new BorderPane();
		playerGrid.setLeft(this.playerB1);
		playerGrid.setRight(this.playerB2);
		playerGrid.setTop(this.playerB3);
		playerGrid.setBottom(this.myPlayerB);
		playerGrid.setCenter(this.playedCardsPane);
		
		// Right Side of Game View 
		
		// Overview Box
		this.lblTitle = new Label(t.getString("game.lblTitle"));
		this.lblGameType = new Label(t.getString("game.lblGameType"));
		this.lblTrumpf = new Label(t.getString("game.lblTrumpf"));
		this.lblRound = new Label(t.getString("game.lblRound"));
//		this.round  // wie soll ich die Runde hinzufügen?
		
		VBox overViewBox = new VBox(); 
		overViewBox.getChildren().addAll(this.lblTitle, this.lblGameType, this.lblTrumpf, this.lblRound);
		
		// Box our Team
		this.player1 = new Label(t.getString("game.lblMyPlayer"));
		this.player2 = new Label(t.getString("game.lblPlayer1"));
		this.lblPoints = new Label(t.getString("game.lblPoints"));
		
		VBox ourTeamBox = new VBox();
		ourTeamBox.getChildren().addAll(this.player1, this.player2, this.lblPoints);
		
		// Box opponent
		this.player3 = new Label(t.getString("game.lblPlayer2"));
		this.player4 = new Label(t.getString("game.lblPlayer4"));
		this.lblPointsOppenent = new Label(t.getString("game.lblOpponentPoints"));
		
		VBox oppTeamBox = new VBox();
		oppTeamBox.getChildren().addAll(this.player3, this.player4, this.lblPointsOppenent);
		
		
		VBox teamsBox = new VBox();
		teamsBox.getChildren().addAll(ourTeamBox,oppTeamBox);
		
		VBox rightBox = new VBox();
		rightBox.getChildren().addAll(overViewBox,teamsBox);
		
//	lblName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
//	btnSignIn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
//	txtName.setMinWidth(150);
//	txtName.setPrefWidth(150);

	// ControlBar
	ControlBar_Game_View controls = new ControlBar_Game_View();
	controls.getStyleClass().add("controls");
	controls.setId("controls");
	
	// Menu
	this.gameMenu = new Menu_Game_View();

	// BorderPane
	GridPane root = new GridPane();
	root.add(this.gameMenu, 0, 0);
	root.add(playerGrid, 0, 1, 20, 20);
	root.add(rightBox, 4, 1, 20, 20);
	root.add(controls, 0, 15);
	
//	root.getStyleClass().add("root"); // Class for styling
	
	Scene scene = new Scene(root);
	stage.setScene(scene);

//	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	stage.setTitle("Game");
    }
	
    }

