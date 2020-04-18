package server;


import java.sql.SQLException;

public class DataBaseTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DataBase db = new DataBase();
		
		//db.executeUpdate("INSERT INTO it_db1.player (player_id, name, password, onMove, fk_team) VALUES(1,'frank','abc',0,null)");
		String result = DataBase.getDataBase().executeQuery("SELECT * FROM it_db1.player WHERE name = 'frank' AND password = '1234';");
		 if(result.length()>0) {
			 System.out.println("ok");
		 }else {
			 System.out.println("nicht ok");
		 }
		
	}

}
