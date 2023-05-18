package com.JDBCPrograms.DateInsertionAndRetrieval;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class DateRetrieval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2 =null;
		ResultSet rs=null;
		
		try {
			con = JDBCUtil.getJDBCConnection();
			
			if(con!=null)
				pstmt = con.prepareStatement("select * from Students");
			if(pstmt != null) 
				rs = pstmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					java.sql.Date dob = rs.getDate(3);
					java.sql.Date dom = rs.getDate(4);
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					
					System.out.println(id+"  "+name+"  "+sdf.format(dob)+"  "+sdf.format(dom));
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JDBCUtil.cleanUp(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
