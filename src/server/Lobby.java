package server;

import java.util.ArrayList;

//Klasse von Frank Mauchle

public class Lobby {

	private static Lobby lobby;
	private static ArrayList<Game> actualgames = new ArrayList<>();
	private static ArrayList<Player> playersOnline = new ArrayList<>();
	private static int counterGames = 0;

	// Statische Lobby also wenn schon eine vorhanden keine weitere erstellen
	// (Singleton)
	public static Lobby getLobby() {
		if (lobby == null)
			lobby = new Lobby();
		return lobby;
	}

	// Spiel erstellen und der Lobby hinzufügen wenn noch nicht vorhanden
	public void createGame(String name) {
		boolean gameInList = false;
		for (Game g : actualgames) {
			if (g.getName().equals(name))
				gameInList = true;
		}
		if (!gameInList) {
			counterGames++;
			Game game = new Game(counterGames, name);
			actualgames.add(game);
		}
	}

	// Spiel aus der Lobby löschen
	public void removeGame(int gameID) {
		for (int i = 0; i < actualgames.size(); i++) {
			if (actualgames.get(i).getGameID() == gameID) {
				actualgames.remove(i);
			}
		}
	}

	public ArrayList<Game> getGames() {
		return Lobby.actualgames;
	}

	public static ArrayList<Player> getPlayersOnline() {
		return Lobby.playersOnline;
	}

	// Spieler wird in der Lobby später als Online angezeigt
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

	// In Spieler aktuelles Spiel schreiben sobald er einem Spiel in der Lobby
	// beitretet
	public void JoinGame(String game_id, Player player) {
		player.setActualGame(Integer.parseInt(game_id));
		for (int i = 0; i < actualgames.size(); i++) {
			if (actualgames.get(i).getGameID() == Integer.parseInt(game_id)) {
				actualgames.get(i).addPlayer(player);
			}
		}
	}

	// Nächste Runde starten in einem Game
	public void startNextRound(String id) {
		for (Game g : actualgames) {
			if (id.equals(g.getGameID() + "")) {
				g.nextRound();
			}
		}
	}

	// Für Message Klasse kann man alle Spieler in einem String zurück geben die
	// Online sind
	public String OnlinePlayersAsString() {

		String result = "";

		for (Player p : playersOnline) {
			result += p.getName() + "|";
		}

		return result;

	}

	// Für Message Klasse kann man alle Games in einem String zurück geben (Games
	// enthalten auch Spieler auf dem Game)
	public String GamesAsString() {

		String result = "";

		for (Game g : actualgames) {
			result += g.getGameID() + " " + g.getName() + "|";
		}

		return result;

	}

	// Diese Methode durchsucht alle Games nach Spieler mit gesuchten Namen und gibt
	// GameID zurueck= Player.actualGame
	public String getGameIDofPlayersGame(String client) {
		int id = 0;
		for (Player p : playersOnline) {
			if (p.getName().equals(client)) {
				id = p.getActualGame();
			}
		}
		return Integer.toString(id);
	}

	// Zu einer Game_ID den Namen dazu finden
	public String getStringOfCertainGame(String gameID) {

		String result = "";
		for (Game g : actualgames) {
			if (Integer.toString(g.getGameID()) == gameID) {
				result = g.GameAsString();
			}
		}
		return result;
	}

	// Alle Karten die sich auf dem Tisch befinden als String zurück geben für
	// Message Klasse
	public String getCardsOnTable(String gameid) {
		int gameID = Integer.parseInt(gameid);
		for (Game g : actualgames) {
			if (g.getGameID() == gameID) {
				ArrayList cards = g.getCardsOnTable();
			}
		}

		return null;
	}

	// Sobald 4 Spieler einen neuen Stapel moechten wird neu ausgeteilt
	public synchronized void nextStaple(String gamename, String client) {
		for (Game g : actualgames) {
			if (g.getName().equals(gamename)) {
				if (g.getWishForNewStaple() == 3) {
					g.nextRound();
					g.setWishForNewStaple(0);
					ServerModel.updateClients(2, client);
				} else {
					int num = g.getWishForNewStaple() + 1;
					g.setWishForNewStaple(num);
				}

			}

		}

	}

}
