package client;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lobby_View{

    private Label lblGames;
    //public TextArea gamesList;
    public ListView<String> gamesList;
    private Label lblChat;
    public TextArea chatList;
    private Label lblPlayerOn;
   // public ListView<String> playerOnList;
    public TextArea playerOnList;
    private Label lblSelectedGame;
    public TextArea selectedGameList;
    private Stage stage;
	private ClientModel model;
    public Menu_Lobby_View lobbyMenu; 
    public ControlBar_Lobby_View controls;
    public Label lblChatOptions;
    private Translator_JC t;
    private ServiceLocator_JC sl;

    
    private TextField txtGameName;
    private Button createBt, leaveBt, leaveLobbyBt, joingame; 
    private TextField txt;

    
    

    public Lobby_View(Stage stage, ClientModel model ) {
		this.stage = stage;
		this.model = model;
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		
		// LeftTop: Actual Games	
		lblGames = new Label(t.getString("lobby.blbGame"));
		//gamesList = new TextArea();
		
		
		gamesList = new ListView<String>();
		gamesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		//ScrollPane gameScroll = new ScrollPane();
		//HBox scrollBox = new HBox();
		//scrollBox.getChildren().add(gamesList);
		
		this.createBt = new Button(t.getString("lobby.btnCreate"));
		
		this.txtGameName = new TextField();
		this.joingame = new Button("Join Game");
		VBox gamesBox = new VBox();
		gamesBox.getChildren().addAll(lblGames, gamesList,createBt,joingame,txtGameName);
		
		gamesBox.setPadding(new Insets(15, 12, 15, 12));
		gamesBox.setSpacing(10);
	
		


		// LeftBottom: Chatroom
		lblChat = new Label(t.getString("lobby.blbChat"));
		chatList = new TextArea();
		//chatList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		ScrollPane scrollChat = new ScrollPane();
		HBox scrollBox2 = new HBox();
		scrollBox2.getChildren().add(chatList);
		
		// ControlBar
		controls = new ControlBar_Lobby_View();
		controls.getStyleClass().add("controls");
		controls.setId("controls");
		VBox footerBox = new VBox(); 
		HBox labelBox = new HBox();
		lblChatOptions = new Label(t.getString("lobby.lblChatOptions"));
		labelBox.getChildren().addAll(lblChatOptions);
		footerBox.getChildren().addAll(labelBox, controls);
		
		this.leaveLobbyBt = new Button(t.getString("lobby.btnLeaveLobby"));
		
		
		VBox chatBox = new VBox();
		chatBox.getChildren().addAll(lblChat, scrollBox2, footerBox, this.leaveLobbyBt );
		chatBox.setPadding(new Insets(15, 12, 15, 12));
		chatBox.setSpacing(10);
		
		//Left Box
		VBox leftBox = new VBox(gamesBox, chatBox);
		leftBox.setPadding(new Insets(15, 12, 15, 12));
	    leftBox.setSpacing(10);
		
		// RightTop: Player online
		lblPlayerOn = new Label(t.getString("lobby.blbPlayerOn"));
		playerOnList = new TextArea();
		//playerOnList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		ScrollPane gameScroll3 = new ScrollPane();
		HBox scrollBox3 = new HBox();
		scrollBox3.getChildren().add(playerOnList);
		
		VBox playerOnBox = new VBox();
		playerOnBox.getChildren().addAll(lblPlayerOn, scrollBox3);
		playerOnBox.setPadding(new Insets(15, 12, 15, 12));
		playerOnBox.setSpacing(10);
		
		
		//RightBottom 
		
		lblSelectedGame = new Label(t.getString("lobby.blbSelectedGame"));
		selectedGameList = new TextArea();
		//selectedGameList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		ScrollPane gameScroll4 = new ScrollPane();
		HBox scrollBox4 = new HBox();
		
		scrollBox4.getChildren().add(selectedGameList);
		
		this.leaveBt = new Button(t.getString("lobby.btnLeaveGame"));
		
				
		VBox selectedGameBox = new VBox();
		selectedGameBox.getChildren().addAll(lblSelectedGame, scrollBox4, this.leaveBt);
		selectedGameBox.setPadding(new Insets(15, 12, 15, 12));
		selectedGameBox.setSpacing(10);
		
		// Right Box
		VBox rightBox = new VBox (playerOnBox, selectedGameBox);
		rightBox.setPadding(new Insets(15, 12, 15, 12));
	    rightBox.setSpacing(10);;

		// TOP --> Menu
		this.lobbyMenu = new Menu_Lobby_View();
		

	
		// BorderPane
		BorderPane root = new BorderPane();
		root.getStyleClass().add("root"); // Class for styling
		root.setTop(this.lobbyMenu);
		root.setLeft(leftBox);
		root.setRight(rightBox);
		
		
		Scene scene = new Scene(root, 1050, 650);
		stage.setScene(scene);

		//scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		this.stage.setTitle("Lobby Jass Game");
	    
    }

    public void setGames(ObservableList<String> games) {
	gamesList.setItems(games);
    }
    
	
    public void setChat(ObservableList<String> chat) {	
	//chatList.setItems(chat);
	}
    public void setPlayerOn(ObservableList<String> playerOn) {	
    
    	//playerOnList.setItems(playerOn);
    
    
	}
    public void setSelectedGame(ObservableList<String> selectedGame) {	
    //selectedGameList.setItems(selectedGame);
    }
    
    public Button getJoinButton() {
    	return this.joingame;
    }
    
    
    public Button getCreateGameButton() {
    	return this.createBt;
    }
    
    public TextField getTextField() {
    	return this.txtGameName;
    }


 
    
	
}

