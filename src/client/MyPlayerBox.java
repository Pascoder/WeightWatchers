package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MyPlayerBox {

	private Image im1, im2, im3, im4, im5, im6, im7, im8, im9;
	private ToggleButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9; 
	private Label lbl;
	private Translator_JC t;
	private ServiceLocator_JC sl;
	
	public MyPlayerBox() {
		
		this.sl = ServiceLocator_JC.getServiceLocator();
		this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
		
		this.im1 = new Image(this.getClass().getClassLoader().getResourceAsStream("scr/"+ "card_images.deutsch"));
		ImageView togIm1 = new ImageView(this.im1);
		this.bt1 = new ToggleButton();
		bt1.setGraphic(togIm1);
		this.lbl = new Label(t.getString("game.lblMyPlayer"));
		
		VBox myCardsBox = new VBox();
		myCardsBox.getChildren().addAll(bt1, this.lbl);
		
	}
}
