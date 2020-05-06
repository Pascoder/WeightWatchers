package client;



import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class JassClient extends Application {
    private static JassClient mainProgram; // singleton
	private ClientModel clientModel;
	private ClientView clientView;
	private ClientController clientController;
	private ServiceLocator_JC serviceLocator;

    // resources, after initializationff

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        if (mainProgram == null) {
            mainProgram = this;
        } else {
            Platform.exit();
        }
    }

    @Override
    public void start(Stage stage) {
        // Create and display the splash screen and model
    	initialize();
		this.clientModel = new ClientModel();
		this.clientView = new ClientView(stage, clientModel);
		this.clientController = new ClientController(clientModel, clientView);
		//nicht beachten, zu Testzwecken
//		this.tc = new Test_Controller(clientModel, tv);
		//Test fertig
		
		clientView.start();

        // Display the splash screen and begin the initialization
        
        
    }
    public void initialize() {
		this.serviceLocator = serviceLocator.getServiceLocator();
		serviceLocator.setLogger(configureLogger());
		serviceLocator.setConfiguration(new Configuration_JC());
		String language = serviceLocator.getConfiguration().getOption("Language");
		serviceLocator.setTranslator(new Translator_JC(language));
		
		
		
		//Hier fehlt noch Verbindung zu Server herstellen
	}

    private Logger configureLogger() {
		Logger rootLogger = Logger.getLogger("");
	    rootLogger.setLevel(Level.FINEST);
	    // By default there is one handler: the console
	    Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
	    defaultHandlers[0].setLevel(Level.INFO);
	    // Add our logger
	    Logger ourLogger = Logger.getLogger(serviceLocator.getAPP_NAME());
	    ourLogger.setLevel(Level.FINEST);
	    // Add a file handler, putting the rotating files in the tmp directory
	    try {
	    Handler logHandler = new FileHandler("%t/"+ serviceLocator.getAPP_NAME() + "_%u" + "_%g" + ".log",1000000, 9);
	    logHandler.setLevel(Level.FINEST);
	    ourLogger.addHandler(logHandler);
	    } catch (Exception e) { // If we are unable to create log files
	    throw new RuntimeException("Unable to initialize log files: "+ e.toString());
	        }
	    return ourLogger;
	}

    
    @Override
    public void stop() {
        serviceLocator.getConfiguration().save();
        if (clientView != null) {
            // Make the view invisible
            clientView.stop();
        }

        // More cleanup code as needed
        serviceLocator.getLogger().info("Application terminated");
    }

    // Static getter for a reference to the main program object
    protected static JassClient getMainProgram() {
        return mainProgram;
    }
    
    public static void changeLocales() {
	/*view.stop();
	Stage appStage = new Stage();
        Start_Model model = new Start_Model();
        view = new Start_View(appStage, model);
        new Start_Controller(model, view);
        view.start();*/
    }
}
