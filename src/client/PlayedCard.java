package client;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class PlayedCard extends VBox{

	private Image im1, im2, im3, im4;
	private Translator_JC t;
	private ServiceLocator_JC sl;
	private Button testButton; // Buttons zum Testen
	
	public PlayedCard() {
		
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		this.testButton = new Button("Test Gespielte Karte");
		
	}
	
}
