package client;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lobby_View{

    private Label lblGames;
    public ListView<String> gamesList;
    private Label lblChat;
    public ListView<String> chatList;
    private Label lblPlayerOn;
    public ListView<String> playerOnList;
    private Label lblSelectedGame;
    public ListView<String> selectedGameList;
    private Stage stage;
	private ClientModel model;
    public Menu_Lobby_View lobbyMenu; 
    public ControlBar_Lobby_View controls;
    public Label lblMainRoom;
    public Label lblMainRoom1;
    private Translator_JC t;
    private ServiceLocator_JC sl;

    public Lobby_View(Stage stage, ClientModel model ) {
		this.stage = stage;
		this.model = model;
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		
		// LeftTop: Actual Games	
		lblGames = new Label(t.getString(""));
		gamesList = new ListView<String>();
		gamesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		VBox gamesBox = new VBox();
		gamesBox.getChildren().addAll(lblGames, gamesList);


		// LeftBottom: Chatroom
		lblChat = new Label(t.getString(""));
		chatList = new ListView<String>();
		chatList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		VBox chatBox = new VBox();
		chatBox.getChildren().addAll(lblChat, chatList);
		
		//Left Box
		VBox leftBox = new VBox(gamesBox, chatBox);
		
		
		// RightTop: Player online
		lblPlayerOn = new Label(t.getString(""));
		playerOnList = new ListView<String>();
		playerOnList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		VBox playerOnBox = new VBox();
		playerOnBox.getChildren().addAll(lblPlayerOn, playerOnList);
		
		//RightBottom 
		
		lblSelectedGame = new Label(t.getString(""));
		selectedGameList = new ListView<String>();
		selectedGameList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		VBox selectedGameBox = new VBox();
		selectedGameBox.getChildren().addAll(lblSelectedGame, selectedGameList);
		
		// Right Box
		VBox rightBox = new VBox (playerOnBox,selectedGameBox);

		// Menu
		lobbyMenu = new Menu_Lobby_View();

		// ControlBar
		controls = new ControlBar_Lobby_View();
		controls.getStyleClass().add("controls");
		controls.setId("controls");
		
		VBox footerBox = new VBox();
		HBox labelBox = new HBox();
		lblMainRoom1 = new Label(t.getString("lobby.lblGames"));
		lblMainRoom = new Label("--");
		labelBox.getChildren().addAll(lblMainRoom1, lblMainRoom);
		footerBox.getChildren().addAll(labelBox, controls);
	    
		// BorderPane
		BorderPane root = new BorderPane();
		root.getStyleClass().add("root"); // Class for styling
		root.setTop(lobbyMenu);
		root.setLeft(leftBox);
		root.setRight(rightBox);
		root.setBottom(footerBox);

		Scene scene = new Scene(root);
		stage.setScene(scene);

		//scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		this.stage.setTitle("Lobby Jass Game");
	    
    }

    public void setGame(ObservableList<String> games) {	
	gamesList.setItems(games);
	}
    public void setChat(ObservableList<String> chat) {	
	chatList.setItems(chat);
	}
    public void setPlayerOn(ObservableList<String> playerOn) {	
    playerOnList.setItems(playerOn);
	}
    public void setSelectedGame(ObservableList<String> selectedGame) {	
    selectedGameList.setItems(selectedGame);
    }
    
	
}

