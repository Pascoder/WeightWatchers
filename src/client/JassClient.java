package client;


import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

//Klasse von Frank Mauchle

public class JassClient extends Application {
	private static JassClient mainProgram; // singleton
	private ClientModel clientModel;
	private ClientView clientView;
	private ClientController clientController;
	private static ServiceLocator_JC serviceLocator;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	// MainProgram als Singleton festlegen
	public void init() {
		if (mainProgram == null) {
			mainProgram = this;
		} else {
			Platform.exit();
		}
	}

	@Override
	public void start(Stage stage) {
		// Initlialize
		initialize();
		this.clientModel = new ClientModel();
		this.clientView = new ClientView(stage, clientModel);
		this.clientController = new ClientController(clientModel, clientView);

		clientView.start();
	}

	// SL, Translator erstellen durch Configuration (JassClient.cfg) Datei
	public static void initialize() {
		serviceLocator = ServiceLocator_JC.getServiceLocator();
		serviceLocator.setLogger(configureLogger());
		serviceLocator.setConfiguration(new Configuration_JC());
		String language = serviceLocator.getConfiguration().getOption("Language");
		serviceLocator.setTranslator(new Translator_JC(language));

	}

	// Logger konfigurieren
	private static Logger configureLogger() {
		Logger rootLogger = Logger.getLogger("");
		rootLogger.setLevel(Level.FINEST);

		Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
		defaultHandlers[0].setLevel(Level.INFO);

		Logger ourLogger = Logger.getLogger(serviceLocator.getAPP_NAME());
		ourLogger.setLevel(Level.FINEST);

		return ourLogger;
	}

	// Beim Aufruffen von dieser Methode wird Konfiguration gespeichert
	public void stop() {
		serviceLocator.getConfiguration().save();
		if (clientView != null) {
			clientView.stop();
		}
		serviceLocator.getLogger().info("Application terminated");
	}

	// Getter für den JassClient
	protected static JassClient getMainProgram() {
		return mainProgram;
	}

}
