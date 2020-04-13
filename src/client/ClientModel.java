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
	
		private static int 				client_id;
		private final String ipAdress 		= "localhost";
		private final int port 				= 9998;
		private Logger logger;
		private Socket socket;
		
		
	public ClientModel() {
		logger = ServiceLocator_JC.getServiceLocator().getLogger();
		connect(ipAdress, port);
		// Send join message to the server
		sayHello();
		testLogin();
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
						process(msgIn);
						System.out.println("Nachricht vom Server erhalten: ");
						System.out.println(msgIn.toString());
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

	protected void process(Message msgIn) {
			
	}

	public String sayHello() {
		String result = null;
		if (socket != null) {
			Message msgOut = new Message_HELLO();
			msgOut.setClient(client_id);
			
			try {
				msgOut.send(socket);
			} catch (Exception e) {
				result = e.toString();
			}

		}
		return result;
	}
	
private String testLogin() {
		
		String result = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Geben Sie einen Usernamen ein!");
		String username = scan.nextLine();
		System.out.println("Geben Sie ein Passwort ein!");
		String password = scan.nextLine();
		Message_LOGIN msgOut = new Message_LOGIN();
		msgOut.setUsername(username);
		msgOut.setPassword(password);
		if(socket != null) {
		try {
			msgOut.send(socket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Message msgIn;
//		try {
//			msgIn = Message.receive(socket);
//			result = msgIn.toString();
//			System.out.println(result);
//		} catch (Exception e) {
//			result = e.toString();
//		}
		}
		
		return result;
		
	}


public static String getClient_id() {
	return client_id;
}


public static void setClient_id(String client_id) {
	ClientModel.client_id = client_id;
}

	
	
	
	
	
	
	
	
	
	
	
	

}
