package client;

import chatroom.olmoClient.Model.Basic_Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrivateChat_View extends Basic_View {

    
    ListView messageList;

    

    public PrivateChat_View(Stage stage, Basic_Model model) { // Model model
	super(stage, model);
	this.model = model;

//	lblName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
//	btnSignIn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
//	txtName.setMinWidth(150);
//	txtName.setPrefWidth(150);

    }

    @Override
    protected Scene create_GUI() {
	

	// Center: actual Chatroom Messages
	messageList = new ListView();
	


	// ControlBar
	ControlBar_PrivateChat_View controls = new ControlBar_PrivateChat_View();
	controls.getStyleClass().add("controls");
	controls.setId("controls");

	// BorderPane
	BorderPane root = new BorderPane();
	root.getStyleClass().add("root"); // Class for styling
	

	root.setCenter(messageList);

	root.setBottom(controls);

	Scene scene = new Scene(root);
	stage.setScene(scene);

	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	stage.setTitle("Private Chat");

	return scene;
    }
}
