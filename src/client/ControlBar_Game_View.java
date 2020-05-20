package client;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControlBar_Game_View extends ControlBar_Basic_View{
    
    public Button btnLeaveGame; 
	private Translator_JC t;
	private ServiceLocator_JC sl;
   
    public ControlBar_Game_View() {
	super();
	
	this.sl = ServiceLocator_JC.getServiceLocator();
	this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
	
	this.btnLeaveGame = new Button(); 
	btnLeaveGame.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	
	toolLeft.getItems().addAll(btnLeaveGame);
	
	
    }
    protected void setTexts() {
   	setTextsBasic();
   
   	this.btnLeaveGame.setText(t.getString("game.btnLeaveGame"));
       }

}
