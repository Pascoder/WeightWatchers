package client;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControlBar_Lobby_View extends ControlBar_Basic_View{
    
    public Button btnCancel;
    public Button btnSend;
    public TextField txtMsg;
    
    public Button btnLeave;
    
    
    public ControlBar_Lobby_View() {
	super();
	
	btnCancel = new Button(t.getString("start.btnCancel"));
	btnSend = new Button(t.getString("chatroom.btnSend"));
	txtMsg = new TextField();
	btnLeave = new Button(t.getString("chatroom.btnLeave"));
	btnCancel.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	
	toolLeft.getItems().addAll(txtMsg, btnSend, btnLeave, btnCancel);
	
	
    }

}
