package model;

import java.sql.*;

public class Hospital { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String hospitalCode, String name, String chiefDoc, String type, int phone,
			String address, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement
			String query = " insert into hospitals (`hospitalID`,`hospitalCode`,`name`,`chiefDoc`,`type`, `phone`, `address`, `desc`)"
					+ " values (?, ?, ?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hospitalCode);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, chiefDoc);
			preparedStmt.setString(5, type);
			preparedStmt.setInt(6, phone);
			preparedStmt.setString(7, address);
			preparedStmt.setString(8, desc);

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Hospital detail.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}