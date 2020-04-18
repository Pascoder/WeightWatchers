package client;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerBox1 extends VBox{

	
	
    private Translator_JC t;
    private ServiceLocator_JC sl;
	
	public PlayerBox1() {
		super();
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		
		VBox playerBox = new VBox();
		playerBox.getChildren().addAll();
	}

}
