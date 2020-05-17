package client;

import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import messages.Message_STARTGAME;
import messages.Message_USERNAMETAKEN;
import server.Card;
import server.Player;
import server.Weis;

public class ClientModel {
    protected SimpleStringProperty newestMessage = new SimpleStringProperty();

    private final String ipAdress = "localhost";
    private final int port = 9998;
    private Logger logger = ServiceLocator_JC.getServiceLocator().getLogger();;
    private Socket socket;
    private String clientName;
    private ArrayList<Player> players = new ArrayList<Player>();
    private String actualGame;

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
			    if (msgIn != null) {
				process(msgIn);
			    }
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
	    msgOut = new Message_LOBBYUPDATE();
	    this.clientName = msgIn.getClient();
	    logger.info("Nachricht LoginOK erhalten");
	    logger.info(msgIn.getClient() + " erfolgreich eingeloggt");
	    ClientController.switchview(2);
	    msgOut.setClient(this.clientName);
	    msgOut.send(socket);
	    logger.info("Client hat Lobby Update angefordert");
	    break;

	case LOGINNOTOK:
	    msgOut = new Message_LOGINNOTOK();
	    msgOut.setClient(this.clientName);
	    logger.info(msgIn.getClient() + " Login Daten nicht korrekt");
	    ClientController.updateLoginInfoLabel("Login Daten nicht korrekt"); // Translator setzen
	    break;

	case CREATEUSER:
	    msgOut = new Message_CREATEUSER();
	    logger.info("User erfolgreich registriert");
	    ClientController.updateLoginInfoLabel("User erfolgreich registriert");
	    break;

	case USERNAMETAKEN:
	    msgOut = new Message_USERNAMETAKEN();
	    logger.info("Username vergeben");
	    ClientController.updateLoginInfoLabel("Der Username ist bereits vergeben");
	    break;

	case LOBBYUPDATE:
	    msgOut = new Message_LOBBYUPDATE();
	    Message_LOBBYUPDATE lu_msg = (Message_LOBBYUPDATE) msgIn;
	    lu_msg.setClient(this.clientName);
	    logger.info("Lobby Update erhalten:" + lu_msg);
	    ClientController.loadPlayersOnline(findPlayers(lu_msg.getPlayersonline()));
	    ClientController.loadGames(findGames(lu_msg.getGames()));
	    ClientController.loadChat(lu_msg.getChat());
	    break;

	case GAMEUPDATE:
	    msgOut = new Message_GAMEUPDATE();
	    Message_GAMEUPDATE gu_msg = (Message_GAMEUPDATE) msgIn;
	    gu_msg.setClient(this.clientName);
	    logger.info("Game Update erhalten: " + "\n" + gu_msg);
	    ClientController.loadPlayersonGame(findPlayersOnGame(gu_msg.getPlayers()), gu_msg.getClient(),
		    gu_msg.getTrumpf(), gu_msg.getTeamScore());
			if(gu_msg.getCardsontable().length()<4) {
				break;
		 	}
	    ClientController.loadCardsOnTable(gu_msg.getCardsontable());
	    System.out.println("CLIENTCONTROLLERCARDSONTABLE:" + gu_msg.getCardsontable());
	    // TODO Bessere Lösung finden als While Schleife
	    if (gu_msg.getStichover().equals("true")) {
		

		if (gu_msg.getGamefinish().equals("true")) {
		    ClientController.showWinnerTeam(gu_msg.getWinnerteamid());

		} else {
		    if (gu_msg.getStapelfinish().equals("true")) {
			ClientController.showStapelWinner(gu_msg.getWinnerteamid(), gu_msg.getTeamScore());
		    } else {
			Message_NEXTROUND msg_nr = new Message_NEXTROUND();
			msg_nr.setGamename(gu_msg.getGameid());
			msg_nr.send(socket);
		    }
		    int i = 0;
			while (i < 40000) {
			    i++;
			}
		   ClientController.emptyTable();

		}
	    }

	    break;

	case CREATEGAME:
	    msgOut = new Message_LOBBYUPDATE();
	    msgOut.setClient(this.clientName);
	    logger.info("Spiel wurde erstellt und Lobby Update angefordert");
	    msgOut.send(socket);
	    break;

	case JOINGAME:
	    msgOut = new Message_LOBBYUPDATE();
	    msgOut.setClient(this.clientName);
	    logger.info("Game wurde beigetreten und Lobby Update angefordert");
	    msgOut.send(socket);
	    break;

	case STARTGAME:
	    Message_STARTGAME msgtemp = (Message_STARTGAME) msgIn;
	    this.actualGame = msgtemp.getGamename();
	    msgOut = new Message_GAMEUPDATE();
	    msgOut.setClient(this.clientName);
	    logger.info("Game wurde gestartet");
	    ClientController.switchview(3);
	    msgOut.send(socket);
	    break;

	case MOVE:
	    msgOut = new Message_GAMEUPDATE();
	    msgOut.setClient(this.clientName);
	    logger.info("Zug wurde gemacht und GameUpdate angefordert");
	    msgOut.send(socket);
	    break;

	case NEXTROUND:
	    msgOut = new Message_GAMEUPDATE();
	    msgOut.setClient(this.clientName);
	    msgOut.send(socket);
	    break;
	case NEXTSTAPLE:
	    msgOut = new Message_GAMEUPDATE();
	    msgOut.setClient(this.clientName);
	    msgOut.send(socket);
	    break;
	case GOODBYE:
	    Message_GOODBYE msg_bye = (Message_GOODBYE) msgIn;
	    msgOut = new Message_GOODBYE();
	    Message_LOBBYUPDATE msg_lbu = new Message_LOBBYUPDATE();

	    msg_lbu.setClient(this.clientName);
	    if (msg_bye.getCiaoSource().equals("LEAVEGAME")) {
		ClientController.showDialog();
		msg_lbu.send(socket);
		// TODO Ausgewähltes Game aus Liste in Lobby ausblenden
	    }

	    break;

	default:
	    msgOut = new Message_ERROR();
	}
	msgOut.setClient(this.clientName);
	return msgOut;
    }

    private Player[] findPlayersOnGame(String players) {
	System.out.println(players);

	String[] playersOnGame = players.split("\\$"); // 1 Zeile = 1 Spieler + Karten etc
	Player[] output = new Player[4];
	String[] spieler1 = null; // 1Spieler auf Zeilen aufgeteilt
	String[] spieler2 = null;
	String[] spieler3 = null;
	String[] spieler4 = null;
	for (int i = 0; i < playersOnGame.length; i++) {
	    if (i == 0) {
		spieler1 = playersOnGame[i].split("\\|");
	    }
	    if (i == 1) {
		spieler2 = playersOnGame[i].split("\\|");
	    }
	    if (i == 2) {
		spieler3 = playersOnGame[i].split("\\|");
	    }
	    if (i == 3) {
		spieler4 = playersOnGame[i].split("\\|");
	    }

	}

	ArrayList<String> cardPlayer1 = new ArrayList<String>();
	ArrayList<String> cardPlayer2 = new ArrayList<String>();
	ArrayList<String> cardPlayer3 = new ArrayList<String>();
	ArrayList<String> cardPlayer4 = new ArrayList<String>();
	// Karten Spieler 1
	for (int i = 5; i < spieler1.length; i = i + 2) {
	    cardPlayer1.add(spieler1[i] + "$" + spieler1[i + 1]);

	}
	// Karten Spieler 2
	for (int i = 5; i < spieler2.length; i = i + 2) {
	    cardPlayer2.add(spieler2[i] + "$" + spieler2[i + 1]);

	}
	// Karten Spieler 3
	for (int i = 5; i < spieler3.length; i = i + 2) {
	    cardPlayer3.add(spieler3[i] + "$" + spieler3[i + 1]);

	}
	// Karten Spieler 4
	for (int i = 5; i < spieler4.length; i = i + 2) {
	    cardPlayer4.add(spieler4[i] + "$" + spieler4[i + 1]);

	}

	// int player_id, String name, int actualGame, boolean onMove, Weis weis,
	// ArrayList<String>cards
	Player p1 = new Player(Integer.parseInt(spieler1[0]), spieler1[1], Integer.parseInt(spieler1[2]),
		Boolean.parseBoolean(spieler1[3]), null, cardPlayer1);
	Player p2 = new Player(Integer.parseInt(spieler2[0]), spieler2[1], Integer.parseInt(spieler2[2]),
		Boolean.parseBoolean(spieler2[3]), null, cardPlayer2);
	Player p3 = new Player(Integer.parseInt(spieler3[0]), spieler3[1], Integer.parseInt(spieler3[2]),
		Boolean.parseBoolean(spieler3[3]), null, cardPlayer3);
	Player p4 = new Player(Integer.parseInt(spieler4[0]), spieler4[1], Integer.parseInt(spieler4[2]),
		Boolean.parseBoolean(spieler4[3]), null, cardPlayer4);
	output[0] = p1;
	output[1] = p2;
	output[2] = p3;
	output[3] = p4;
	setPlayers(p1, p2, p3, p4);// Auf instanz ebene
	return output;
    }

    private String[] findGames(String games) {
	String[] gamesArray = games.split("\\|");
	return gamesArray;

    }

    private String[] findJoinedgames(String joinedgames) {
	String[] joinedgamesArray = joinedgames.split("\\|");
	return joinedgamesArray;
    }

    private String[] findPlayers(String s) {
	String[] playersArray = s.split("\\|");

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
	if (socket != null) {
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

	if (socket != null) {
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

	if (socket != null) {
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
	logger.info("Join Game Message an Server gesendet: " + msgOut);
	if (socket != null) {
	    try {
		msgOut.send(socket);
	    } catch (Exception e) {
		logger.warning(e.toString());
	    }
	}
    }

    public void sayMove(String gameID, String playerID, String card) {
	System.out.println("SAYMOVE: " + gameID + playerID + card);
	Message_MOVE msgOut = new Message_MOVE();
	msgOut.setClient(clientName);
	msgOut.setPlayerid(playerID);
	msgOut.setGameid(gameID);
	msgOut.setCard(card);
	if (socket != null) {
	    try {
		msgOut.send(socket);
	    } catch (Exception e) {
		logger.warning(e.toString());
	    }
	}
    }

    public void setPlayers(Player p1, Player p2, Player p3, Player p4) {
	if (players.size() < 1) {
	    players.add(p1);
	    players.add(p2);
	    players.add(p3);
	    players.add(p4);
	} else {
	    for (int i = 0; i < players.size(); i++) {
		if (players.get(i).getName().equals(p1.getName())) {
		    players.remove(i);
		    players.add(p1);
		}
		if (players.get(i).getName().equals(p2.getName())) {
		    players.remove(i);
		    players.add(p2);
		}
		if (players.get(i).getName().equals(p3.getName())) {
		    players.remove(i);
		    players.add(p3);
		}
		if (players.get(i).getName().equals(p4.getName())) {
		    players.remove(i);
		    players.add(p4);
		}
	    }
	}

    }

    public Player getPlayer(String name) {
	Player pla = null;
	for (Player p : players) {
	    if (p.getName().equals(name)) {
		pla = p;
	    }
	}
	return pla;
    }

    public void sayGoodBye(String source) {
	Message_GOODBYE msgOut = new Message_GOODBYE();
	msgOut.setClient(clientName);
	msgOut.setPlayername(clientName);
	msgOut.setCiaoSource(source);
	if (socket != null) {
	    try {
		msgOut.send(socket);
	    } catch (Exception e) {
		logger.warning(e.toString());
	    }
	}

    }

    public void sayNextStaple() {
	Message_NEXTSTAPLE msgOut = new Message_NEXTSTAPLE();
	msgOut.setGamename(actualGame);
	msgOut.setClient(clientName);
	msgOut.send(socket);

    }

    public void sayExitGame() {
	Message_GOODBYE msgOut = new Message_GOODBYE();
	msgOut.setCiaoSource("Lobby2");
	msgOut.setClient(clientName);
	msgOut.send(socket);

    }

    public String getActualGame() {
	return actualGame;
    }

    public void setActualGame(String actualGame) {
	this.actualGame = actualGame;
    }

    public void saynewChat(String message) {
	Message_CHAT msgOut = new Message_CHAT();
	msgOut.setChatMessage(message);
	msgOut.setClient(clientName);
	msgOut.send(socket);

    }

}
