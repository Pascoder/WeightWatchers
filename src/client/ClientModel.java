package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

import messages.Message;
import messages.Message_HELLO;
import messages.Message_LOGIN;



public class ClientModel {
	
		private static int ClientNr = 0;
		private final String ipAdress = "localhost";
		private final int port = 9998;
		private Logger logger;
		private String clientName = "";
		private Socket socket;
		
		
	public ClientModel() {
		logger = ServiceLocator_JC.getServiceLocator().getLogger();
		connect(ipAdress, port);
		System.out.println("namen eingeben: ");
		Scanner scan = new Scanner (System.in);
		clientName = scan.nextLine();
		// Send join message to the server
		sayHello(clientName);
		String answer = testLogin();
//		System.out.println(answer);
	}
	
	
	
	
	private String testLogin() {
		
		String result = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Geben Sie einen Usernamen ein!");
		String username = scan.nextLine();
		System.out.println("Geben Sie ein Passwort ein!");
		String password = scan.nextLine();
		Message_LOGIN testMsg = new Message_LOGIN();
		testMsg.setUsername(username);
		testMsg.setPassword(password);
		testMsg.setId(1);

		
		testMsg.send(socket);
		Message msgIn;
		try {
			msgIn = Message.receive(socket);
			result = msgIn.toString();
			System.out.println(result);
		} catch (Exception e) {
			result = e.toString();
		}
		
		
		return result;
		
	}




	public void connect(String ipAddress, int Port) {
	try {
		socket = new Socket(ipAddress, Port);

		// Create thread to read incoming messages
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Message msgIn = (Message) Message.receive(socket);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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


	public String sayHello(String clientName) {
		String result = null;
		if (socket != null) {
			Message msgOut = new Message_HELLO();
			msgOut.setClient(clientName);
			
			try {
				
				msgOut.send(socket);
//				System.out.println("Client sagt dem Server hallo. nachricht: "+ msgOut);
				Message msgIn = Message.receive(socket);
				result = msgIn.toString();
				System.out.println("Server antwortet: " + result);
			} catch (Exception e) {
				result = e.toString();
			}
//				try { if (socket != null) socket.close(); } catch (IOException e) {} Braucht es nicht?!
		}
		return result;
	}

	public static int getClientID() {
		return ClientNr ++;
	}
	
	
	
	
	
	
	
	
	
	
	

}
