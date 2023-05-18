package com.JDBCPrograms.PlayingWithScrollable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class ScrollableExs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner sc = null;
		try {
			con = JDBCUtil.getJDBCConnection();
			
			if(con!=null) {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			}
			if(stmt!=null) {
				rs = stmt.executeQuery("Select * from emp");
				System.out.println("Fetching Records from top to bottom: ");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				}
				
				System.out.println("Fetching Records from bottom to top: ");
				while(rs.previous()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				}
				
				System.out.println("Fetching First Record");
				rs.first();
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				
				System.out.println("Fetching Last Record");
				rs.last();//rs.absolute(-1)
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				
				System.out.println("Fetching Third Record");
				rs.absolute(3);
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				
				System.out.println("Fetching the next Record of the current record");
				rs.relative(1);
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				
				
				rs.last();
				System.out.println("Fetching last but one record using relative ");
				rs.relative(-1);
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));

				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.cleanUp(con, stmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
