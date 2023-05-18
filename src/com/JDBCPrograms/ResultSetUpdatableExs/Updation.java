package com.JDBCPrograms.ResultSetUpdatableExs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class Updation {

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
				
				
				System.out.println("Before Updating the records: ");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				}
				
				rs.beforeFirst();
				while(rs.next()) {
					if(rs.getString(2).equals("siva")) {
						rs.updateString(2,"Siva2");
						rs.updateRow();
					}
				}
				
				System.out.println("After Updating the records: ");
				rs.beforeFirst();
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				}
				
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
