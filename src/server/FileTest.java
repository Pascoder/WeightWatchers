package server;

import java.sql.SQLException;

public class FileTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
 ServerModel model = new ServerModel();
 System.out.println(model.CheckLogin("frank", "123"));
	}

}
