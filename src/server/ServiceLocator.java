package server;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


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
