package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Logger;

import client.ClientModel;
import client.ServiceLocator_JC;
import messages.Message;
import messages.MessageType;
import messages.Message_CREATEUSER;
import messages.Message_ERROR;
import messages.Message_HELLO;
import messages.Message_LOGIN;
import messages.Message_LOGINNOTOK;
import messages.Message_LOGINOK;
import messages.Message_MOVE;
import messages.Message_USERNAMETAKEN;


//aka Server Model
public class ClientThread extends Thread {
	

	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
//	private final Logger logger = Logger.getLogger("");
	private String clientName = null;
	
	public ClientThread(Socket clientSocket) throws IOException {
		
		this.clientSocket = clientSocket;
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream());
		ServerModel.addClientThreadToList(this);
	}
	
	public void run() {
		Logger.getLogger(("Request from client " + clientSocket.getInetAddress().toString()
                + " for server " + clientSocket.getLocalAddress().toString()));
				
				while(true)
				try {
					//Read Message from the Client
					Message msgIn = Message.receive(clientSocket);
					System.out.println("Nachricht vom Client erhalten: " + msgIn);
					Message msgOut = processMessage(msgIn);
					System.out.println("Antwort dem Client gesendet: "+ msgOut);
					msgOut.send(clientSocket);
				} catch (Exception e) {
					Logger.getLogger(e.getMessage());
				} 
//					finally {
//					try { if ( clientSocket != null) clientSocket.close(); 
//					} catch (IOException e) {}
//				}
			}
	
	

	Message processMessage(Message msgIn) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		
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
					this.clientName = lg_msg.getUsername();
					lg_msg.setClient(clientName);
					ServerModel.updateClients(1, lg_msg.getClient());//1=Lobby Update
				} else  { 
					msgOut = new Message_LOGINNOTOK();
				}
				break;
				
			case CREATEUSER:
				Message_CREATEUSER cu_msg = (Message_CREATEUSER) msgIn;
				//Wenn true, dann ist der Username vergeben, false ist er frei
			
				if (ServerModel.createUser(cu_msg.getUsername(), cu_msg.getPassword())) {
					
					msgOut = new Message_USERNAMETAKEN();
//					logger.info("Server an Client: Username vergeben");
					
				} else {
					msgOut= new Message_CREATEUSER();
//					logger.info("Server an Client: Username erfolgreich registriert");
				}
			
				break;
			
			case MOVE:
				Message_MOVE mo_msg = (Message_MOVE) msgIn;
				
				int Game_ID = Integer.parseInt(mo_msg.getGameid());
				int Player_ID = Integer.parseInt(mo_msg.getPlayerid());
				String Card = mo_msg.getCard();
				
				for(Game g : Lobby.getLobby().getGames()) {
					if(g.getGameID() == Game_ID) {
						g.playCard(Game_ID, Player_ID, null);//TODO send Card as String
					}
				}
				
				ServerModel.updateClients(2, mo_msg.getClient());
				 
				break;
				
				
				
				
				
			default:
				msgOut = new Message_ERROR();
				
		}
		msgOut.setClient(clientName);
		return msgOut;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public String getClientName() {
		return clientName;
	}

	
	

}
