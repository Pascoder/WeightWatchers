package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {
    static ServiceLocator sl = ServiceLocator.getServiceLocator();
    Logger logger = sl.getLogger();

    private Properties defaultOptions;
    private FileInputStream inStream;

    public Configuration() {
	// Load default properties from wherever the code is
	defaultOptions = new Properties();

	try {
	    inStream = new FileInputStream("ServerDefaults.cfg");
	    defaultOptions.load(inStream);
	} catch (FileNotFoundException e) {
	    logger.config("No default configuration file found");
	} catch (IOException e) {
	    logger.warning("Error reading default options file: " + e.toString());
	} finally {
	    try {
		inStream.close();
	    } catch (Exception ignore) {
	    }
	}

	for (Enumeration<Object> i = defaultOptions.keys(); i.hasMoreElements();) {
	    String key = (String) i.nextElement();
	    logger.config("Option: " + key + " = " + defaultOptions.getProperty(key));
	}
    }
    
    // nmomentan nicht verwendet, keine Änderungen im Programm vorgesehen
    public void save() {
	FileOutputStream propFile = null;
	try {
	    propFile = new FileOutputStream("ServerDefaults.cfg");
	    defaultOptions.store(propFile, null);
	    logger.config("Default configuration file saved");
	} catch (Exception e) {
	    logger.warning("Unable to save default options: " + e.toString());
	} finally {
	    if (propFile != null) {
		try {
		    propFile.close();
		} catch (Exception e) {
		}
	    }
	}
    }
    
  
}
