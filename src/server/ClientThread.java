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
import messages.Message_CHAT;
import messages.Message_CREATEGAME;
import messages.Message_CREATEUSER;
import messages.Message_ERROR;
import messages.Message_GAMEUPDATE;
import messages.Message_GOODBYE;
import messages.Message_HELLO;
import messages.Message_JOINGAME;
import messages.Message_LOBBYUPDATE;
import messages.Message_LOGIN;
import messages.Message_LOGINNOTOK;
import messages.Message_LOGINOK;
import messages.Message_MOVE;
import messages.Message_NEXTROUND;
import messages.Message_NEXTSTAPLE;
import messages.Message_USERNAMETAKEN;


//aka Server Model
public class ClientThread extends Thread {
	

	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
//	private final Logger logger = Logger.getLogger("");
	private String clientName = null;
	private String actualGameID = null;
	
	
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
		
					Message msgOut = processMessage(msgIn);
					
					msgOut.send(clientSocket);
				} catch (Exception e) {
					Logger.getLogger(e.getLocalizedMessage());
				} 				
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
//					ServerModel.updateClients(1, getClientName());//1 = Lobby Update TODO ENUM machen!
				} else  { 
					msgOut = new Message_LOGINNOTOK();
				}
				break;
				
			case CREATEUSER:
				Message_CREATEUSER cu_msg = (Message_CREATEUSER) msgIn;
				//Wenn true, dann ist der Username vergeben, false ist er frei
			
				if (ServerModel.createUser(cu_msg.getUsername(), cu_msg.getPassword())) {
					
					msgOut = new Message_USERNAMETAKEN();
					
				} else {
					msgOut= new Message_CREATEUSER();
					
				}
				break;
			
			case MOVE:
				Message_MOVE mo_msg = (Message_MOVE) msgIn;
				
				int Game_ID = Integer.parseInt(mo_msg.getGameid());
				int Player_ID = Integer.parseInt(mo_msg.getPlayerid());
				String Card = mo_msg.getCard();
				
				for(Game g : Lobby.getLobby().getGames()) {
					if(g.getGameID() == Game_ID) g.playedCardfromClient_2(Game_ID, Player_ID, Card);
					
				}
				msgOut = new Message_MOVE();
				
				break;
				
			case CREATEGAME:
				Message_CREATEGAME cg_msg = (Message_CREATEGAME) msgIn;
				Lobby.getLobby().createGame(cg_msg.getGamename());
				msgOut = new Message_CREATEGAME();
				break;
				
			case JOINGAME:
				Message_JOINGAME jg_msg = (Message_JOINGAME) msgIn;
				Lobby.getLobby().JoinGame(ServerModel.searchGameID(jg_msg.getGamename())
				,ServerModel.searchPlayerbyName(getClientName()) );
				msgOut = new Message_JOINGAME();
				break;
				
			case NEXTROUND:
				Message_NEXTROUND nr_msg = (Message_NEXTROUND) msgIn;
				Lobby.getLobby().startNextRound(nr_msg.getGamename());
				msgOut = new Message_HELLO();
				break;
				
			case NEXTSTAPLE:
				Message_NEXTSTAPLE ns_msg = (Message_NEXTSTAPLE) msgIn;
				Lobby.getLobby().nextStaple(ns_msg.getGamename(), ns_msg.getClient());
				msgOut = new Message_NEXTSTAPLE();
				break;	
				
			case GOODBYE:
				//Lobby1 = Lobby verlassen, Lobby2 = Ausgewähltes Spiel verlassen, ExitGame = Spiel, das bereits gestartet ist verlassen
				Message_GOODBYE ciao_msg = (Message_GOODBYE) msgIn;
				if(ciao_msg.getCiaoSource().equals("Lobby1")) {
					ServerModel.removePlayerFromLobby(clientName);
					ServerModel.updateClients(1, clientName);
				}
				if(ciao_msg.getCiaoSource().equals("Lobby2")) {
					System.out.println(clientName + " verlässt das Spiel");
					ServerModel.leaveGame(clientName);
					ServerModel.updateClients(1, clientName);

					}
				if(ciao_msg.getCiaoSource().equals("ExitGame")) {
					ServerModel.kickPlayers(clientName);
					ServerModel.removePlayerFromLobby(clientName);
					
					
				}
				
				msgOut = new Message_HELLO();
				break;
				
			case LOBBYUPDATE:
				Message_LOBBYUPDATE lu_msg = (Message_LOBBYUPDATE) msgIn;
				lu_msg.setClient(clientName);
				ServerModel.updateClients(1, clientName);//1 = Lobby Update
				msgOut = new Message_HELLO();
				break;
				
			case GAMEUPDATE:
				Message_GAMEUPDATE gu_msg = (Message_GAMEUPDATE) msgIn;
				gu_msg.setClient(clientName);
				ServerModel.updateClients(2, clientName);//2 = Game Update
				msgOut = new Message_HELLO();
				
				break;
			case CHAT:
				Message_CHAT chat_msg = (Message_CHAT) msgIn;
				chat_msg.setClient(clientName);
				ServerModel.addnewChat(clientName+": "+chat_msg.getChatMessage());
				ServerModel.updateClients(1, clientName);//1 = Lobby Update
				msgOut = new Message_HELLO();
				
				break;
				
			default:
				msgOut = new Message_ERROR();
				
		}
		 
		    	msgOut.setClient(this.clientName);	
		    
			
			
		
		return msgOut;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public String getClientName() {
		return this.clientName;
	}

	
	

}
