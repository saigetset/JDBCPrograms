package SQLInjectionEx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.JDBCPrograms.JDBCUtil.JDBCUtil;

public class SQLInjectionUsingStatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		Scanner sc = new Scanner(System.in);
		
		try {
			con = JDBCUtil.getJDBCConnection();
			
			if(con!=null)
				stmt = con.createStatement();
			if(stmt!=null) {
				System.out.println("Enter name: ");
				String name = sc.next();
				String query = String.format("Select * from emp name='%s'",name);
				rs = stmt.executeQuery(query);
			}
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.cleanUp(con, stmt, rs);
				sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
