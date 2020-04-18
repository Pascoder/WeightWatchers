package client;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerBox3 {
private Label player;
	
    private Translator_JC t;
    private ServiceLocator_JC sl;
	
	public PlayerBox3() {
		
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		this.player = new Label(t.getString("game.lblPlayer3"));
		
		VBox playerBox = new VBox();
		playerBox.getChildren().addAll(this.player, this.player);
	}

}
