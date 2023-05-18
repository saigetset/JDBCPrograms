package com.JDBCPrograms.PreparedStatementExs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class SelectionApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int val;

		try {
			con = JDBCUtil.getJDBCConnection();

			if (con != null) {
				pstmt = con.prepareStatement("Select * from emp where id = ?");
			}
			if (pstmt != null) {
				pstmt.setInt(1, 2);
				rs = pstmt.executeQuery();
			}
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.cleanUp(con, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
