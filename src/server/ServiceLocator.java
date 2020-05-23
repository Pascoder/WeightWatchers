package server;

//2020 Oliver Mosimann

import java.util.logging.Logger;

public class ServiceLocator {
    private static ServiceLocator serviceLocator;
    final private Class<?> APP_CLASS = ServerMain.class;
    private static Logger logger = null;
    private Configuration configuration;
    final private String APP_NAME = "JassServer1";

    public static ServiceLocator getServiceLocator() {
	if (serviceLocator == null)
	    serviceLocator = new ServiceLocator();
	return serviceLocator;
    }

    private ServiceLocator() {
    }

    public Logger getLogger() {
	return logger;
    }

    public void setLogger(Logger logger) {
	ServiceLocator.logger = logger;
    }

    public Configuration getConfiguration() {
	return configuration;
    }

    public void setConfiguration(Configuration configuration) {
	this.configuration = configuration;
    }

    public String getAPP_NAME() {
	return APP_NAME;
    }

    public Class<?> getAPP_CLASS() {
	return APP_CLASS;
    }

}
