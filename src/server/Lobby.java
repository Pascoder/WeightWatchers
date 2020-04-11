package server;

import java.util.ArrayList;

public class Lobby {
	
private ArrayList <Game> games = new ArrayList <>();
private ArrayList <Player> players = new ArrayList<>();
private int counterGames = 0;
private int counterPlayers = 0;

public void createGame(int game_id) {
//Muss noch ergänzt werden wenn Oli Klasse Game erstellt hat
this.counterGames++;
Game game = new Game(counterGames);
games.add(game);
}

public void createPlayer(String name) {
this.counterPlayers++;
Player player = new Player(counterPlayers, name);
players.add(player);
}

public void removeGame(int gameID) {
	for(int i=0; i<games.size();i++) {
		if(games.get(i).getGameID()==gameID) {
			games.remove(i);
		}
	}
}

public void removePlayer(int playerID) {
	for(int i = 0; i<players.size();i++) {
		if(players.get(i).getPlayer_id()==playerID) {
			players.remove(i);
		}
	}
}

public ArrayList <Game> getGames(){
	return this.games;
}

public ArrayList <Player> getPlayers(){
	return this.players;
}



}
