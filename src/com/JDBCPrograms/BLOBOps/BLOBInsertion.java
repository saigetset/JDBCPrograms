package com.JDBCPrograms.BLOBOps;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class BLOBInsertion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Scanner sc = new Scanner(System.in);
		
		try {
			con = JDBCUtil.getJDBCConnection();
			
			if(con!=null) {
				pstmt = con.prepareStatement("Insert into Identifications(name,photo) values(?,?)");
			}
			if(pstmt!=null) {
				pstmt.setString(1, "sai");
				pstmt.setBinaryStream(2,new FileInputStream("C:\\Users\\krish\\Desktop\\mypic.jpeg\\"));
				int val = pstmt.executeUpdate();
				System.out.println("No.of rows affected: "+val);
				
				pstmt.setString(1, "siva");
				pstmt.setBinaryStream(2,new FileInputStream("C:\\Users\\krish\\Desktop\\mypic.jpeg\\"));
				val = pstmt.executeUpdate();
				System.out.println("No.of rows affected: "+val);
				
				pstmt.setString(1, "vamsi");
				pstmt.setBinaryStream(2,new FileInputStream("C:\\Users\\krish\\Desktop\\mypic.jpeg\\"));
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
