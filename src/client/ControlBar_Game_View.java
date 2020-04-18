package client;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControlBar_Game_View extends ControlBar_Basic_View{
    
    Button btnCancel = new Button("Cancel");
    Button btnSend = new Button("Send");
    TextField txtMsg = new TextField();
    
    
    public ControlBar_Game_View() {
	super();
	
	btnCancel.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	
	toolLeft.getItems().addAll(txtMsg, btnSend, btnCancel);
	
	
    }

}
