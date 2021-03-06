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

	public String readHospitals() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Hospital Code</th><th>Hospital Name</th><th>Chief Doc</th><th>Type</th><th>Phone</th><th>Address</th><th>Description</th></tr>";
			String query = "select * from Hospitals";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String hospitalCode = rs.getString("hospitalCode");
				String name = rs.getString("name");
				String chiefDoc = rs.getString("chiefDoc");
				String type = rs.getString("type");
				String phone = Integer.toString(rs.getInt("phone"));
				String address = rs.getString("address");
				String desc = rs.getString("desc");

// Add into the html table
				output += "<tr><td>" + hospitalCode + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + chiefDoc + "</td>";
				output += "<td>" + type + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + desc + "</td>";

// buttons
				output += "<input name=\"hospitalID\" type=\"hidden\" value=\"" + hospitalID + "\">"
						+ "</form></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the hospitals.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateHospital(String ID, String hospitalCode, String name, String chiefDoc, String type,
			String phone, String address, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE Hospitals SET `hospitalCode`=? ,`name`=?, `chiefDoc`=?, `type`=?, `phone`=?, `address`=?, `desc`=? WHERE `hospitalID` = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, hospitalCode);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, chiefDoc);
			preparedStmt.setString(4, type);
			preparedStmt.setInt(5, Integer.parseInt(phone));
			preparedStmt.setString(6, address);
			preparedStmt.setString(7, desc);
			preparedStmt.setInt(8, Integer.parseInt(ID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating a Hospital detail.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospital(String hospitalID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from hospitals where hospitalID =?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(hospitalID));
// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Hospital detail.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}