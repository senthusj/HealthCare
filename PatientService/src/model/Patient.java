package model;

import java.sql.*;

public class Patient { // A common method to connect to the DB
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

	public String addPatientDetails(String patientID, String firstname, String lastName, String gender, String phone,
			String address, String age, String bloodGroup, String nextOfKin) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement

			String query = " insert into patients (`Pno`,`patientID`,`firstname`,`lastName`,`gender`, `phone`, `address`,`age`,`bloodGroup`, `nextOfKin`)"
					+ " values (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = con.prepareStatement(query);

// binding values
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, patientID);
			preparedStatement.setString(3, firstname);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, gender);
			preparedStatement.setString(6, phone);
			preparedStatement.setString(7, address);
			preparedStatement.setString(8, age);
			preparedStatement.setString(9, bloodGroup);
			preparedStatement.setString(10, nextOfKin);

// execute the statement
			preparedStatement.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the patient detail.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
