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
	
	this.btnLeaveGame = new Button(t.getString("game.btnLeaveGame")); 
	btnLeaveGame.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	
	toolLeft.getItems().addAll(btnLeaveGame);
	
	
    }

}
