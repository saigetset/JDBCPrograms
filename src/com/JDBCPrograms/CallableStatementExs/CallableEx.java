package com.JDBCPrograms.CallableStatementExs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.CallableStatement;
import com.JDBCPrograms.JDBCUtil.JDBCUtil;

//created a storedProcedure GetAllEmployees() in mysqlWorkbench
public class CallableEx {
	private static final String callableStatementAll = "{CALL GetAllEmployees()}";//SELECT * FROM EMP
	private static final String callableStatementByID = "{CALL P_GET_EMPLOYEE_BY_ID(?,?,?)}";//SELECT NAME,AGE FROM EMP WHERE ID=?
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		CallableStatement cstmt = null;
		CallableStatement cstmt2 = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getJDBCConnection();
			
			if(con!=null) {
				cstmt = con.prepareCall(callableStatementAll);
				cstmt2 = con.prepareCall(callableStatementByID);
			}
			if(cstmt!=null) {
				cstmt.execute();
				rs = cstmt.getResultSet();
			}
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
				}
			}
			
			System.out.println("=================");
			
			if(cstmt2!=null){
				cstmt2.setInt(1,4);
				//registering the datatypes of output values
				cstmt2.registerOutParameter(2, Types.VARCHAR);
				cstmt2.registerOutParameter(3, Types.INTEGER);
				
				cstmt2.execute();
				
				if(cstmt2!=null)
					System.out.println(cstmt2.getString(2)+"  "+cstmt2.getInt(3));
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.cleanUp(con, cstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
