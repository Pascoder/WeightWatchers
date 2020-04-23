package client;

import java.util.Locale;
import java.util.logging.Logger;

import client.Configuration_JC;
import client.Translator_JC;



public class ServiceLocator_JC {
    private static ServiceLocator_JC serviceLocator; // singleton

    // Application-global constants
    final private Class<?> APP_CLASS = JassClient.class;
    final private String APP_NAME = "JassClient";
    
    // Supported locales (for translations)
    final private Locale[] locales = new Locale[] { new Locale("en"), new Locale("de") };

    // Resources
    private Logger logger;
    private Configuration_JC configuration;
    private Translator_JC translator;

 

    
    public static ServiceLocator_JC getServiceLocator() {
        if (serviceLocator == null)
            serviceLocator = new ServiceLocator_JC();
        return serviceLocator;
    }

   
    private ServiceLocator_JC() {
    }

    public Class<?> getAPP_CLASS() {
        return APP_CLASS;
    }
    
    public String getAPP_NAME() {
        return APP_NAME;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Configuration_JC getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration_JC configuration) {
        this.configuration = configuration;
    }

    public Locale[] getLocales() {
        return locales;
    }

    public Translator_JC getTranslator() {
        return translator;
    }
    
    public void setTranslator(Translator_JC translator) {
        this.translator = translator;
    }
}
