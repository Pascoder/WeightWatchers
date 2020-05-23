package client;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

//Klasse von Leon Xhinovci

public class ControlBar_Basic_View extends HBox {
    protected ServiceLocator_JC sl;
    protected Translator_JC t;


    ToolBar toolLeft;
    ToolBar toolRight;

    private Region spacer = new Region(); // Empty spacer

    final int SPACING = 2;

    private Button btnQuit;

    protected ControlBar_Basic_View() {
	super();
	t = ServiceLocator_JC.getServiceLocator().getTranslator();
	btnQuit = new Button();
	
	this.setSpacing(SPACING);
	HBox.setHgrow(spacer, Priority.ALWAYS);

	btnQuit.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	
	toolLeft = new ToolBar();
	toolRight = new ToolBar();

	toolRight.getItems().add(btnQuit);

	this.getChildren().addAll(toolLeft, spacer, toolRight);
	this.toolLeft.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	this.toolLeft.getStyleClass().add("control_bar");
	this.toolRight.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	this.toolRight.getStyleClass().add("control_bar");
	this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	this.getStyleClass().add("control_bar");

    }
    protected void setTextsBasic() {
	this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
	btnQuit.setText(t.getString("basic.btnQuit"));
    }
    
    public Button getQuitButton() {
   	return btnQuit;
       }
       

}
