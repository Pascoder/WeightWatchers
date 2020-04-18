package server;


import java.sql.SQLException;

public class DataBaseTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DataBase db = new DataBase();
		
		//db.executeUpdate("INSERT INTO it_db1.player (player_id, name, password, onMove, fk_team) VALUES(1,'frank','abc',0,null)");
		 String result = db.executeQuery("SELECT * FROM it_db1.player;");
		 System.out.println(result);
		
	}

}
