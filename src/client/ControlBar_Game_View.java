package client;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControlBar_Game_View extends ControlBar_Basic_View{
    
   private Button btnLeaveGame; 
   
    public ControlBar_Game_View() {
	super();
	
	
	this.btnLeaveGame = new Button(); 
	
	toolLeft.getItems().addAll(btnLeaveGame);
	
	
    }
    protected void setTexts() {
   	setTextsBasic();
   
   	this.btnLeaveGame.setText(t.getString("game.btnLeaveGame"));
       }
    public Button getLeaveGameButton() {
	return btnLeaveGame;
    }
    
}
