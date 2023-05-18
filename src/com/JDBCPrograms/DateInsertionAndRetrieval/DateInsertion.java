package com.JDBCPrograms.DateInsertionAndRetrieval;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class DateInsertion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2 =null;
		ResultSet rs=null;
		java.util.Date utildob=null;
		int val;
		
		try {
			con = JDBCUtil.getJDBCConnection();
			
			if(con!=null)
				pstmt = con.prepareStatement("Insert into Students(name,DOB,DOM) values(?,?,?)");
			if(pstmt != null) {
				pstmt.setString(1,"sai");
				//formatting user-defined date format to util.Date and then to sql.Date
				String dob = "30-09-1994";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				utildob = sdf.parse(dob);
				long l = utildob.getTime();
				java.sql.Date sqldob = new java.sql.Date(l);
				
				pstmt.setDate(2, sqldob);
				//if user entered format is yyyy-mm-dd, then we can directly convert into sql.Date
				String dom="2028-05-09";
				java.sql.Date sqldom = java.sql.Date.valueOf(dom);
				pstmt.setDate(3, sqldom);
				
				val = pstmt.executeUpdate();
				System.out.println("No.of rows affected: "+val);
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
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
