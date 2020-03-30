package client;

import chatroom.olmoClient.ServiceLocator;
import chatroom.olmoClient.Translator;
import chatroom.olmoClient.Model.Basic_Model;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ClientView<M extends Basic_Model> {

    public Stage stage;
    protected Scene scene;
    protected M model;
    protected ServiceLocator_JC sl;
    protected Translator_JC t;

    /**
     * Set any options for the stage in the subclass constructor
     * 
     * @param stage
     * @param model
     */
    protected ClientView(Stage stage, M model) {
	this.stage = stage;
	this.model = model;
	sl = ServiceLocator_JC.getServiceLocator();
	t = ServiceLocator_JC.getServiceLocator().getTranslator();

	scene = create_GUI(); // Create all controls within "root"
	stage.setScene(scene);
    }

    protected abstract Scene create_GUI();

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
