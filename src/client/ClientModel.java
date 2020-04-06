package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;



public class ClientModel {
	
		private int ClientNr = 0;
		private final String ipAdress = "localhost";
		private final int port = 9998;
		private Logger logger;
		
		
	public ClientModel() {
		logger = ServiceLocator_JC.getServiceLocator().getLogger();
		connect(ipAdress, port);
	}
	
	public void connect(String ipAddress, int Port) {
	
	
	try {
		Socket socket = new Socket(ipAddress, Port);

		// Create thread to read incoming messages
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Message msg = (Message) Message.receive(socket);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						
				}
			}
		};
		Thread t = new Thread(r);
		t.start();

		// Send join message to the server
		Message msg = new Message_HELLO();
		msg.send(socket);
	} catch (Exception e) {
		logger.warning(e.toString());
	}
}
	

}
