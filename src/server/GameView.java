package server;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameView extends BorderPane{
//Layout
private HBox topBox, bottomBox ;
private VBox leftBox, rightBox, subleftBox1, subleftBox2,subrightBox1, subrightBox2;
//Buttons....
Label round, gametype,trumpf,player1,player2,card1,card2,card3,card4,player3,player4,turnof,pointsof,dealCard;
private Button deal;
private TextField txtDealCard;
private Stage stage;
	
public GameView(Stage primaryStage) {
	this.stage = primaryStage;
	
	//TopBox
	topBox = new HBox();
	this.round = new Label("Round: 1");
	this.topBox.getChildren().add(round);
	this.gametype = new Label("GameType: Trumpf");
	this.topBox.getChildren().add(gametype);
	this.trumpf = new Label("Trumpf: Schellen");
	this.topBox.getChildren().add(trumpf);
	topBox.setSpacing(100);
	this.topBox.setPadding(new Insets(10,10,10,10));
	this.setTop(topBox);
	
	//LeftBox
	leftBox =  new VBox();
	this.setLeft(leftBox);
	leftBox.setSpacing(100);
	this.leftBox.setPadding(new Insets(10,10,10,10));
		//SubLeftBox1
		subleftBox1 =  new VBox();
		this.player1 = new Label("Player 1");
		this.card1 = new Label("Cards: EichelKönig");
		this.subleftBox1.getChildren().add(player1);
		this.subleftBox1.getChildren().add(card1);
		this.leftBox.getChildren().add(subleftBox1);
		//SubLeftBox2
		subleftBox2 = new VBox();
		this.player2 = new Label("Player 2");
		this.card2 = new Label("Cards: EichelNeun");
		this.subleftBox2.getChildren().add(player2);
		this.subleftBox2.getChildren().add(card2);
		this.leftBox.getChildren().add(subleftBox2);
	
	//RightBox
	rightBox = new VBox();
	this.setRight(rightBox);
	this.setLeft(leftBox);
	rightBox.setSpacing(100);
	this.rightBox.setPadding(new Insets(10,10,10,10));
	
		//SubRightBox1
		subrightBox1 =  new VBox();
		this.player3 = new Label("Player 3");
		this.card3 = new Label("Cards: SchiltenKönig");
		this.subrightBox1.getChildren().add(player3);
		this.subrightBox1.getChildren().add(card3);
		this.rightBox.getChildren().add(subrightBox1);
		//SubRightBox2
		subrightBox2 =  new VBox();
		this.player4 = new Label("Player 4");
		this.card4 = new Label("Cards: SchellenKönig");
		this.subrightBox2.getChildren().add(player4);
		this.subrightBox2.getChildren().add(card4);
		this.rightBox.getChildren().add(subrightBox2);
		
	//BottomBox
	bottomBox = new HBox();
	this.setBottom(bottomBox);
	bottomBox.setSpacing(100);
	this.bottomBox.setPadding(new Insets(10,10,10,10));
	this.turnof = new Label("Spieler 1 ist am Zug");
	this.bottomBox.getChildren().add(turnof);
	this.pointsof = new Label("Punkte von Spieler 1: 0");
	this.bottomBox.getChildren().add(pointsof);
	this.dealCard = new Label("Spiel Karte:");
	this.bottomBox.getChildren().add(dealCard);
	this.txtDealCard = new TextField();
	this.bottomBox.getChildren().add(txtDealCard);
	this.deal = new Button ("Deal");
	this.bottomBox.getChildren().add(deal);
	
	
	
	
	Scene scene = new Scene(this,1000,800);
	stage.setScene(scene);
	stage.show();
}
}
