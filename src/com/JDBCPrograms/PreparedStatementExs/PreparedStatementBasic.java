package com.JDBCPrograms.PreparedStatementExs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class PreparedStatementBasic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2 =null;
		ResultSet rs=null;
		int val;
		
		try {
			con = JDBCUtil.getJDBCConnection();
			
			if(con!=null) {
				pstmt = con.prepareStatement("Insert into emp(name,age) values(?,?)");
				pstmt2 = con.prepareStatement("Select * from emp");
			}
			if(pstmt!=null) {
				pstmt.setString(1, "siva");
				pstmt.setInt(2, 24);
				val = pstmt.executeUpdate();
				System.out.println("No.of rows executed: "+val);
			}
			if(pstmt2!=null) {
				rs = pstmt2.executeQuery();
			}
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+rs.getString(2)+"  "+rs.getInt(3));
				}
			}
		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.cleanUp(con, pstmt, rs);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
