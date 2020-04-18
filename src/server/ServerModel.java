package server;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServerModel {

private static int player_id = 1;
	//Methode um die Login Credentials auf der Datenbank zu kontrollieren, true wenn Korrekt
	public static boolean CheckLogin(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean loginOK = true;
		
		//Erweiterung DB
		
		/*String result = DataBase.getDataBase().executeQuery("SELECT * FROM it_db1.player WHERE name ='"+username+"'AND password ='"+password+"';");
		if(result.length()>0) {
			loginOK = true;
			String id = DataBase.getDataBase().executeQuery("SELECT player_id FROM it_db1.player WHERE name ='"+username+"'AND password ='"+password+"';");
			Player player = new Player(Integer.parseInt(id),username);
			Lobby.getLobby().setPlayersOnline(player);
		}*/
		
		
	
		
		
		return loginOK;
	}

	public static void joinLobby() {
		// TODO Auto-generated method stub
		
	}

	public static void createUser(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		//DataBase.getDataBase().executeUpdate("INSERT INTO it_db1.player (name,password,onMove,fk_team) VALUES ('"+username+"','"+password+"',0,null);");
	Player player = new Player(player_id,username,password);
	Lobby.getLobby().setPlayersOnline(player);
	player_id++;
	}

	public static boolean checkUserExisting(String username) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean existingOk = false;
		/*String result = DataBase.getDataBase().executeQuery("SELECT * FROM it_db1.player WHERE name ='"+username+"';");
		if(result.length()>0) {
			existingOk = true;
		}*/
		return existingOk;
	}

	
	
	

}
