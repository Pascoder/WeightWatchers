package client;

import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import messages.Message;
import messages.MessageType;
import messages.Message_CREATEGAME;
import messages.Message_CREATEUSER;
import messages.Message_ERROR;
import messages.Message_GAMEUPDATE;
import messages.Message_HELLO;
import messages.Message_JOINGAME;
import messages.Message_LOBBYUPDATE;
import messages.Message_LOGIN;
import messages.Message_LOGINNOTOK;
import messages.Message_LOGINOK;
import messages.Message_STARTGAME;
import messages.Message_USERNAMETAKEN;
import server.Card;
import server.Player;
import server.Weis;



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
			this.clientName = msgIn.getClient();
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
			Message_LOBBYUPDATE lu_msg = (Message_LOBBYUPDATE) msgIn;
			lu_msg.setClient(clientName);
			logger.info("Lobby Update erhalten:");
			System.out.println(msgIn.toString());
			ClientController.loadPlayersOnline(findPlayers(lu_msg.getPlayersonline()));
			ClientController.loadGames(findGames(lu_msg.getGames()));
			ClientController.joinGame(findJoinedgames(lu_msg.getGames())); //TODO:Hier Fehlt eine Methode auf dem Server Model LobbyUpdate habe einfach mal getGames genommen
			break;
			
		case GAMEUPDATE: 
			msgOut = new Message_GAMEUPDATE();
			Message_GAMEUPDATE gu_msg = (Message_GAMEUPDATE) msgIn;
			gu_msg.setClient(clientName);
			logger.info("Game Update erhalten:");
			ClientController.loadPlayersonGame(findPlayersOnGame(gu_msg.getPlayers()),gu_msg.getClient());
			//ClientController.spreadCards(spreadCards(gu_msg.getPlayers()),gu_msg.getClient());
			//TODO Verbindung zu Controller um ViewUpdate zu machen
			break;
			
		case CREATEGAME:
			msgOut = new Message_CREATEGAME();
			logger.info("Spiel wurde erstellt");
			break;
		
		case JOINGAME:
			msgOut = new Message_JOINGAME();
			logger.info("Game wurde beigetreten");
			break;
			
		case STARTGAME:
			msgOut = new Message_STARTGAME();
			logger.info("Game wurde gestartet");
			ClientController.switchview(3);
			break;
			
			
		default:
			msgOut = new Message_ERROR();
		}
		msgOut.setClient(clientName);
		return msgOut;
	}

	
	
	
	
	


	private String[] spreadCards(String players) {
		int counter = 0;
		String[] playersOnGame = players.split("\\$");
		
		 String [] spieler1 = null;
		 String [] spieler2 = null;
		 String [] spieler3 = null;
		 String [] spieler4 = null;
		 String [] output = new String [40];
		 for(int i = 0; i< playersOnGame.length;i++) {
			if(i == 0) {
				spieler1 = playersOnGame[i].split("\\|");
			}
			if(i == 1) {
				spieler2 = playersOnGame[i].split("\\|");
			}
			if(i == 2) {
				spieler3 = playersOnGame[i].split("\\|");
			}
			if(i == 3) {
				spieler4 = playersOnGame[i].split("\\|");
			}
		 }
		 //Karten Spieler 1
		 for(int i = 5; i<spieler1.length;i=i+2) {
			 if(i == 5) {
				output[counter] = spieler1[1]; 
				counter++;
			 }
			
			 output[counter] = spieler1[i];
			 counter++;
		 }
		 //Karten Spieler 2
		 for(int i = 5; i<spieler2.length;i=i+2) {
			 if(i == 5 ) {
				 output[counter] = spieler2[1];
				 counter++;
			 }
			 output[counter] = spieler2[i];
			 counter++;
		 }
		 //Karten Spieler 3
		 for(int i = 5; i<spieler3.length;i=i+2) {
			 if(i == 5 ) {
				 output[counter] = spieler3[1];
				 counter++;
			 }
			 
			 output[counter] = spieler3[i];
			 counter++;
		 }
		 //Karten Spieler 4
		 for(int i = 5; i<spieler4.length;i=i+2) {
			 
			 if(i == 5 ) {
				 output[counter] = spieler4[1];
				 counter++;
			 }
			 output[counter] = spieler4[i];
			 counter++;
		 }
		 
		 
		return output;
		
	}


	private Player[] findPlayersOnGame(String players) {
		//System.out.println("Dieser String wird in die Methode gegeben:"+players);
		 String[] playersOnGame = players.split("\\$"); // 1 Zeile = 1 Spieler + Karten etc
		 Player [] output = new Player [4];
		 String [] spieler1 = null; // 1Spieler auf Zeilen aufgeteilt
		 String [] spieler2 = null;
		 String [] spieler3 = null;
		 String [] spieler4 = null;
		 for(int i = 0; i< playersOnGame.length;i++) {
			if(i == 0) {
				spieler1 = playersOnGame[i].split("\\|");
			}
			if(i == 1) {
				spieler2 = playersOnGame[i].split("\\|");
			}
			if(i == 2) {
				spieler3 = playersOnGame[i].split("\\|");
			}
			if(i == 3) {
				spieler4 = playersOnGame[i].split("\\|");
			}
	
		 }
			/*System.out.println("Spieler1:");
			for(String s:spieler1) {
				System.out.println(s);
			}
			System.out.println("Spieler2:");
			for(String a:spieler2) {
				System.out.println(a);
			}
			System.out.println("Spieler3:");
			for(String b:spieler3) {
				System.out.println(b);
			}
			System.out.println("Spieler4:");
			for(String c:spieler4) {
				System.out.println(c);
			}*/
			
		 ArrayList<String> cardPlayer1 = new ArrayList<String>();
		 ArrayList<String> cardPlayer2 = new ArrayList<String>();
		 ArrayList<String> cardPlayer3 = new ArrayList<String>();
		 ArrayList<String> cardPlayer4 = new ArrayList<String>();
		 //Karten Spieler 1
		 for(int i = 5; i<spieler1.length;i=i+2) {
			cardPlayer1.add(spieler1[i]+spieler1[i+1]) ;
			
			 
		 }
		 //Karten Spieler 2
		 for(int i = 5; i<spieler2.length;i=i+2) {
				cardPlayer2.add(spieler2[i]+spieler2[i+1]) ;
				
				 
			 }
		 //Karten Spieler 3
		 for(int i = 5; i<spieler3.length;i=i+2) {
				cardPlayer3.add(spieler3[i]+spieler3[i+1]) ;
				
				 
			 }
		 //Karten Spieler 4
		 for(int i = 5; i<spieler4.length;i=i+2) {
				cardPlayer4.add(spieler4[i]+spieler4[i+1]) ;
				
				 
			 }
		 
		 
		 //int player_id, String name, int actualGame, boolean onMove, Weis weis, ArrayList<String>cards
		 Player p1 = new Player(Integer.parseInt(spieler1[0]), spieler1[1], Integer.parseInt(spieler1[2]), Boolean.parseBoolean(spieler1[3]),null,cardPlayer1);
		 Player p2 = new Player(Integer.parseInt(spieler2[0]), spieler2[1], Integer.parseInt(spieler2[2]), Boolean.parseBoolean(spieler2[3]),null,cardPlayer2);
		 Player p3 = new Player(Integer.parseInt(spieler3[0]), spieler3[1], Integer.parseInt(spieler3[2]), Boolean.parseBoolean(spieler3[3]),null,cardPlayer3);
		 Player p4 = new Player(Integer.parseInt(spieler4[0]), spieler4[1], Integer.parseInt(spieler4[2]), Boolean.parseBoolean(spieler4[3]),null,cardPlayer4);
		 output[0] = p1;
		 output[1] = p2;
		 output[2] = p3;
		 output[3] = p4;
		 
		return output;
	}


	private String [] findGames(String games) {
		String [] gamesArray = games.split("\\|");
		return gamesArray;
		
	}
	
	private String [] findJoinedgames(String joinedgames) {
		String [] joinedgamesArray = joinedgames.split("\\|");
		return joinedgamesArray;
	}


	private String [] findPlayers(String s) {
		String [] playersArray = s.split("\\|");
		for(String se:playersArray) {
			System.out.println("Das ist ein Spieler: "+se);
		}
		
//		ArrayList <String> playersList = new ArrayList <String>();
//		String [] playersArray = s.split("\\n");
//		playersList.add(playersArray[4]);
//		String players = playersList[3];
//		String playersNew = players.substring(14);
//		String [] returnString = playersNew.split("\\|");
//		System.out.println("Das ist ein String: " + s);
		
		
		return playersArray;
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
		System.out.println(msgOut.toString());
		
		if(socket != null) {
			try {
					msgOut.send(socket);
				} catch (Exception e) {
					logger.warning(e.toString());
					}
				}		
	}
	
	public void sayJoinGame(String gamename) {
		Message_JOINGAME msgOut = new Message_JOINGAME();
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
