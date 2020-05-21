package client;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game_View extends BorderPane{
 
    private Stage stage;
  	private ClientModel model;
    private Translator_JC t;
    private ServiceLocator_JC sl;
    //Layout
    protected HBox menu, player4InfoBox, ourPointsBox, opponentsPointsBox;
    protected TilePane player4CardBox;
    BorderPane tableBox;
    protected GridPane teppichBox;
    protected VBox rightBox, buttomBox,player1, player2, player3, pointsBox, P13, P24, trumpfBox;
    protected Menu_Game_View menuGame;
    protected ControlBar_Game_View controls;
    //Labels,Buttons...
    protected Label p1_name,p1name,p2_name,p2name,p3_name,p3name,p4_name,p4name,trumpf, verdecktekarten1,verdecktekarten2,verdecktekarten3,onTurn, selectedT, ourPointsLabel, ourPlbl, opponentsPointsLabel, oppPlbl;
    protected Button weis, nextRound;
    protected ToggleButton karte1, karte2, karte3,karte4,karte5,karte6,karte7,karte8,karte9;
    protected ArrayList <ToggleButton> playerButtons;
   
 
    //protected Image imgverdeckt = new Image(this.getClass().getClassLoader().getResourceAsStream("KartenHaltend.jpg"));
    

    //protected ImageView imageverdeckt = new ImageView(imgverdeckt);
    //protected ImageView imageverdeckt2 = new ImageView(imgverdeckt);
    //protected ImageView imageverdeckt3 = new ImageView(imgverdeckt);
    protected ImageView cardOnTable1 = new ImageView();   
    protected ImageView cardOnTable2 = new ImageView();
    protected ImageView cardOnTable3 = new ImageView();
    protected ImageView cardOnTable4 = new ImageView();
    protected ImageView trumpfColor = new ImageView();
   
    
    // Leon
    public Game_View(Stage stage, ClientModel model) { 
    	this.stage = stage;
    	this.setId("GameView");
		this.model = model;
		//this.setPadding(new Insets(10,10,10,10));
		
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		this.getStyleClass().add("game_view");

		// Menu
		this.menuGame = new Menu_Game_View();
		this.setTop(menuGame);
		

		
		
		
		
		//Center: tableBox; Players 1-3 % teppichBox
		playerButtons = new ArrayList<ToggleButton>();
		
		this.tableBox = new BorderPane();
		this.tableBox.getStyleClass().add("gameTableBox");
		//this.tableBox.setGridLinesVisible(true);
		this.setCenter(tableBox);
		
		
		
			//Player1 Rechts
			this.player1 = new VBox();
			this.player1.getStyleClass().add("gamePlayerBox");
			//this.player1.setSpacing(30);
			//this.player1.setPadding(new Insets(10,60,60,60));
			this.p1_name = new Label("--");
			this.player1.getChildren().add(p1_name);
			
			
			//this.imageverdeckt.setFitHeight(140);
			//this.imageverdeckt.setFitWidth(100);
			//player1.getChildren().add(imageverdeckt);
			this.tableBox.setRight(player1);
			this.player1.setAlignment(Pos.CENTER);
			
			//teppichBox
			this.teppichBox = new GridPane();
			this.teppichBox.setGridLinesVisible(true);
			this.teppichBox.setId("gameTeppichBox");
			//this.teppichBox.getColumnConstraints().add(new ColumnConstraints(100));
			//this.teppichBox.getRowConstraints().add(new RowConstraints(105));

			//this.teppichBox.setPadding(new Insets(10,60,60,60));
			this.teppichBox.setAlignment(Pos.CENTER);
			this.teppichBox.add(cardOnTable1, 1, 2);
			this.teppichBox.add(cardOnTable2, 2, 1);
			this.teppichBox.add(cardOnTable3, 1, 0);
			this.teppichBox.add(cardOnTable4, 0, 1);
			this.cardOnTable1.setId("gameCardsOnTable");
			this.cardOnTable2.setId("gameCardsOnTable");
			this.cardOnTable3.setId("gameCardsOnTable");
			this.cardOnTable4.setId("gameCardsOnTable");
			
			cardOnTable1.setFitHeight(100);
			cardOnTable1.setFitWidth(80);
			cardOnTable2.setFitHeight(100);
			cardOnTable2.setFitWidth(80);
			cardOnTable3.setFitHeight(100);
			cardOnTable3.setFitWidth(80);
			cardOnTable4.setFitHeight(100);
			cardOnTable4.setFitWidth(80);
			this.teppichBox.setId("gameTeppichBox");
			
			this.tableBox.setCenter(teppichBox);
			

			//Player2 oben 
			this.player2 = new VBox();
			this.player2.getStyleClass().add("gamePlayerBox");
			//this.player2.setSpacing(30);
			//this.player2.setPadding(new Insets(10,60,60,60));
			this.p2_name = new Label("--");
			this.player2.getChildren().add(p2_name);
			this.tableBox.setTop(player2);
			//this.player2.setAlignment(Pos.CENTER);
			
			
			//this.imageverdeckt2.setFitHeight(140);
			//this.imageverdeckt2.setFitWidth(100);
			//player2.getChildren().add(imageverdeckt2);
			
			
			//Player3 links
			this.player3 = new VBox();
			this.player3.getStyleClass().add("gamePlayerBox");
			//this.player3.setSpacing(30);
			//this.player3.setPadding(new Insets(10,60,60,60));
			this.p3_name = new Label("--");
			this.player3.getChildren().add(p3_name);
			this.tableBox.setLeft(player3);
			//this.player3.setAlignment(Pos.CENTER);
			
			//this.imageverdeckt3.setFitHeight(140);
			//this.imageverdeckt3.setFitWidth(100);
			//player3.getChildren().add(imageverdeckt3);
			
			
	//Player4 unten (eigener Spieler)
			this.player4CardBox = new TilePane();
			this.player4CardBox.getStyleClass().add("gamePlayer4CardBox");
			//this.player4CardBox.setPadding(new Insets(10,10,10,10));
			
			//this.tablePane.add(player4, 0, 2,10,1);
			
				//Hier unten werden dann ImageViews verwendet die Image beinhalten
			
			//TODO kann mit for schleife gelöst werden
			
			this.karte1 = new ToggleButton();
			this.karte1.getStyleClass().add("gamePlayerCards");
			this.karte1.setVisible(false);
			player4CardBox.getChildren().add(karte1);
			playerButtons.add(karte1);
			
			this.karte2 = new ToggleButton();
			this.karte1.getStyleClass().add("gamePlayerCards");
			player4CardBox.getChildren().add(karte2);
			playerButtons.add(karte2);
			this.karte2.setVisible(false);

			
			this.karte3 = new ToggleButton();
			this.karte1.getStyleClass().add("gamePlayerCards");
			player4CardBox.getChildren().add(karte3);
			playerButtons.add(karte3);
			this.karte3.setVisible(false);
			
			this.karte4 = new ToggleButton();
			this.karte1.getStyleClass().add("gamePlayerCards");
			player4CardBox.getChildren().add(karte4);
			playerButtons.add(karte4);
			this.karte4.setVisible(false);


			this.karte5 = new ToggleButton();
			this.karte1.getStyleClass().add("gamePlayerCards");
			player4CardBox.getChildren().add(karte5);
			playerButtons.add(karte5);
			this.karte5.setVisible(false);


			this.karte6 = new ToggleButton();
			this.karte1.getStyleClass().add("gamePlayerCards");
			player4CardBox.getChildren().add(karte6);
			playerButtons.add(karte6);
			this.karte6.setVisible(false);


			this.karte7 = new ToggleButton();
			this.karte1.getStyleClass().add("gamePlayerCards");
			player4CardBox.getChildren().add(karte7);
			playerButtons.add(karte7);
			this.karte7.setVisible(false);


			this.karte8 = new ToggleButton();
			this.karte1.getStyleClass().add("gamePlayerCards");
			player4CardBox.getChildren().add(karte8);
			playerButtons.add(karte8);
			this.karte8.setVisible(false);


			this.karte9 = new ToggleButton();
			this.karte1.getStyleClass().add("gamePlayerCards");
			player4CardBox.getChildren().add(karte9);
			playerButtons.add(karte9);
			this.karte9.setVisible(false);


		//Bottom
		this.buttomBox = new VBox();
		this.player4InfoBox = new HBox();
		this.player4InfoBox.getStyleClass().add("gamePlayer4InfoBox");
		this.p4_name = new Label("--");
		this.onTurn = new Label("-");	
		
		this.controls = new ControlBar_Game_View();
	
		
		this.player4InfoBox.getChildren().addAll(p4_name, onTurn);
		this.buttomBox.getChildren().addAll(this.player4InfoBox, this.player4CardBox, this.controls);
		
		
		
		
		this.setBottom(buttomBox);
		
		// RightBox (Our and opponent points) -> RightPane
		this.rightBox = new VBox();
		this.rightBox.getStyleClass().add("gameRightBox");
		
		//Unsere Punkte
		this.ourPointsBox = new HBox();
		this.ourPointsLabel = new Label();
		this.ourPlbl = new Label("--"); // 
		this.ourPointsBox.getChildren().addAll(this.ourPointsLabel, this.ourPlbl);
		
		// Namen unser Team
		this.P13 = new VBox();
		p1name = new Label("--");
		p3name = new Label("--");
		this.P13.getChildren().addAll(p1name, p3name);
		
		
		//Punkte Gegner
		this.opponentsPointsBox = new HBox();
		this.opponentsPointsLabel = new Label();
		this.oppPlbl = new Label("0");
		this.opponentsPointsBox.getChildren().addAll(this.opponentsPointsLabel,this.oppPlbl);
		
		//Namen Gegner
		this.P24 = new VBox();
		p2name = new Label("--");
		p4name = new Label("--");
		this.P24.getChildren().addAll(p2name, p4name);

				
		// Trumpf Displaying
		this.trumpfBox = new VBox();
		this.trumpf = new Label();
		this.trumpf.setId("label_game_view");
		this.trumpf.setId("game_trumpfIcon");
		this.trumpfBox.getChildren().addAll(this.trumpf, this.trumpfColor);
		
		
		this.pointsBox = new VBox();
		this.pointsBox.getChildren().addAll(ourPointsBox, P13, opponentsPointsBox, P24);
		this.pointsBox.getStyleClass().add("gamePointsBox");
		
		this.rightBox.getChildren().addAll(trumpfBox, pointsBox);
		this.setRight(rightBox);
		
		setTexts();	
	
	Scene scene = new Scene(this);
	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	stage.setWidth(1100);
	stage.setHeight(800);
	stage.setScene(scene);
	stage.setResizable(true);
    }
    
    public void setTexts() {
	this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
	this.stage.setTitle(t.getString("game.windowTitle"));
	    this.menuGame.setTexts();
	    this.controls.setTexts();
	    this.ourPointsLabel.setText(t.getString("game.lblPoints"));
	    this.opponentsPointsLabel.setText(t.getString("game.lblOpponentPoints"));
	    this.trumpf.setText(t.getString("game.lblTrumpf"));
	    
	}

    
    public void setGraphic(int num, ImageView img, boolean b) {
    	switch(num) {
    	case 1:
    		this.karte1.setGraphic(img);
    		this.karte1.setDisable(!b);
    	break;
    	case 2: 
    		
    		this.karte2.setGraphic(img);
    		this.karte2.setDisable(!b);
    	break;
    	case 3:
    		this.karte3.setGraphic(img);
    		this.karte3.setDisable(!b);
    	break;
    	case 4:
    		this.karte4.setGraphic(img);
    		this.karte4.setDisable(!b);
    	break;
    	case 5:
    		this.karte5.setGraphic(img);
    		this.karte5.setDisable(!b);
    	break;
    	case 6:
    		this.karte6.setGraphic(img);
    		this.karte6.setDisable(!b);
    	break;
    	case 7:
    		this.karte7.setGraphic(img);
    		this.karte7.setDisable(!b);
    	break;
    	case 8:
    		this.karte8.setGraphic(img);
    		this.karte8.setDisable(!b);
    	break;
    	case 9:
    		this.karte9.setGraphic(img);
    		this.karte9.setDisable(!b);
    	break;
    	}
   
    }
    
public void placeCardtoTable(int num, Image img) {
	
	
	switch(num) {
	case 1:
		this.cardOnTable1.setImage(img);
	break;
	case 2: this.cardOnTable2.setImage(img);
	break;
	case 3: this.cardOnTable3.setImage(img);
	break;
	case 4: this.cardOnTable4.setImage(img);
	break;
	default:
		System.out.println("Zu viele Karten auf dem Tisch!! max: 4!");
	
	}
    	
    }
    
    public ToggleButton getToggleButton(int button) {
    	ToggleButton b = null;
    	switch(button) {
    	case 1:
    		b = this.karte1;
    	break;
    	case 2: 
    		b = this.karte2;
    	break;
    	case 3:
    		b =this.karte3;
    	break;
    	case 4:
    		b =this.karte4;
    	break;
    	case 5:
    		b =this.karte5;
    	break;
    	case 6:
    		b = this.karte6;
    	break;
    	case 7:
    		b = this.karte7;
    	break;
    	case 8:
    		b = this.karte8;
    	break;
    	case 9:
    		b = this.karte9;
    	break;
    	}
    	return b;
    	
    }
    
    public Label getOnTurn() {
    	return this.onTurn;
    }
    
    
    
    
    
    public String getPlayerName() {
    	return this.p4_name.getText();
    }
    
    //Alle Button unsichtbar machen und nur soviele sichtbar wie Karten in der Hand sind
	public void makeButtonsVisible(int cards) {
		for(ToggleButton b : playerButtons) {
			b.setVisible(false);
		}
		for (int i=0; i<cards; i++) {
			playerButtons.get(i).setVisible(true);

		}
		
	}

	public void removeCardsonTable() {
		this.cardOnTable1.setImage(null);
		this.cardOnTable2.setImage(null);
		this.cardOnTable3.setImage(null);
		this.cardOnTable4.setImage(null);
	}
	
	public void setTitle(String title) {
		this.stage.setTitle(title);
	}
	
	public void setTrumpf(String trumpf) {
	    this.trumpf.setText(trumpf);
		
	}
	public void setTrumpfColor(String trumpf) {
		String lang = "_CH";
		if(sl.getConfiguration().isFrenchCards()) {
			lang = "_FR";
			}
		JassImage ji = new JassImage();
		this.trumpfColor.setImage(ji.getTrumpfImage(trumpf+lang).getImage());

		
	    
	}
	
	public Label getTrumpf() {
		return this.trumpf;
	}
	
	public void setPoints(String points) {
		this.ourPlbl.setText(points);
	}

	public void setOtherTeamPoints(String points) {
		this.oppPlbl.setText(points);
		
	}
	public ControlBar_Game_View getControls() {
	    return this.controls;
	}

	
   
    
	
    }

