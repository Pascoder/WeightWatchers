package server;

import java.util.ArrayList;

public class Lobby {

private static Lobby lobby;

private static ArrayList <Game> actualgames = new ArrayList <>();
private static ArrayList <Player> playersOnline = new ArrayList<>();
//Hier kommt dann die Game id von der DB rein gibt kein Counter mehr
private static int counterGames = 0;
private static int counterPlayers = 0;

public static Lobby getLobby() {
    if (lobby == null)
        lobby = new Lobby();
    return lobby; 
}

public void createGame(String name) {
//Muss noch erg�nzt werden wenn Oli Klasse Game erstellt hat
this.counterGames++;

Game game = new Game(counterGames, name);
actualgames.add(game);
}

public void removeGame(int gameID) {
	for(int i=0; i<actualgames.size();i++) {
		if(actualgames.get(i).getGameID()==gameID) {
			actualgames.remove(i);
		}
	}
}

//
public ArrayList <Game> getGames(){
	return this.actualgames;
}

public ArrayList <Player> getPlayersOnline(){
	return this.playersOnline;
}

//Nach dem Login muss diese Methode aufgeruffen werden um den erstellten Player in die Online Liste zu setzen
public void setPlayersOnline(Player player) {
this.playersOnline.add(player);
}

//In Spieler aktuelles Spiel schreiben sobald er einem Spiel in der Lobby beitretet
public void JoinGame(int game_id, Player player) {
	player.setActualGame(game_id);
	for(int i = 0; i<actualgames.size();i++) {
		if(actualgames.get(i).getGameID()==game_id) {
			actualgames.get(i).addPlayer(player);
		}
	}
	}


public String OnlinePlayersAsString() {
	
	String result = "";
	
	for(Player p : playersOnline) {
		result += p.toString() + "|";
	}
	
	return result;
		
	
	}






















}
