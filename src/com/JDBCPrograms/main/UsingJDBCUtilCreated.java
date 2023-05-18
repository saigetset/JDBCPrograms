package com.JDBCPrograms.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class UsingJDBCUtilCreated {

	public static void main(String[] args) {// throws SQLException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getJDBCConnection();

			if (con != null)
				stmt = con.createStatement();
			if (stmt != null)
				rs = stmt.executeQuery("Select * from emp");
			if (rs != null) {
				while (rs.next()) {
					Integer id = rs.getInt(1);
					String name = rs.getString(2);
					Integer age = rs.getInt(3);
					System.out.println(id + "  " + name + "  " + age);
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.cleanUp(con, stmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
