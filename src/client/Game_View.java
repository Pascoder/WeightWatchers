package client;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    protected HBox menu, player4, bottomBox;
    protected GridPane centerPane;
    protected VBox player1, player2, player3;
    protected MenuBar menubar;
    protected Menu menumain;
    protected MenuItem sprache;
    //Labels,Buttons...
    protected Label round, points,p1_name,p2_name,p3_name,p4_name,verdecktekarten1,verdecktekarten2,verdecktekarten3,turn;
    protected Button weis;
    protected ToggleButton karte1, karte2, karte3,karte4,karte5,karte6,karte7,karte8;
    
    //Geh�rt nachher ins CSS
    protected Image img1 = new Image(this.getClass().getClassLoader().getResourceAsStream("card_images/deutsch/Eichel_Acht.jpg"));
    protected Image img2 = new Image(this.getClass().getClassLoader().getResourceAsStream("card_images/deutsch/Eichel_Ass.jpg"));
    protected Image img3 = new Image(this.getClass().getClassLoader().getResourceAsStream("card_images/deutsch/Eichel_Koenig.jpg"));
    protected Image img4 = new Image(this.getClass().getClassLoader().getResourceAsStream("card_images/deutsch/Eichel_Neun.jpg"));
    protected Image img5 = new Image(this.getClass().getClassLoader().getResourceAsStream("card_images/deutsch/Eichel_Ober.jpg"));
    protected Image img6 = new Image(this.getClass().getClassLoader().getResourceAsStream("card_images/deutsch/Eichel_Sechs.jpg"));
    protected Image img7 = new Image(this.getClass().getClassLoader().getResourceAsStream("card_images/deutsch/Eichel_Sieben.jpg"));
    protected Image img8 = new Image(this.getClass().getClassLoader().getResourceAsStream("card_images/deutsch/Eichel_Under.jpg"));
    protected Image imgverdeckt = new Image(this.getClass().getClassLoader().getResourceAsStream("card_images/deutsch/Poker-fuenf-verdeckte-karten.jpg"));
    
    protected ImageView image1 = new ImageView(img1);
    protected ImageView image2 = new ImageView(img2);
    protected ImageView image3 = new ImageView(img3);
    protected ImageView image4 = new ImageView(img4);
    protected ImageView image5 = new ImageView(img5);
    protected ImageView image6 = new ImageView(img6);
    protected ImageView image7 = new ImageView(img7);
    protected ImageView image8 = new ImageView(img8);
    protected ImageView imageverdeckt = new ImageView(imgverdeckt);
    protected ImageView imageverdeckt2 = new ImageView(imgverdeckt);
    protected ImageView imageverdeckt3 = new ImageView(imgverdeckt);
    
   
    
    
    public Game_View(Stage stage) { 
    	this.stage = stage;
		this.model = model;
		this.setPadding(new Insets(10,10,10,10));
		
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		//Top MenuLeiste (sollte eine Menubar sein hier nur als Beispiel)
		this.menu = new HBox();
		this.menubar = new MenuBar();
		this.menumain = new Menu("Options");
		this.menubar.getMenus().add(menumain);
		this.sprache = new MenuItem("Sprache");
		this.menumain.getItems().add(sprache);
		this.menu.getChildren().add(menubar);
		
		
		
		this.round = new Label("Runde: 1");
		menu.getChildren().add(round);
		this.points = new Label("Punkte: 0");
		menu.getChildren().add(points);
		
		this.setTop(menu);
		menu.setPadding(new Insets(40,40,40,40));
		menu.setSpacing(30); //Abstand zwischen Label, Button...
		
		//Center Table and Players
		this.centerPane = new GridPane();
		this.setCenter(centerPane);
		
		
			//Player1
			this.player1 = new VBox();
			this.player1.setSpacing(30);
			this.player1.setPadding(new Insets(10,60,60,60));
			this.p1_name = new Label("Frank");
			this.player1.getChildren().add(p1_name);
			
			
			this.imageverdeckt.setFitHeight(140);
			this.imageverdeckt.setFitWidth(100);
			player1.getChildren().add(imageverdeckt);
			this.centerPane.add(player1, 0, 1);
			
			
			
			
			
			//Player2
			this.player2 = new VBox();
			this.player2.setSpacing(30);
			this.player2.setPadding(new Insets(10,60,60,60));
			this.p2_name = new Label("Leon");
			this.player2.getChildren().add(p2_name);
			this.centerPane.add(player2, 1, 0);
			
			
			this.imageverdeckt2.setFitHeight(140);
			this.imageverdeckt2.setFitWidth(100);
			player2.getChildren().add(imageverdeckt2);
			
			
			//Player3
			this.player3 = new VBox();
			this.player3.setSpacing(30);
			this.player3.setPadding(new Insets(10,60,60,60));
			this.p3_name = new Label("Pascal");
			this.player3.getChildren().add(p3_name);
			this.centerPane.add(player3, 2, 1);
			
			this.imageverdeckt3.setFitHeight(140);
			this.imageverdeckt3.setFitWidth(100);
			player3.getChildren().add(imageverdeckt3);
			
			//Player4
			this.player4 = new HBox();
			this.player4.setSpacing(30);
			this.player4.setPadding(new Insets(10,10,10,10));
			this.p4_name = new Label("Oli");
			this.player4.getChildren().add(p4_name);
			this.centerPane.add(player4, 0, 2,10,1);
			
				//Hier unten werden dann ImageViews verwendet die Image beinhalten
		
			this.karte1 = new ToggleButton();
			karte1.setGraphic(image1);
			this.image1.setFitHeight(130);
			this.image1.setFitWidth(65);
			player4.getChildren().add(karte1);
			
			this.karte2 = new ToggleButton();
			karte2.setGraphic(image2);
			this.image2.setFitHeight(130);
			this.image2.setFitWidth(65);
			player4.getChildren().add(karte2);
			
			this.karte3 = new ToggleButton();
			karte3.setGraphic(image3);
			this.image3.setFitHeight(130);
			this.image3.setFitWidth(65);
			player4.getChildren().add(karte3);
			
			this.karte4 = new ToggleButton();
			karte4.setGraphic(image4);
			this.image4.setFitHeight(130);
			this.image4.setFitWidth(65);
			player4.getChildren().add(karte4);
			
			this.karte5 = new ToggleButton();
			karte5.setGraphic(image5);
			this.image5.setFitHeight(130);
			this.image5.setFitWidth(65);
			player4.getChildren().add(karte5);
			
			this.karte6 = new ToggleButton();
			karte6.setGraphic(image6);
			this.image6.setFitHeight(130);
			this.image6.setFitWidth(65);
			player4.getChildren().add(karte6);
			
			this.karte7 = new ToggleButton();
			karte7.setGraphic(image7);
			this.image7.setFitHeight(130);
			this.image7.setFitWidth(65);
			player4.getChildren().add(karte7);
			
			this.karte8 = new ToggleButton();
			karte8.setGraphic(image8);
			this.image8.setFitHeight(130);
			this.image8.setFitWidth(65);
			player4.getChildren().add(karte8);
			
			
			
		//Bottom
		this.bottomBox = new HBox();
		this.setBottom(bottomBox);
		this.turn = new Label("Oli's Turn");
		this.bottomBox.getChildren().add(turn);
		this.weis = new Button ("Weis");
		this.bottomBox.getChildren().add(weis);
		this.bottomBox.setPadding(new Insets(10,10,10,10));
		this.bottomBox.setSpacing(30);
		
		
		
		
	
//	root.getStyleClass().add("root"); // Class for styling
	
	Scene scene = new Scene(this,1000,850);
	stage.setScene(scene);

//	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	stage.setTitle("Game");
    }
	
    }

