package com.capgemini.bank.utility;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	public Connection dBConnection() throws ClassNotFoundException
	{
		Connection connect=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","capgemini2","root");
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		
		return connect; 
		
	}

	
	
}

