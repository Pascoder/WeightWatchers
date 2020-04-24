package client;

import client.ServiceLocator_JC;
import client.Translator_JC;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class ControlBar_Basic_View extends HBox {
    protected ServiceLocator_JC sl;
    protected Translator_JC t;


    ToolBar toolLeft;
    ToolBar toolRight;

    private Region spacer = new Region(); // Empty spacer

    final int SPACING = 2;

    public Button btnQuit;

    protected ControlBar_Basic_View() {
	super();
	t = ServiceLocator_JC.getServiceLocator().getTranslator();
	btnQuit = new Button(t.getString("basic.btnQuit"));

	this.getStyleClass().add("controlArea");
	this.setId("controlArea");

	this.setSpacing(SPACING);
	HBox.setHgrow(spacer, Priority.ALWAYS);

	btnQuit.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	
	toolLeft = new ToolBar();
	toolRight = new ToolBar();

//	toolRight.getItems().add(btnQuit);

	this.getChildren().addAll(toolLeft, spacer);

    }

}
