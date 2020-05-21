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

public class Lobby_View {

    public ListView<String> gamesList;
    public ListView<String> playerOnList;
    public ListView<String> selectedGameList;
    private Label lblChat, lblGames, lblPlayerOn, lblSelectedGame, lblChatOptions;
    public TextArea chatList;
    private TextField txtGameName, txt, txtMsg;
    private Button createBt, leaveBt, btnSend;

    private Stage stage;
    private ClientModel model;
    private Menu_Lobby_View lobbyMenu;
    private ControlBar_Lobby_View controls;

    private Translator_JC t;
    private ServiceLocator_JC sl;

    public Lobby_View(Stage stage, ClientModel model) {
	this.stage = stage;
	this.model = model;
	this.sl = ServiceLocator_JC.getServiceLocator();
	this.t = ServiceLocator_JC.getServiceLocator().getTranslator();

	// LeftTop: Actual Games
	lblGames = new Label();
	gamesList = new ListView<String>();
	gamesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	this.createBt = new Button();

	this.txtGameName = new TextField();
	VBox gamesBox = new VBox();
	gamesBox.getChildren().addAll(lblGames, gamesList, createBt, txtGameName);

	gamesBox.setPadding(new Insets(15, 12, 15, 12));
	gamesBox.setSpacing(10);


	
	// LeftBottom: Chatroom

	lblChat = new Label();
	chatList = new TextArea();
	chatList.setEditable(false);
	lblChatOptions = new Label();
	btnSend = new Button();
	txtMsg = new TextField();
	
	HBox labelBox = new HBox(txtMsg, btnSend);
	// chatList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

//	ScrollPane scrollChat = new ScrollPane();

	HBox scrollBox2 = new HBox(chatList);
	VBox chatBox = new VBox(lblChat, scrollBox2, labelBox);


	
	// Left Box
		VBox leftBox = new VBox(gamesBox, chatBox);
		leftBox.getStyleClass().add("lobby_view");
		leftBox.setPadding(new Insets(15, 12, 15, 12));
		leftBox.setSpacing(10);	
	

	// ControlBar Bottom
	controls = new ControlBar_Lobby_View();
	controls.getStyleClass().add("controls");
	controls.setId("controls");


	chatBox.setPadding(new Insets(15, 12, 15, 12));
	chatBox.setSpacing(10);

	

	// RightTop: Player online
	lblPlayerOn = new Label();
	playerOnList = new ListView<String>();
	// playerOnList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


	VBox playerOnBox = new VBox();
	playerOnBox.getChildren().addAll(lblPlayerOn, playerOnList);
//	playerOnBox.setPadding(new Insets(15, 12, 15, 12));
//	playerOnBox.setSpacing(10);

	// RightBottom

	lblSelectedGame = new Label();
	selectedGameList = new ListView<String>();
	selectedGameList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


	this.leaveBt = new Button();

	VBox selectedGameBox = new VBox();
	selectedGameBox.getChildren().addAll(lblSelectedGame, selectedGameList, this.leaveBt);
	selectedGameBox.setPadding(new Insets(15, 12, 15, 12));
	selectedGameBox.setSpacing(10);

	// Right Box
	VBox rightBox = new VBox(playerOnBox, selectedGameBox);
	rightBox.getStyleClass().add("lobby_view");
	rightBox.setPadding(new Insets(15, 12, 15, 12));
	rightBox.setSpacing(10);
	;

	// TOP --> Menu
	this.lobbyMenu = new Menu_Lobby_View();

	// BorderPane
	BorderPane root = new BorderPane();
	root.setTop(this.lobbyMenu);
	root.setLeft(leftBox);
	root.setRight(rightBox);
	root.setBottom(controls);


	setTexts();

	Scene scene = new Scene(root, 1050, 650);
	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	stage.setScene(scene);

    }

    protected void setTexts() {
	this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
	this.stage.setTitle(t.getString("lobby.windowTitle"));
	this.lobbyMenu.setTexts();
	this.controls.setTexts();
	this.lblGames.setText(t.getString("lobby.lblGame"));
	this.stage.setTitle(t.getString("login.windowTitle"));
	this.createBt.setText(t.getString("lobby.btnCreate"));
	this.lblChat.setText(t.getString("lobby.blbChat"));
	this.lblChatOptions.setText(t.getString("lobby.lblChatOptions"));
	this.btnSend.setText(t.getString("lobby.btnSend"));
	this.lblPlayerOn.setText(t.getString("lobby.blbPlayerOn"));
	this.lblSelectedGame.setText(t.getString("lobby.blbSelectedGame"));
	this.leaveBt.setText(t.getString("lobby.btnLeaveGame"));
    }

    public void setGames(ObservableList<String> games) {

	gamesList.setItems(games);

    }

    public void setChat(ObservableList<String> chat) {
	// chatList.setItems(chat);
    }

    public void setPlayerOn(ObservableList<String> playerOn) {
	playerOnList.setItems(playerOn);

    }

    public void setSelectedGame(ObservableList<String> selectedGame) {
	selectedGameList.setItems(selectedGame);
    }

    public ListView getSelectedGames() {
	return this.selectedGameList;
    }

    public Button getCreateGameButton() {
	return this.createBt;
    }

    public TextField getTextField() {
	return this.txtGameName;
    }

    public Button getLeaveGameButton() {
	return this.leaveBt;
    }
    public Button getSendButton() {
    	return this.btnSend;
    }
    
    public TextField getTextChat() {
    	return this.txtMsg;
    }

    public ControlBar_Lobby_View getControls() {
	return this.controls;
    }

    public TextArea getChatList() {
	return this.chatList;
    }

}
