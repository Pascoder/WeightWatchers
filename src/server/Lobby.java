package server;

import java.util.ArrayList;

public class Lobby {
	
private ArrayList <Game> games = new ArrayList <>();
private ArrayList <Player> playersOnline = new ArrayList<>();
private int counterGames = 0;
private int counterPlayers = 0;

public void createGame(String name) {
//Muss noch erg�nzt werden wenn Oli Klasse Game erstellt hat
this.counterGames++;
Game game = new Game(counterGames, name);
games.add(game);
}

public void removeGame(int gameID) {
	for(int i=0; i<games.size();i++) {
		if(games.get(i).getGameID()==gameID) {
			games.remove(i);
		}
	}
}

//
public ArrayList <Game> getGames(){
	return this.games;
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
	for(int i = 0; i<games.size();i++) {
		if(games.get(i).getGameID()==game_id) {
			games.get(i).addPlayer(player);
		}
	}
	}


}
