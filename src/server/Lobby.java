package server;

import java.util.ArrayList;

public class Lobby {

    private static Lobby lobby;

    private static ArrayList<Game> actualgames = new ArrayList<>();
    private static ArrayList<Player> playersOnline = new ArrayList<>();
//Hier kommt dann die Game id von der DB rein gibt kein Counter mehr
    private static int counterGames = 0;
    private static int counterPlayers = 0;

    public static Lobby getLobby() {
	if (lobby == null)
	    lobby = new Lobby();
//		lobby.createGame("game");//Für Testklasse!! Pascal
	return lobby;
    }

    public void createGame(String name) {
	counterGames++;
	Game game = new Game(counterGames, name);
	actualgames.add(game);
    }

    public void removeGame(int gameID) {
	for (int i = 0; i < actualgames.size(); i++) {
	    if (actualgames.get(i).getGameID() == gameID) {
		actualgames.remove(i);
	    }
	}
    }

//
    public ArrayList<Game> getGames() {
	return Lobby.actualgames;
    }

    public static ArrayList<Player> getPlayersOnline() {
	return Lobby.playersOnline;
    }

//Nach dem Login muss diese Methode aufgeruffen werden um den erstellten Player in die Online Liste zu setzen
    public void setPlayersOnline(Player player) {

	boolean playerExists = false;

	for (Player p : playersOnline) {
	    if (p.equals(player)) {
		playerExists = true;
	    }
	}
	if (!playerExists) {
	    Lobby.playersOnline.add(player);
	}
    }

//In Spieler aktuelles Spiel schreiben sobald er einem Spiel in der Lobby beitretet
//TODO @Oli diese Methode soll zwei Strings entgegennehmen, thanks!!
    public void JoinGame(String game_id, Player player) {
	player.setActualGame(Integer.parseInt(game_id));
	for (int i = 0; i < actualgames.size(); i++) {
	    if (actualgames.get(i).getGameID() == Integer.parseInt(game_id)) {
		actualgames.get(i).addPlayer(player);
	    }
	}
    }

    public void startNextRound(String id) {
	for (Game g : actualgames) {
	    if (id.equals(g.getGameID()+"")) {
		g.nextRound();
	    }
	}
    }

    public String OnlinePlayersAsString() {

	String result = "";

	for (Player p : playersOnline) {
	    result += p.getName() + "|";
	}

	return result;

    }

    public String GamesAsString() {

	String result = "";

	for (Game g : actualgames) {
	    result += g.getGameID() + " " + g.getName() + "|";
	}

	return result;

    }

// Diese Methode durchsucht alle Games nach Spieler mit gesuchten Namen und gibt GameID zurück= Player.actualGame
    public String getGameIDofPlayersGame(String client) {
	int id = 0;
	for (Player p : playersOnline) {
	    if (p.getName().equals(client)) {
		id = p.getActualGame();
	    }
	}
	return Integer.toString(id);
    }

    public String getStringOfCertainGame(String gameID) {

	String result = "";
	for (Game g : actualgames) {
	    if (Integer.toString(g.getGameID()) == gameID) {
		result = g.GameAsString();
	    }
	}
	return result;
    }

	public String getCardsOnTable(String gameid) {
		int gameID = Integer.parseInt(gameid);
		for( Game g : actualgames) {
			if(g.getGameID() == gameID) {
				ArrayList cards = g.getCardsOnTable();
			}
		}

		return null;
	}

	

  
    
    
}
