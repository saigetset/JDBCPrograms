package com.JDBCPrograms.CLOBOps;

import java.io.File;
import java.io.FileReader;
//import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.io.FileWriter;
import org.apache.commons.io.IOUtils;

import java.io.Reader;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class CLOBRetrieval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);

		try {
			con = JDBCUtil.getJDBCConnection();

			if (con != null) {
				pstmt = con.prepareStatement("Select * from Persons where id=?");
			}
			if (pstmt != null) {
				pstmt.setInt(1, 2);
				rs = pstmt.executeQuery();
			}
			if (rs != null) {
				rs.next();
				int id = rs.getInt(1);
				String name = rs.getString(2);

				Reader reader = rs.getCharacterStream(3);
				File file = new File("copy.txt");
				FileWriter writer = new FileWriter(file);

				// copying the data from is to fos
				IOUtils.copy(reader, writer);

				writer.close();
				reader.close();

				System.out.println(id + "\t" + name + "\t" + file.getAbsolutePath());

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
