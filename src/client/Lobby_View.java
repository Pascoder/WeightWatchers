package client;

import chatroom.olmoClient.ServiceLocator;
import chatroom.olmoClient.Model.Chatroom_Model;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

    public Lobby_View(Stage stage, ClientModel model ) {
		this.stage = stage;
		this.model = model;
//    Menu_Chatroom_View chatroomMenu;
//    public ControlBar_Chatroom_View controls;
//    public Label lblMainRoom;
//    public Label lblMainRoom1;
//   
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
    
	// LeftTop: Chatrooms	
	lblGames = new Label(t.getString(""));
	gamesList = new ListView<String>();
	gamesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
	VBox gamesBox = new VBox();
	gamesBox.getChildren().addAll(lblGames, gamesList);


	// LeftBottom: actual Chatroom Messages
	lblChat = new Label(t.getString(""));
	chatList = new ListView<String>();
	
	VBox chatBox = new VBox();
	chatBox.getChildren().addAll(lblChat, chatList);
	
	
	// RightTop: Chatrom Members
	lblPlayerOn = new Label(t.getString(""));
	playerOnList = new ListView<String>();
	playerOnList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
	VBox chatterBox = new VBox();
	chatterBox.getChildren().addAll(lblChatter, chatterList);

	// Menu
	chatroomMenu = new Menu_Chatroom_View();

	// ControlBar
	controls = new ControlBar_Chatroom_View();
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
	root.setTop(chatroomMenu);
	root.setLeft(roomBox);
	root.setCenter(msgBox);
	root.setRight(chatterBox);
	root.setBottom(footerBox);

	Scene scene = new Scene(root);
	stage.setScene(scene);

	//scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	stage.setTitle(t.getString("program.name"));
    
	return scene;
    }
    }
}
