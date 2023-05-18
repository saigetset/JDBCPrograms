package com.JDBCPrograms.ConnectionPoolingExample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPoolingEx {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * MysqlConnectionPoolDataSource dataSource = new
		 * MysqlConnectionPoolDataSource();
		 * dataSource.setUrl("jdbc:mysql://localhost:3306/sai");
		 * dataSource.setUser("root"); dataSource.setPassword("root");
		 */
		String configFileLoc = "D:\\AdvJavaPrograms\\AdvJavaPrograms\\src\\resources\\db.properties";
		HikariConfig config = new HikariConfig(configFileLoc);
		try (HikariDataSource dataSource = new HikariDataSource(config)) {
			Connection con  = dataSource.getConnection();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from emp");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
			}
			
			rs.close();
			stmt.close();
			con.close();
		}
	}

}
