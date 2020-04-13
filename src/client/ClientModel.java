package client;

import java.net.Socket;
import java.util.logging.Logger;

import messages.Message;
import messages.MessageType;
import messages.Message_ERROR;
import messages.Message_HELLO;
import messages.Message_LOGIN;



public class ClientModel {
	
		private final String ipAdress 		= "localhost";
		private final int port 				= 9998;
		private Logger logger;
		private Socket socket;
		
		
	public ClientModel() {
		logger = ServiceLocator_JC.getServiceLocator().getLogger();
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
						Message msgIn = (Message) Message.receive(socket);
						logger.info("Nachricht vom Server erhalten: ");
						logger.info(msgIn.toString());
						Message msgOut = process(msgIn);
						msgOut.send(socket);
						logger.info("Nachricht dem Server gesendet: ");
						logger.info(msgOut.toString());
						
					} catch (Exception e) {
						logger.warning(e.toString());
					}		
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
			logger.info(msgIn.getClient() + " erfolgreich beim Server angemeldet");
			
		case LOGINOK:
			logger.info(msgIn.getClient() + " erfolgreich eingeloggt");
			//TODO Methode um Lobby beizutreten und View zu wechseln auf LobbyView
		case LOGINNOTOK:
			logger.info(msgIn.getClient() + " Login Daten nicht korrekt");
			//TODO Update View, Login nicht korrekt
		
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
	
	public void login(String username, String password) {

		Message_LOGIN msgOut = new Message_LOGIN();
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
	}
