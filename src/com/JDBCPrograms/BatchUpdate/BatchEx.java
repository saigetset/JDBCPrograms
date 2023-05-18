package com.JDBCPrograms.BatchUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class BatchEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = null;
		try {
			con = JDBCUtil.getJDBCConnection();
			
			if(con!=null)
				pstmt = con.prepareStatement("Insert into Schools(name,city) values(?,?)");
			if(pstmt!=null) {
				sc = new Scanner(System.in);
				while(true) {
					System.out.print("Enter name of school: ");
					pstmt.setString(1, sc.next());
					System.out.print("Enter name of city: ");
					pstmt.setString(2, sc.next());
					
					pstmt.addBatch();
					
					System.out.print("Do you want to add one more record ?(yes/no): ");
					if(sc.next().equalsIgnoreCase("no"))
						break;
				}
				sc.close();
				
				pstmt.executeBatch();
				
				System.out.println("Records inserted");
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.cleanUp(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
