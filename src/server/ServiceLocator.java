package server;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Configuration;

public class ServiceLocator {
    private static ServiceLocator serviceLocator; // singleton

    // Resources
    private Logger logger;
    private Configuration configuration;

    public static ServiceLocator getServiceLocator() {
	if (serviceLocator == null)
	    serviceLocator = new ServiceLocator();
	return serviceLocator;
    }

    private ServiceLocator() {
    }

    //
    
    private Logger configureLogger() {
	Logger rootLogger = Logger.getLogger("");
    rootLogger.setLevel(Level.FINEST);
    // By default there is one handler: the console
    Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
    defaultHandlers[0].setLevel(Level.INFO);
    // Add our logger
    Logger ourLogger = Logger.getLogger("ServerLogger");
    ourLogger.setLevel(Level.FINEST);
    // Add a file handler, putting the rotating files in the tmp directory
    try {
    Handler logHandler = new FileHandler("%t/"+"ServerLogger"+ "_%u" + "_%g" + ".log",1000000, 9);
    logHandler.setLevel(Level.FINEST);
    ourLogger.addHandler(logHandler);
    } catch (Exception e) { // If we are unable to create log files
    throw new RuntimeException("Unable to initialize log files: "+ e.toString());
        }
    return ourLogger;
}
    
    public Logger getLogger() {
	return logger;
    }

    public void setLogger(Logger logger) {
	this.logger = logger;
    }

    public Configuration getConfiguration() {
	return configuration;
    }

    public void setConfiguration(Configuration configuration) {
	this.configuration = configuration;
    }

}
