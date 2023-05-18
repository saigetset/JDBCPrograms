package com.JDBCPrograms.BLOBOps;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.io.InputStream;
import java.io.FileOutputStream;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class BLOBRetrieval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);

		try {
			con = JDBCUtil.getJDBCConnection();

			if (con != null) {
				pstmt = con.prepareStatement("Select * from Identifications where id = ?");
			}
			if (pstmt != null) {
				pstmt.setInt(1, 2);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					InputStream is = rs.getBinaryStream(3);
					File file = new File("image.jpg");
					FileOutputStream fos = new FileOutputStream(file);
					int i = is.read();
					while(i!=-1) {
						fos.write(i);
						i=is.read();	
					}
					System.out.println(id+"  "+name+"  "+file.getAbsolutePath());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.cleanUp(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
