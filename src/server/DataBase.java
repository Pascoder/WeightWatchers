package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import client.ServiceLocator_JC;

public class DataBase {
	private static DataBase db;
	static Connection connection = null;
	static String databaseName = "it_db1";
	static String url = "jdbc:mysql://localhost:3306/" +databaseName;

	static String username = "itprojekt2020";
	static String password = "itprojekt2020";
	
	
	
public static DataBase getDataBase() {
        if (db == null)
            db = new DataBase();
        return db; 
    }
	
	
//Für Update, Insert, Drop...	
public void executeUpdate(String query) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	connection = DriverManager.getConnection(url,username,password);
	PreparedStatement ps = connection.prepareStatement(query);
	int status = ps.executeUpdate();
	if(status != 0) {
    System.out.println("Database was connected");
    System.out.println("Record was INSERTED");
	}
}

//Für Selects nur zum test
public String executeQuery(String query) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	String output = "";
	ResultSet rs = null;
	
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	connection = DriverManager.getConnection(url,username,password);
	PreparedStatement ps = connection.prepareStatement(query);
	rs = ps.executeQuery();
	
	ResultSetMetaData rsmd = rs.getMetaData();
	int columnsNumber = rsmd.getColumnCount();
	while(rs.next()) {
		for (int i = 1; i <= columnsNumber; i++) {
	        String columnValue = rs.getString(i);
	        output+=rsmd.getColumnName(i)+" = "+columnValue+"\n";
	    }
	}
	return output;
	
	
	
}
}
