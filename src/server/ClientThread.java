package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import client.ClientModel;
import client.ServiceLocator_JC;
import messages.Message;
import messages.MessageType;
import messages.Message_ERROR;
import messages.Message_HELLO;
import messages.Message_LOGIN;


//aka Server Model
public class ClientThread extends Thread {
	

	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private String client_id;
	private Logger logger;
	
	public ClientThread(String client_id, Socket clientSocket) throws IOException {
		this.logger = ServiceLocator_JC.getServiceLocator().getLogger();
		this.clientSocket = clientSocket;
		this.client_id = client_id;
		ClientModel.setClient_id(client_id);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream());
		
	}
	
	public void run() {
		
		
		try { 
			
			//			while ((inputLine = in.readLine()) != null) {
				try {
								
					Message msgIn = Message.receive(clientSocket);
					System.out.println("Nachricht vom Client erhalten: " + msgIn);
					Message msgOut = processMessage(msgIn);
					System.out.println("Antwort dem Client gesendet: "+ msgOut);
					msgOut.setClient(client_id);
					msgOut.send(clientSocket);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	

	

	private Message processMessage(Message msgIn) {
		
		String clientName = msgIn.getClient();
		Message msgOut = null;
		
		switch (MessageType.getType(msgIn)) {
			case HELLO:
				msgOut = new Message_HELLO();
				break;
				
			case LOGIN:
				Message_LOGIN lg_msg = (Message_LOGIN) msgIn;
				//Methode um Login zu prüfen und ggf. zu aktzeptieren
				msgOut = new Message_LOGIN();
				msgOut.setClient(lg_msg.getClient());
				break;
				
			default:
				msgOut = new Message_ERROR();
		}
		msgOut.setClient(clientName);
		return msgOut;
	}
	

}
