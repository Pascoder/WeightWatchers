package server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

//Pascal Wyser

public class ServerMain {
    private static int client_id = 0;
    private final static int port = 9998;
    private static ServerModel model;
    private static ServiceLocator sl;
    /*
     * wartet bis sich ein Client verbindet um dann einen Socket abzuspalten und
     * diesen dem ClientThread mitzugeben
     * 
     */

    public static void main(String[] args) throws IOException {
	sl = ServiceLocator.getServiceLocator();
	sl.setLogger(configureLogger());
	sl.setConfiguration(new Configuration());
	model = new ServerModel();

	sl.getLogger().info("Server gestartet");

	try (ServerSocket serverSocket = new ServerSocket(port, 10, null)) {

	    while (true) {
		Socket socket = serverSocket.accept();
		client_id++;

		ClientThread ct = new ClientThread(socket);
		ct.start();
		sl.getLogger().info("Client-Socket erstellt");

	    }

	} catch (Exception e) {
	    System.err.println(e.getMessage());
	}

    }
    
    public void stop() {
	sl.getConfiguration().save();
	
    }
    
    private static Logger configureLogger() {
		Logger rootLogger = Logger.getLogger("");
		rootLogger.setLevel(Level.FINEST);
		// By default there is one handler: the console
		Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
		defaultHandlers[0].setLevel(Level.INFO);
		// Add our logger
		Logger ourLogger = Logger.getLogger(sl.getAPP_NAME());
		ourLogger.setLevel(Level.INFO);
		// Add a file handler, putting the rotating files in the tmp directory
		try {
		    Handler logHandler = new FileHandler("%t/" + sl.getAPP_NAME() + "_%u" + "_%g" + ".log", 1000000,
			    9);
		    logHandler.setLevel(Level.INFO);
		    ourLogger.addHandler(logHandler);
		} catch (Exception e) { // If we are unable to create log files
		    throw new RuntimeException("Unable to initialize log files: " + e.toString());
		}
		return ourLogger;
    }

}
