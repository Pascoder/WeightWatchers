package server;

import java.util.ArrayList;

public class Lobby {
	
private ArrayList <Game> games = new ArrayList <>();
private ArrayList <Player> playersOnline = new ArrayList<>();
private int counterGames = 0;
private int counterPlayers = 0;

public void createGame(String name) {
//Muss noch ergänzt werden wenn Oli Klasse Game erstellt hat
this.counterGames++;
Game game = new Game(counterGames, name);
games.add(game);
}

//In der Lobby werden erstmals die Spieler erstellt und in der Liste OnlinePlayers hinzugefügt
public void createPlayer(String name) {
this.counterPlayers++;
Player player = new Player(counterPlayers, name);
playersOnline.add(player);
}

public void removeGame(int gameID) {
	for(int i=0; i<games.size();i++) {
		if(games.get(i).getGameID()==gameID) {
			games.remove(i);
		}
	}
}

public void removePlayer(int playerID) {
	for(int i = 0; i<playersOnline.size();i++) {
		if(playersOnline.get(i).getPlayer_id()==playerID) {
			playersOnline.remove(i);
		}
	}
}

public ArrayList <Game> getGames(){
	return this.games;
}

public ArrayList <Player> getPlayers(){
	return this.playersOnline;
}

//Spieler zu einem Game hinzufügen
public void JoinGame(int game_id, Player p1) {
	for(int i = 0; i< games.size();i++) {
		if(this.games.get(i).getGameID()==game_id) {
			this.games.get(i).joinGame(game_id, p1);
		}
	}
	
}



}
