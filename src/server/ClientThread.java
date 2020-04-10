package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import client.Message;
import client.MessageType;
import client.Message_ERROR;
import client.Message_HELLO;
import client.ServiceLocator_JC;


//aka Server Model
public class ClientThread extends Thread {
	

	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private int id = 0;
	private Logger logger;
	
	public ClientThread(int id, Socket clientSocket) throws IOException {
		logger = ServiceLocator_JC.getServiceLocator().getLogger();
		this.clientSocket = clientSocket;
		this.id = id;
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream());
		
	}
	
	public void run() {
		try { 
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				try {
					
					Message msgIn = Message.receive(clientSocket);
					System.out.println("Nachricht vom Client erhalten: " + msgIn);
					Message msgOut = processMessage(msgIn);
					System.out.println("Antwort dem Client gesendet: "+ msgOut);
					msgOut.send(clientSocket);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	

	

	private Message processMessage(Message msgIn) {
		logger.info("Message received from client: " + msgIn.toString());
		String clientName = msgIn.getClient();
		
		Message msgOut = null;
		switch (MessageType.getType(msgIn)) {
			case HELLO:
				msgOut = new Message_HELLO();
				break;
				
			default:
				msgOut = new Message_ERROR();
		}
		msgOut.setClient(clientName);
		return msgOut;
	}
	

}
