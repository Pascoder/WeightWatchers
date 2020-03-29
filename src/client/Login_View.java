package client;

import chatroom.olmoClient.Model.Login_Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login_View extends Basic_View<Login_Model> {

    Label lblLogIn;
    Label lblName;
    public TextField txtName;
    Label lblPassword;
    public PasswordField txtPassword;
    public Label pruf = new Label();
    Label lblPassword2;
    public PasswordField txtPassword2;
    Label txtPassCheck;
    public Button btnSignIn;
    public Button btnNewUser;
    
    private Menu_Login_View loginMenu;
    public ControlBar_Login_View controls;


    public Login_View(Stage stage, Login_Model model) {
	super(stage, model);
	sl.getLogger().info("Login_View initialized");


	lblName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	btnSignIn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	txtName.setMinWidth(150);
	txtName.setPrefWidth(150);
	
    }

    @Override
    protected Scene create_GUI() {
	// Center Login-View
	lblLogIn = new Label(t.getString("login.lblLogIn"));
	lblName = new Label(t.getString("login.lblName"));
	txtName = new TextField();
	lblPassword = new Label(t.getString("login.lblPassword"));
	txtPassword = new PasswordField();
	lblPassword2 = new Label(t.getString("login.lblPassword2"));
	txtPassword2 = new PasswordField();
	txtPassCheck = new Label();
	btnSignIn = new Button(t.getString("login.btnSignIn"));
	//btnSignIn.setDisable(true);
	btnNewUser = new Button(t.getString("login.btnNewUser"));
	
TextField pruf = new TextField();

	// CenterBox2
	GridPane cBox2 = new GridPane();
	cBox2.setAlignment(Pos.CENTER);
	cBox2.getStyleClass().add("cBox2");
	cBox2.setId("grid-custom");
	cBox2.add(lblLogIn, 1, 1);
	cBox2.add(lblName, 1, 2);
	cBox2.add(txtName, 2, 2);
	cBox2.add(lblPassword, 1, 3);
	cBox2.add(txtPassword, 2, 3);
	cBox2.add(lblPassword2, 1, 4);
	cBox2.add(txtPassword2, 2, 4);
	cBox2.add(btnSignIn, 2, 5, 2, 1);
	cBox2.add(btnNewUser, 6, 2, 2, 1);
	cBox2.add(txtPassCheck, 6, 4);
cBox2.add(pruf, 2, 7);

	cBox2.getStyleClass().add("cbox2");
	cBox2.setId("cbox2");

	// Menu
	loginMenu = new Menu_Login_View();

	// ControlBar
	controls = new ControlBar_Login_View();
	controls.getStyleClass().add("controls");
	controls.setId("controls");

	// BorderPane
	BorderPane root = new BorderPane();
	root.getStyleClass().add("root"); // Class for styling
	root.setTop(loginMenu);
	root.setCenter(cBox2);
	root.setBottom(controls);

	Scene scene = new Scene(root);
	stage.setScene(scene);

	//scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

	stage.setTitle(t.getString("program.name"));
	
	

	return scene;
    }
    

}
