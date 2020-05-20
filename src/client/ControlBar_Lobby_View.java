package client;

import javafx.scene.control.Button;


public class ControlBar_Lobby_View extends ControlBar_Basic_View{
    
   
    private Button btnLeave;
    
    
    public ControlBar_Lobby_View() {
	super();
	

	btnLeave = new Button(t.getString("lobby.btnLeave"));

	
	toolLeft.getItems().addAll(btnLeave);
	
	
    }
    
    protected void setTexts() {
   	setTextsBasic();
   	
   	this.btnLeave.setText(t.getString("lobby.btnLeaveLobby"));

    }
    public Button getLeaveLobbyButton() {
	return btnLeave;
    }
    

}
