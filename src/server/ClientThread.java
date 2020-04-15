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
import messages.Message_LOGINNOTOK;
import messages.Message_LOGINOK;


//aka Server Model
public class ClientThread extends Thread {
	

	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private Logger logger;
	
	public ClientThread(Socket clientSocket) throws IOException {
		this.logger = ServiceLocator_JC.getServiceLocator().getLogger();
		this.clientSocket = clientSocket;
		
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
				//Ueberprueft ob Login korrekt ist und sendet dann die entsprechende Nachricht
				Message_LOGIN lg_msg = (Message_LOGIN) msgIn;
				if(ServerModel.CheckLogin(lg_msg.getUsername(), lg_msg.getPassword())) {
					msgOut = new Message_LOGINOK();
				} else  { 
					msgOut = new Message_LOGINNOTOK();
					
				
				}
				break;
				
			default:
				msgOut = new Message_ERROR();
		}
		msgOut.setClient(clientName);
		return msgOut;
	}
	

}
