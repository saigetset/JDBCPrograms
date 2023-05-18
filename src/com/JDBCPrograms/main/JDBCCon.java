package com.JDBCPrograms.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCCon {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sai", "root", "root");
		Statement stmt = con.createStatement();
		/*
		 * //Selection ResultSet rs = stmt.executeQuery("Select * from emp");
		 * 
		 * while(rs.next()) { Integer id=rs.getInt(1); String name = rs.getString(2);
		 * Integer age = rs.getInt(3); System.out.println(id + "  " + name + "  " +age);
		 * }
		 * 
		 * rs.close();
		 */
		/*
		 * //Insertion int a =
		 * stmt.executeUpdate("Insert into emp(id,name,age) values(3,'siva',20)");
		 * System.out.println("No.of rows affected: "+a);
		 */
		/*
		 * // Updation int a =
		 * stmt.executeUpdate("Update emp set name='hari' where id=3");
		 * System.out.println("No.of rows affected: " + a);
		 */
		//Deletion
		int a = stmt.executeUpdate("Delete from emp where id=4");
		System.out.println("No.of rows affected: " + a);
		ResultSet rs = stmt.executeQuery("Select * from emp");

		while (rs.next()) {
			Integer id = rs.getInt(1);
			String name = rs.getString(2);
			Integer age = rs.getInt(3);
			System.out.println(id + "  " + name + "  " + age);
		}

		rs.close();
		stmt.close();
		con.close();
	}

}
