package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;



public class Configuration_JC {
    ServiceLocator_JC sl = ServiceLocator_JC.getServiceLocator(); // for easy reference
    Logger logger = sl.getLogger(); // for easy reference

    private Properties defaultOptions;
    private Properties localOptions;

    private String ip;
    private int port;
    private Socket socket = null;
    private BufferedWriter writer;
    private BufferedReader reader;
    private String name;
    private String token;
    private String pass;
    private String userAct;
    private boolean loginSucces = false;

    public Configuration_JC() {
	// Load default properties from wherever the code is
	defaultOptions = new Properties();
	String defaultFilename = sl.getAPP_NAME() + "Defaults.cfg";
	InputStream inStream = sl.getAPP_CLASS().getResourceAsStream(defaultFilename);
	try {
	    defaultOptions.load(inStream);
	    logger.config("Default configuration file found");
	} catch (Exception e) {
	    logger.warning("No default configuration file found: " + defaultFilename);
	} finally {
	    try {
		inStream.close();
	    } catch (Exception ignore) {
	    }
	}

	// Define locally-saved properties; link to the default values
	localOptions = new Properties(defaultOptions);

	// Load the local configuration file, if it exists.
	try {
	    inStream = new FileInputStream(sl.getAPP_NAME() + ".cfg");
	    localOptions.load(inStream);
	} catch (FileNotFoundException e) { // from opening the properties file
	    logger.config("No local configuration file found");
	} catch (IOException e) { // from loading the properties
	    logger.warning("Error reading local options file: " + e.toString());
	} finally {
	    try {
		inStream.close();
	    } catch (Exception ignore) {
	    }
	}

	for (Enumeration<Object> i = localOptions.keys(); i.hasMoreElements();) {
	    String key = (String) i.nextElement();
	    logger.config("Option: " + key + " = " + localOptions.getProperty(key));
	}
    }

    public void save() {
	FileOutputStream propFile = null;
	try {
	    propFile = new FileOutputStream(sl.getAPP_NAME() + "Defaults.cfg");
	    localOptions.store(propFile, null);
	    logger.config("Local configuration file saved");
	} catch (Exception e) {
	    logger.warning("Unable to save local options: " + e.toString());
	} finally {
	    if (propFile != null) {
		try {
		    propFile.close();
		} catch (Exception e) {
		}
	    }
	}
    }

    public void generateSocket() throws Exception {
	try {
	    this.socket = new Socket(this.ip, this.port);
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void closeSocket() throws Exception {
	try {
	    this.socket.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void createBufferedWriter() throws Exception {
	try {
	    this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
	} catch (Exception ex) {
	    ex.getMessage();
	}
    }

    public void createBufferedReader() throws Exception {
	try {
	    this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public String getPass() {
	return pass;
    }

    public void setPass(String pass) {
	this.pass = pass;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public void sendMsg(String string) throws Exception {
	try {
	    writer.write(string + "\n");
	    writer.flush();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public String receiveMsg() throws Exception {
	String reply = "";
	try {
	    String reply1 = reader.readLine();
	    if (reply1 != null) {
		reply = reply1;
	    } else {
		reply = "Keine Nachricht";
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return reply;
    }

    public String getIp() {
	return ip;
    }

    public int getPort() {
	return port;
    }

    public void setPort(int port) {
	this.port = port;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    public String getOption(String name) {
	return localOptions.getProperty(name);
    }

    public void setLocalOption(String name, String value) {
	localOptions.setProperty(name, value);
    }

    public void setName(String username) {
	this.name = username;

    }

    public String getName() {
	return name;
    }

    public boolean getLogin() {
	// TODO Auto-generated method stub
	return false;
    }

    public void setWriter(BufferedWriter writer) {
	this.writer = writer;
    }

    public BufferedReader getReader() {
	return reader;
    }

    public void setReader(BufferedReader reader) {
	this.reader = reader;
    }

    public Socket getSocket() {
	return socket;
    }

    public void setSocket(Socket socket) {
	this.socket = socket;
    }

    public BufferedWriter getWriter() {
	return writer;
    }

    public boolean isLoginSucces() {
	return loginSucces;
    }

    public void setLoginSucces(boolean loginSucces) {
	this.loginSucces = loginSucces;
    }
}
