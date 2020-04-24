package client;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClientView {

   
    protected Scene scene;
    protected ClientModel clientModel;
    protected ServiceLocator_JC sl;
    protected Translator_JC t;
    
    private Login_View loginView;
    private Lobby_View lobbyView;
    private Game_View gameView;
    private Stage loginStage, lobbyStage, gameStage;
    /**
     * Set any options for the stage in the subclass constructor
     * 
     * @param stage
     * @param model
     */
    public ClientView(Stage stage, ClientModel clientModel) {
	this.loginStage = stage;
	lobbyStage = new Stage();
	gameStage = new Stage();
	this.clientModel = clientModel;
	sl = ServiceLocator_JC.getServiceLocator();
	t = ServiceLocator_JC.getServiceLocator().getTranslator();
	this.lobbyView = new Lobby_View(lobbyStage, clientModel);
	this.loginView = new Login_View(loginStage, clientModel);
//	this.gameView = new Game_View(gameStage, clientModel);
	switchView(1);
	
    }

   public void switchView (int viewNumber) {
	   if(viewNumber == 1) {
		   //LoginView
		   loginStage.show();
	   }else {
		if(viewNumber == 2) {
			//LobbyView
			lobbyStage.show();
			loginStage.hide();
		
		}else {
		//GameView
			gameStage.show();
			
			}
	   }
   }
	
   
	/**
     * Display the view
     */
    public void start() {
    	loginStage.show();
    }

    /**
     * Hide the view
     */
    public void stop() {
    	loginStage.hide();
    }

    /**
     * Getter for the stage, so that the controller can access window events
     */
    

	public Login_View getLoginView() {
		return loginView;
	}
	
	public Lobby_View getLobbyView() {
		return lobbyView;
	}
	
	public Game_View getGameView() {
		return gameView;
	}

	public Stage getLoginStage() {
		return loginStage;
	}

	public void setLoginStage(Stage loginStage) {
		this.loginStage = loginStage;
	}

	public Stage getLobbyStage() {
		return lobbyStage;
	}

	public void setLobbyStage(Stage lobbyStage) {
		this.lobbyStage = lobbyStage;
	}

	public Stage getGameStage() {
		return gameStage;
	}

	public void setGameStage(Stage gameStage) {
		this.gameStage = gameStage;
	}

	
		   

}

