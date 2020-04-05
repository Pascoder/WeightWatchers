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
	createGUI(); 
	
	
	
    }

   

    private void createGUI() {
    	//Nur für Testzwecke ob MVC funktioniert
    	GridPane root = new GridPane();
    	Label l1 = new Label("test");
    	root.add(l1, 0, 0);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
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
