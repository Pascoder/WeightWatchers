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

public class Lobby_View extends ClientView<Chatroom_Model> {

    private Label lblRoom;
    public ListView<String> roomList;
    private Label lblMsg;
    public ListView<String> messageList;
    private Label lblChatter;
    public ListView<String> chatterList;

    
    Menu_Chatroom_View chatroomMenu;
    public ControlBar_Chatroom_View controls;
    public Label lblMainRoom;
    public Label lblMainRoom1;
   
    

    public Lobby_View(Stage stage, Chatroom_Model model) {
	super(stage, model);
	sl.getLogger().info("Chatroom_View initialized");

    }
    

    public void setRoom(ObservableList<String> rooms) {	
	roomList.setItems(rooms);
	}
    public void setMessages(ObservableList<String> messages) {	
	messageList.setItems(messages);
	}
    public void setChatter(ObservableList<String> chatter) {	
	chatterList.setItems(chatter);
	}

    @Override
    protected Scene create_GUI() {
	t = ServiceLocator_JC.getServiceLocator().getTranslator();
	sl = ServiceLocator_JC.getServiceLocator();
	
	// Left: Chatrooms	
	lblRoom = new Label(t.getString("chatroom.lblRooms"));
	roomList = new ListView<String>();
	roomList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
	VBox roomBox = new VBox();
	roomBox.getChildren().addAll(lblRoom, roomList);


	// Center: actual Chatroom Messages
	lblMsg = new Label(t.getString("chatroom.lblMsg"));
	messageList = new ListView<String>();
	

	VBox msgBox = new VBox();
	msgBox.getChildren().addAll(lblMsg, messageList);
	
	// Right: Chatrom Members
	lblChatter = new Label(t.getString("chatroom.lblChatter"));
	chatterList = new ListView<String>();
	roomList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
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
	lblMainRoom1 = new Label(t.getString("chatroom.lblMainRoom1"));
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
