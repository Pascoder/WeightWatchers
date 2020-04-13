package client;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;

//TODO in der initialize Methode Klasse zur Verbindungsaufbau aufrufen

public class ClientMVC extends Application{
	private ClientModel clientModel;
	private ClientView clientView;
	private ClientController clientController;
	private ServiceLocator_JC sl;
	//nicht beachten, zu Testzwecken
//	private Test_Controller tc;
//	private Test_View tv;

	public static void main(String[] args){
		launch(args);

	}
	public void start (Stage stage) throws Exception{
		initialize();
		this.clientModel = new ClientModel();
		this.clientView = new ClientView(stage, clientModel);
		this.clientController = new ClientController(clientModel, clientView);
		//nicht beachten, zu Testzwecken
//		this.tc = new Test_Controller(clientModel, tv);
		//Test fertig
		
		clientView.start();
		
		
	}
	public void stop () {
		clientView.stop();
	}
	public void initialize() {
		this.sl = sl.getServiceLocator();
		sl.setLogger(configureLogger());
		sl.setConfiguration(new Configuration_JC());
		String language = sl.getConfiguration().getOption("Language");
		sl.setTranslator(new Translator_JC(language));
		
		//Hier fehlt noch Verbindung zu Server herstellen
	}
	private Logger configureLogger() {
		Logger rootLogger = Logger.getLogger("");
	    rootLogger.setLevel(Level.FINEST);
	    // By default there is one handler: the console
	    Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
	    defaultHandlers[0].setLevel(Level.INFO);
	    // Add our logger
	    Logger ourLogger = Logger.getLogger(sl.getAPP_NAME());
	    ourLogger.setLevel(Level.FINEST);
	    // Add a file handler, putting the rotating files in the tmp directory
	    try {
	    Handler logHandler = new FileHandler("%t/"+ sl.getAPP_NAME() + "_%u" + "_%g" + ".log",1000000, 9);
	    logHandler.setLevel(Level.FINEST);
	    ourLogger.addHandler(logHandler);
	    } catch (Exception e) { // If we are unable to create log files
	    throw new RuntimeException("Unable to initialize log files: "+ e.toString());
	        }
	    return ourLogger;
	}

}
