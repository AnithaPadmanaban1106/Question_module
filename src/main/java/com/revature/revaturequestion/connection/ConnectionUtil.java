package com.revature.revaturequestion.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() { /// method is used to get DB connection
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/devtask_db";
		String userName = "root";
		String password = "root";
		Connection con = null;
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con, PreparedStatement pst) { /// method is used to close DB connection
		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
