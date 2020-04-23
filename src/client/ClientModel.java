package client;

import java.net.Socket;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import messages.Message;
import messages.MessageType;
import messages.Message_CREATEGAME;
import messages.Message_CREATEUSER;
import messages.Message_ERROR;
import messages.Message_GAMEUPDATE;
import messages.Message_HELLO;
import messages.Message_LOBBYUPDATE;
import messages.Message_LOGIN;
import messages.Message_LOGINNOTOK;
import messages.Message_LOGINOK;
import messages.Message_USERNAMETAKEN;



public class ClientModel {
		protected SimpleStringProperty newestMessage = new SimpleStringProperty();
		
		private final String ipAdress 		= "localhost";
		private final int port 				= 9998;
		private Logger logger = ServiceLocator_JC.getServiceLocator().getLogger();;
		private Socket socket;
		private String clientName;
		
		
	public ClientModel() {
		clientName = "none";
		connect(ipAdress, port);
		
	}
	

	public void connect(String ipAddress, int Port) {
	try {
		socket = new Socket(ipAddress, Port);
		logger.info("Connected to server");

		// Create thread to read incoming messages
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (true) {
					
						
						try {
							
							Message msgIn = Message.receive(socket);
							if(msgIn != null) {
							process(msgIn);}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
//						msgOut.send(socket);
						
							
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		
		
		
		} catch (Exception e) {
			logger.warning(e.toString());
		}
	}

	protected Message process(Message msgIn) {
		String clientName = msgIn.getClient();
		Message msgOut = null;
		
		switch (MessageType.getType(msgIn)) {
		
		case HELLO:
			msgOut = new Message_HELLO();
			logger.info(msgIn.getClient() + " erfolgreich beim Server angemeldet");
			break;
			
		case LOGINOK:
			msgOut = new Message_LOGINOK();
			clientName = msgIn.getClient();
			logger.info(msgIn.getClient() + " erfolgreich eingeloggt");
			ClientController.switchview(2);
			break;
			
		case LOGINNOTOK:
			msgOut = new Message_LOGINNOTOK();
			logger.info(msgIn.getClient() + " Login Daten nicht korrekt");
			ClientController.updateLoginInfoLabel("Login Daten nicht korrekt");
			break;
			
		case CREATEUSER:
			msgOut= new Message_CREATEUSER();
			logger.info(msgIn.getClient() + "User erfolgreich registriert");
			ClientController.updateLoginInfoLabel("User erfolgreich registriert");
			break;
			
		case USERNAMETAKEN:
			msgOut = new Message_USERNAMETAKEN();
			logger.info("Username vergeben");
			ClientController.updateLoginInfoLabel("Der Username: *" + msgIn.getClient() + "* ist bereits vergeben");
			break;
			
		case LOBBYUPDATE:
			msgOut = new Message_LOBBYUPDATE();
			logger.info("Lobby Update erhalten:");
			System.out.println(msgIn.toString());
			//TODO Verbindung zu Controller um ViewUpdate zu machen
			break;
			
		case GAMEUPDATE:
			msgOut = new Message_GAMEUPDATE();
			logger.info("Game Update erhalten:");
			System.out.println(msgIn.toString());
			//TODO Verbindung zu Controller um ViewUpdate zu machen
			break;
			
		default:
			msgOut = new Message_ERROR();
		}
		msgOut.setClient(clientName);
		return msgOut;
	}

	public void sayHello(String clientName) {
		
		if (socket != null) {
			Message msgOut = new Message_HELLO();
			msgOut.setClient(clientName);
			try {
				msgOut.send(socket);
			} catch (Exception e) {
				logger.info(e.toString()); 
			}
		}
		
	}
	
	public void sayLogin(String username, String password) {
		
//		sayHello(username);
		Message_LOGIN msgOut = new Message_LOGIN();
		msgOut.setClient(username);
		msgOut.setUsername(username);
		msgOut.setPassword(password);
		if(socket != null) {
		try {
				msgOut.send(socket);
			} catch (Exception e) {
				logger.warning(e.toString());
				}
			}	
		}


	public void sayRegister(String username, String password) {
		
		Message_CREATEUSER msgOut = new Message_CREATEUSER();
		msgOut.setClient(username);
		msgOut.setUsername(username);
		msgOut.setPassword(password);
		
		if(socket != null) {
			try {
					msgOut.send(socket);
				} catch (Exception e) {
					logger.warning(e.toString());
					}
				}	
	}
	
	
	public void sayCreateGame(String gamename) {
		
		Message_CREATEGAME msgOut = new Message_CREATEGAME();
		msgOut.setClient(clientName);
		msgOut.setGamename(gamename);
		
		if(socket != null) {
			try {
					msgOut.send(socket);
				} catch (Exception e) {
					logger.warning(e.toString());
					}
				}	
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
