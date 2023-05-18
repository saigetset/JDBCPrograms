package com.JDBCPrograms.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class JDBCUtil {
	
	static {
		// Step-1: Loading and registering the driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getJDBCConnection() throws SQLException, IOException {
		
		//Step-2: Getting the data from properties file and establishing a connection to database
		
		//Specifying all the parameters usually
		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sai","root","root");
		
		//Getting all the parameters from application.properties
		FileInputStream fis = new FileInputStream("D:\\AdvJavaPrograms\\AdvJavaPrograms\\src\\resources\\application.properties");
		Properties props = new Properties();
		props.load(fis);
		Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
		return con;
	}

	public static void cleanUp(Connection con,Statement stmt,ResultSet rs) throws SQLException {
		//Step-6: Closing all the resources utilized
		if(con!=null)
			con.close();
		if(stmt!=null)
			stmt.close();
		if(rs!=null)
			rs.close();
	}

}
