package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game_View_Test extends Application{
	ClientModel model;
	Game_View view;
	

	public static void main(String[] args) {
		launch(args);

	}
	public void start(Stage primary) {
		
		this.view = new Game_View(primary, model);
		primary.show();
	}
	

}
