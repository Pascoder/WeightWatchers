package client;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.ServiceLocator;

public class Login_View extends BorderPane {
    
    private GridPane centerPane;
    private TextField usernameField, registerField;
    private PasswordField passwordField;
    private Button loginButton, registerButton;
    private Stage stage;
    private ClientModel model;
    private Label registerLabel, usernameLabel, passwordLabel, lblTitle;
    private Menu_Login_View MenuLgVi;
    private ControlBar_Login_View controls;
    

    private Translator_JC t;
    private ServiceLocator_JC sl;

    public Login_View(Stage stage, ClientModel model) {
	this.stage = stage;
	this.model = model;
	this.sl = ServiceLocator_JC.getServiceLocator();
	this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
	
	
	this.centerPane = new GridPane();
	this.centerPane.getStyleClass().add("login_view");
	this.lblTitle = new Label();
	this.centerPane.add(this.lblTitle, 3, 0);

	this.MenuLgVi = new Menu_Login_View();
	this.setTop(MenuLgVi);

	this.usernameLabel = new Label();
	this.centerPane.add(usernameLabel, 2, 6);
	this.usernameField = new TextField();
	this.centerPane.add(this.usernameField, 3, 6);

	this.passwordLabel = new Label();
	this.centerPane.add(passwordLabel, 2, 8);
	this.passwordField = new PasswordField();
	this.centerPane.add(this.passwordField, 3, 8);

	this.loginButton = new Button();
	this.centerPane.add(loginButton, 3, 10);

	registerLabel = new Label();
	this.centerPane.add(registerLabel, 3, 11);

	this.registerButton = new Button();
	this.centerPane.add(registerButton, 3, 12);


	this.centerPane.setAlignment(Pos.CENTER);
	this.centerPane.setHgap(20);
	this.centerPane.setVgap(10);
	this.setCenter(centerPane);
	
	this.controls = new ControlBar_Login_View();
	this.setBottom(controls);
	
	
	setTexts();

	Scene scene = new Scene(this, 850, 600);
	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	this.stage.setScene(scene);
	this.stage.show();
    }


    protected void setTexts() {
	this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
	this.stage.setTitle(t.getString("login.windowTitle"));
	this.MenuLgVi.setTexts();
	this.controls.setTexts();
	System.out.println("updateLabels in LoginView auferufen");
	this.lblTitle.setText(t.getString("login.lblTitle"));
	this.usernameLabel.setText(t.getString("login.lblUsername"));
	this.passwordLabel.setText(t.getString("login.lblPassword"));
	this.loginButton.setText(t.getString("login.btnLogin"));
	this.registerLabel.setText(t.getString("login.lblNotRegistert"));
	this.registerButton.setText(t.getString("login.btnRegister"));
    }

    public ControlBar_Login_View getControls() {
        return controls;
    }
    
    public TextField getUsernameField() {
	return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
	this.usernameField = usernameField;
    }

    public PasswordField getPasswordField() {
	return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
	this.passwordField = passwordField;
    }

    public Button getLoginButton() {
	return loginButton;
    }

    public void setLoginButton(Button loginButton) {
	this.loginButton = loginButton;
    }

    public Button getRegisterButton() {
	return registerButton;
    }

    public void setRegisterButton(Button registerButton) {
	this.registerButton = registerButton;
    }

    public TextField getRegisterField() {
	return registerField;
    }

    public void setRegisterField(TextField registerField) {
	this.registerField = registerField;
    }

    public Label getRegisterLabel() {
	return registerLabel;
    }

    public void setRegisterLabel(Label registerLabel) {
	this.registerLabel = registerLabel;
    }

}
