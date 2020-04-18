package server;

import java.sql.SQLException;

public class ServerModel {

	
	//Methode um die Login Credentials auf der Datenbank zu kontrollieren, true wenn Korrekt
	public static boolean CheckLogin(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean loginOK = false;
		/*String result = DataBase.getDataBase().executeQuery("SELECT * FROM it_db1.player WHERE name ='"+username+"'AND password ='"+password+"';");
		if(result.length()>0) {
			loginOK = true;
		}*/
		
		return loginOK;
	}

	public static void joinLobby() {
		// TODO Auto-generated method stub
		
	}

	public static void createUser(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		//DataBase.getDataBase().executeUpdate("INSERT INTO it_db1.player (name,password,onMove,fk_team) VALUES ('"+username+"','"+password+"',0,null);");
		
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
