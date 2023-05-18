package com.JDBCPrograms.CLOBOps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class CLOBOps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Scanner sc = new Scanner(System.in);
		
		try {
			con = JDBCUtil.getJDBCConnection();
			
			if(con!=null) {
				pstmt = con.prepareStatement("Insert into Persons(name,description) values(?,?)");
			}
			if(pstmt!=null) {
				pstmt.setString(1, "sai");
				pstmt.setCharacterStream(2,new BufferedReader(new FileReader("C:\\Users\\krish\\Desktop\\New Text Document.txt\\")));
				int val = pstmt.executeUpdate();
				System.out.println("No.of rows affected: "+val);
				
				pstmt.setString(1, "hari");
				pstmt.setCharacterStream(2,new BufferedReader(new FileReader("C:\\Users\\krish\\Desktop\\hari.txt\\")));
				val = pstmt.executeUpdate();
				System.out.println("No.of rows affected: "+val);
				
				pstmt.setString(1, "siva");
				pstmt.setCharacterStream(2,new BufferedReader(new FileReader("C:\\Users\\krish\\Desktop\\siva.txt\\")));
				val = pstmt.executeUpdate();
				System.out.println("No.of rows affected: "+val);
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
