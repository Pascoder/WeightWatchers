package client;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClientView {

    public Stage stage;
    protected Scene scene;
    protected ClientModel clientModel;
    protected ServiceLocator_JC sl;
    protected Translator_JC t;

    /**
     * Set any options for the stage in the subclass constructor
     * 
     * @param stage
     * @param model
     */
    public ClientView(Stage stage, ClientModel clientModel) {
	this.stage = stage;
	this.clientModel = clientModel;
	sl = ServiceLocator_JC.getServiceLocator();
	t = ServiceLocator_JC.getServiceLocator().getTranslator();
	// Leon ab hier kommt die Gestaltung der 
	switchView(1);
	
	 

	
    }

   public void switchView (int viewNumber) {
	   if(viewNumber == 1) {
		   //LoginView
		   Login_View view = new Login_View(this.stage, this.clientModel);
	   }else {
		if(viewNumber == 2) {
			//LobbyView
			//Lobby_View lobbyview = new Lobby_View(this.stage, this.clientModel);
		}else {
		//GameView
			//Game_View gameView = new Game_View(this.stage, this.clientModel);		}
		}
	   }
   }

  


	/**
     * Display the view
     */
    public void start() {
	stage.show();
    }

    /**
     * Hide the view
     */
    public void stop() {
	stage.hide();
    }

    /**
     * Getter for the stage, so that the controller can access window events
     */
    public Stage getStage() {
	return stage;
    }


}
