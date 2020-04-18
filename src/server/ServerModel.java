package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerModel {

	private static int player_id = 1;
	private static ArrayList<String> playerList = new ArrayList<>();

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
			
			String key = username+password;
			
			 //this.getClass().getClassLoader().getResourceAsStream("client/"+ "Schweizer_Jasskarten.jpg")
			//Hier wird methode newPlayer erstellt und somit Login erstellt
			
			try(BufferedReader in = new BufferedReader(new FileReader("src/PlayerFile.txt"))){
			String s = in.readLine();
			
			while(s!=null) {
				playerList.add(s);
				s = in.readLine();
			}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i = 0; i<playerList.size();i++) {
				if(playerList.get(i).equals(key)) {
					Player player = new Player();
					loginOK = true;
				}
			}
			
			
			return loginOK;
		}



		public static boolean createUser(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
			//DataBase.getDataBase().executeUpdate("INSERT INTO it_db1.player (name,password,onMove,fk_team) VALUES ('"+username+"','"+password+"',0,null);");
			playerList.add(username+""+password);
		try(BufferedWriter out = new BufferedWriter(new FileWriter("src/PlayerFile.txt"))){
		for(int i = 0; i<playerList.size();i++) {
		out.write(playerList.get(i)+"\n");
		}
		out.flush();
		}
		//Muss im Textfile gespeichert werden	
			
		/*Player player = new Player(player_id,username,password);
		Lobby.getLobby().setPlayersOnline(player);
		player_id++;*/
		
		return false;
		}


		
		

	
	
	

}
