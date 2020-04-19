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
	private static ArrayList<String> accounts = new ArrayList<>();
	private static boolean accountsloaded = false;


//Methode um Loggin zu pr�fen, wenn ok Lobby wird Player als Online hinzugef�gt	
		public static boolean CheckLogin(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			if(accountsloaded == false) {
				loadaccounts();
			}
			boolean loginOK = false;
			String key = username+password;
			
				
				for(int i = 0; i<accounts.size();i++) {
					if(accounts.get(i).equals(key)) {
						Player player = new Player(player_id,username,password);
						Lobby.getLobby().setPlayersOnline(player);
						player_id++;
						loginOK = true;
					}
				}
<<<<<<< HEAD
				return loginOK;
=======
				
				//fuer testphase von lobby/game, login immer true
				return loginOK = true;
>>>>>>> branch 'master' of https://github.com/Pascoder/WeightWatchers
			 //this.getClass().getClassLoader().getResourceAsStream("client/"+ "Schweizer_Jasskarten.jpg")
			//Hier wird methode newPlayer erstellt und somit Login erstellt
			
			//Erweiterung DB
			
			/*String result = DataBase.getDataBase().executeQuery("SELECT * FROM it_db1.player WHERE name ='"+username+"'AND password ='"+password+"';");
			if(result.length()>0) {
				loginOK = true;
				String id = DataBase.getDataBase().executeQuery("SELECT player_id FROM it_db1.player WHERE name ='"+username+"'AND password ='"+password+"';");
				Player player = new Player(Integer.parseInt(id),username);
				Lobby.getLobby().setPlayersOnline(player);
			}*/
		}



		private static void loadaccounts() {
			try(BufferedReader in = new BufferedReader(new FileReader("src/PlayerFile.txt"))){
				String s = in.readLine();
				
				while(s!=null) {
					accounts.add(s);
					s = in.readLine();
				}	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		accountsloaded = true;
			
		}



		public static boolean createUser(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
			//DataBase.getDataBase().executeUpdate("INSERT INTO it_db1.player (name,password,onMove,fk_team) VALUES ('"+username+"','"+password+"',0,null);");
			
			//Pr�fen ob Accounts bereits geladen wurden
			if(accountsloaded == false) {
				loadaccounts();
			}
			//Pr�fen ob User bereits existiert
			boolean userNotExist = true;
			for(int i = 0; i<accounts.size();i++) {
				if(accounts.get(i).equals(username+""+password)) {
					
					userNotExist = false;
				}
			}
			//Wenn User nicht existiert wird Accounts Liste updated
				if(userNotExist == true) {
				accounts.add(username+""+password);
				saveAccounts();
				}
			
		return userNotExist;
		}



		private static void saveAccounts() throws IOException {
			try(BufferedWriter out = new BufferedWriter(new FileWriter("src/PlayerFile.txt"))){
				for(int b = 0; b<accounts.size();b++) {
				out.write(accounts.get(b)+"\n");
				}
				out.flush();			
					}
			
		}


		
		

	
	
	

}
