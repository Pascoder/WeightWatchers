package client;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game_View extends BorderPane{
 
    private Stage stage;
  	private ClientModel model;
    private Translator_JC t;
    private ServiceLocator_JC sl;
    //Layout
    protected HBox menu, player4;
    protected GridPane centerPane;
    protected VBox player1, player2, player3;
    //Labels,Buttons...
    protected Label round, points,p1_name,p2_name,p3_name,p4_name,verdecktekarten1,verdecktekarten2,verdecktekarten3,k1,k2,k3,k4,k5,k6,k7,k8;
   
    public Game_View(Stage stage/*, ClientModel model*/) { 
    	this.stage = stage;
		this.model = model;
		this.setPadding(new Insets(10,10,10,10));
		
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		//Top MenuLeiste (sollte eine Menubar sein hier nur als Beispiel)
		this.menu = new HBox();
		this.round = new Label("Runde: 1");
		menu.getChildren().add(round);
		this.points = new Label("Punkte: 0");
		menu.getChildren().add(points);
		
		this.setTop(menu);
		menu.setPadding(new Insets(10,10,10,10));
		menu.setSpacing(30); //Abstand zwischen Label, Button...
		
		//Center Table and Players
		this.centerPane = new GridPane();
		this.setCenter(centerPane);
			//Player1
			this.player1 = new VBox();
			this.player1.setSpacing(30);
			this.player1.setPadding(new Insets(10,10,10,10));
			this.p1_name = new Label("Frank");
			this.player1.getChildren().add(p1_name);
			this.centerPane.add(player1, 0, 1);
			this.verdecktekarten1 = new Label("VerdeckteKarten");
			this.player1.getChildren().add(verdecktekarten1);
			
			//Player2
			this.player2 = new VBox();
			this.player2.setSpacing(30);
			this.player2.setPadding(new Insets(10,10,10,10));
			this.p2_name = new Label("Leon");
			this.player2.getChildren().add(p2_name);
			this.centerPane.add(player2, 1, 0);
			this.verdecktekarten2 = new Label("VerdeckteKarten");
			this.player2.getChildren().add(verdecktekarten2);
			
			//Player3
			this.player3 = new VBox();
			this.player3.setSpacing(30);
			this.player3.setPadding(new Insets(10,10,10,10));
			this.p3_name = new Label("Pascal");
			this.player3.getChildren().add(p3_name);
			this.centerPane.add(player3, 2, 1);
			this.verdecktekarten3 = new Label("VerdeckteKarten");
			this.player3.getChildren().add(verdecktekarten3);
			//Player4
			this.player4 = new HBox();
			this.player4.setSpacing(30);
			this.player4.setPadding(new Insets(10,10,10,10));
			this.p4_name = new Label("Oli");
			this.player4.getChildren().add(p4_name);
			this.centerPane.add(player4, 0, 2,3,1);
			this.k1 = new Label("Karte1");
			player4.getChildren().add(k1);
			this.k2 = new Label("Karte2");
			player4.getChildren().add(k2);
			this.k3 = new Label("Karte3");
			player4.getChildren().add(k3);
			this.k4 = new Label("Karte4");
			player4.getChildren().add(k4);
			this.k5 = new Label("Karte5");
			player4.getChildren().add(k5);
			this.k6 = new Label("Karte6");
			player4.getChildren().add(k6);
			this.k7 = new Label("Karte7");
			player4.getChildren().add(k7);
			this.k8 = new Label("Karte8");
			player4.getChildren().add(k8);
			
		
		
		
		
	
//	root.getStyleClass().add("root"); // Class for styling
	
	Scene scene = new Scene(this);
	stage.setScene(scene);

//	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	stage.setTitle("Game");
	stage.show();
    }
	
    }

