package server;

// Pascal Wyser, Oliver Mosimann

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import messages.Message_GAMEUPDATE;
import messages.Message_GOODBYE;
import messages.Message_LOBBYUPDATE;
import messages.Message_STARTGAME;

public class ServerModel {

    private static int player_id = 1;
    private static ArrayList<String> accounts = new ArrayList<>();
    private static boolean accountsloaded = false;
    private static ArrayList<ClientThread> clientList;
    private static String chatHistory = "";
    static ServiceLocator sl = ServiceLocator.getServiceLocator();
    static Logger logger = sl.getLogger();

    public ServerModel() {
	clientList = new ArrayList<ClientThread>();
    }

    // Methode um Loggin zu pruefen, wenn ok Lobby wird Player als Online
    // hinzugefuegt
    public static boolean CheckLogin(String username, String password)
	    throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	if (accountsloaded == false) {
	    loadaccounts();
	}
	boolean loginOK = false;
	for (int i = 0; i < accounts.size(); i++) {
	    String[] logindata = accounts.get(i).split("\\|");

	    if (logindata[1].equals(password) && logindata[0].equals(username)) {
		if (isPlayerOffline(username)) {
		    Player player = new Player(player_id, username, password);
		    Lobby.getLobby().setPlayersOnline(player);
		    player_id++;
		    loginOK = true;
		}

	    }
	}

	return loginOK;

	
    }

    private static boolean isPlayerOffline(String username) {
	boolean isPlayerOffline = true;
	for (Player p : Lobby.getPlayersOnline()) {
	    if (p.getName().equals(username)) {
		isPlayerOffline = false;
	    }
	}
	return isPlayerOffline;
    }

    private static void loadaccounts() {
	try (BufferedReader in = new BufferedReader(new FileReader("PlayerFile.txt"))) {
	    String s = in.readLine();

	    while (s != null) {
		accounts.add(s);
		s = in.readLine();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	accountsloaded = true;
	logger.info("loadAccounts");
    }

    public static boolean createUser(String username, String password)
	    throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
	

	// Pruefen ob Accounts bereits geladen wurden
	if (accountsloaded == false) {
	    loadaccounts();
	}
	// Pruefen ob User bereits existiert
	boolean userNotExist = true;
	for (int i = 0; i < accounts.size(); i++) {
	    if (accounts.get(i).equals(username + "|" + password)) {

		userNotExist = false;
	    }
	}
	// Wenn User nicht existiert wird Accounts Liste updated
	if (userNotExist == true) {
	    accounts.add(username + "|" + password);
	    saveAccounts();
	}
	return !userNotExist;
    }

    private static void saveAccounts() throws IOException {
	try (BufferedWriter out = new BufferedWriter(new FileWriter("PlayerFile.txt"))) {
	    for (int b = 0; b < accounts.size(); b++) {
		out.write(accounts.get(b) + "\n");
	    }
	    out.flush();
	}

    }

    public static void updateClients(int option, String client) {
	// option = 1 Update Lobby / option = 2 Update Game / option = 3 StichOver
	String clientName = client;
	switch (option) {
	case 1:
	    Message_LOBBYUPDATE msgOutLobby = new Message_LOBBYUPDATE();
	    msgOutLobby.setClient(clientName);
	    msgOutLobby.setPlayersonline(Lobby.getLobby().OnlinePlayersAsString());
	    msgOutLobby.setGames(Lobby.getLobby().GamesAsString());
	    msgOutLobby.setChat(ServerModel.chatHistory);
	    for (ClientThread cT : clientList) {
		msgOutLobby.send(cT.getClientSocket());

	    }
	    break;

	case 2:
	    // In Methoden aufsplitten
	    Message_GAMEUPDATE msgOutGame = new Message_GAMEUPDATE();

	    String gameId = Lobby.getLobby().getGameIDofPlayersGame(client);
	    ArrayList<String> playersInGame = new ArrayList<String>();
	    String playersInGameString = "";
	    Game game = null;

	    // Fuellt Spielernamen in ArrayList
	    for (Game g : Lobby.getLobby().getGames()) {
		if (Integer.toString(g.getGameID()).equals(gameId)) {
		    game = g;
		    playersInGameString += g.GameAsString();
		    for (Player p : g.getPlayersOnGame()) {
			playersInGame.add(p.getName());
		    }
		}
	    }
	    msgOutGame.setWinnernames(game.getWinnerNames());
	    msgOutGame.setGameid(gameId);
	    msgOutGame.setPlayers(playersInGameString);
	    msgOutGame.setCardsontable(game.getCardsOnTableAsString());
	    msgOutGame.setTrumpf(game.getTrumpf() + "");
	    msgOutGame.setStichover(game.isStichFinish() + "");
	    msgOutGame.setStapelfinish(game.isStapelFinish() + "");
	    msgOutGame.setWinnerteamid(game.getLastWinnerTeam_ID() + "");
	    msgOutGame.setGamefinish(game.isGameFinish() + "");
	    msgOutGame.setTeamScore(game.getTeam1().getTeamMembers().get(0).getName() + "|"
		    + game.getTeam1().getTeamMembers().get(1).getName() + "|" + game.getTeam1().getTeamPoints() + "$"
		    + game.getTeam2().getTeamMembers().get(0).getName() + "|"
		    + game.getTeam2().getTeamMembers().get(1).getName() + "|" + game.getTeam2().getTeamPoints());

	    // Update an alle Clients senden die im gleichen Spiel sind
	    for (ClientThread cT : clientList) {
		for (String player : playersInGame) {
		    if (cT.getClientName().equals(player)) {
			System.out.println("SERVER sendet:" + cT.getClientName() + " " + playersInGameString);
			msgOutGame.setClient(player);
			msgOutGame.send(cT.getClientSocket());

		    }
		}
	    }
	    break;
	}

    }

    // Sendet GameStart Message an den Client im Game
    public static void sayGameStarted(String gamename, String client) {
	Message_STARTGAME msgOutstart = new Message_STARTGAME();

	msgOutstart.setClient(client);
	msgOutstart.setGamename(gamename);

	for (ClientThread ct : clientList) {
	    if (ct.getClientName().equals(client)) {
		msgOutstart.send(ct.getClientSocket());

	    }
	}

    }

    public static void addClientThreadToList(ClientThread clientThread) {
	clientList.add(clientThread);
    }

    // Sucht den Spieler nach name mittels String
    public static Player searchPlayerbyName(String name) {
	Player player = null;
	for (Player p : Lobby.getPlayersOnline()) {
	    if (p.getName().equals(name))

		player = p;
	}
	return (player);
    }

    // Sucht den Spieler nach ID mittels String
    public Player searchPlayer(String person_id) {
	Player player = null;
	for (Player p : Lobby.getPlayersOnline()) {
	    if (p.getPlayer_id() == Integer.parseInt(person_id))

		player = p;
	}
	return (player);
    }

    // Sucht das Game nach ID mittels String
    public Game searchGame(String game_id) {
	Game game = null;
	for (Game g : Lobby.getLobby().getGames()) {
	    if (g.getGameID() == Integer.parseInt(game_id))
		game = g;
	}
	return (game);
    }

    // Sucht die GameID String:name
    public static String searchGameID(String name) {
	String game_ID = null;
	for (Game g : Lobby.getLobby().getGames()) {
	    if (g.getName().equals(name)) {

		game_ID = Integer.toString(g.getGameID());
	    }
	}
	return game_ID;
    }

    // Löscht Spieler aus den Spiel
    public void clearGame(int game_ID) {
	for (Player p : Lobby.getPlayersOnline()) {
	    if (p.getActualGame() == game_ID) {
		p.setActualGame(-1);
	    }
	}
    }

    public static void removePlayerFromLobby(String playername) {
	for (int i = 0; i < Lobby.getPlayersOnline().size(); i++) {
	    if (Lobby.getPlayersOnline().get(i).getName().equals(playername)) {
		Lobby.getPlayersOnline().remove(i);
	    }
	}

    }

    // Sucht game des Spielers und kickt ihn aus dem Spiel
    public static void leaveGame(String playername) {
	for (int i = 0; i < Lobby.getLobby().getGames().size(); i++) {
	    for (int b = 0; b < Lobby.getLobby().getGames().get(i).getPlayersOnGame().size(); b++) {
		if (Lobby.getLobby().getGames().get(i).getPlayersOnGame().get(b).getName().equals(playername)) {

		    Lobby.getLobby().getGames().get(i).getPlayersOnGame().remove(b);
		    System.out.println("Spieler :"
			    + Lobby.getLobby().getGames().get(i).getPlayersOnGame().get(b).getName()
			    + " hat das Spiel: " + Lobby.getLobby().getGames().get(i).getName() + " verlassen.");
		}
	    }
	}

    }

    // Sucht das richtige Spiel und die übrigen Spieler noch im Spiel
    public static void kickPlayers(String client) {

	String GameID = Lobby.getLobby().getGameIDofPlayersGame(client);
	for (Game g : Lobby.getLobby().getGames()) {
	    if (GameID.equals(g.getGameID() + "")) {
		for (Player p : g.getPlayersOnGame()) {
		    if (p.getName().equals(client)) {

		    } else {
			sendGoodbyeMsg(p.getName());

		    }

		}
	    }
	}

    }

    private static void sendGoodbyeMsg(String name) {
	Message_GOODBYE bye_msg = new Message_GOODBYE();
	bye_msg.setCiaoSource("LEAVEGAME");
	bye_msg.setClient(name);
	for (ClientThread ct : clientList) {
	    if (ct.getClientName().equals(name)) {
		bye_msg.send(ct.getClientSocket());
	    }
	}

    }

    public static void addnewChat(String chatMessage) {
	ServerModel.chatHistory += "|" + chatMessage;

    }

	public static void deleteGame(String client) {
		String GameID = Lobby.getLobby().getGameIDofPlayersGame(client);
		Lobby.getLobby().removeGame(Integer.parseInt(GameID));
		
	}

}
