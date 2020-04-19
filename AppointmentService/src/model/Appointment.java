package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/test?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public String insertAppointment(String appointmentid, String doctorid, String doctorName, String patientid, String patientName,
			String hospitalName, String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error nothing in the database.";
			}
			// create a prepared statement
			String query = " insert into appointment(`appointmentid`,`doctorid`,`doctorName`,`patientid`,`patientName`,`hospitalName`,date)"
					+ " values (?, ?, ?, ?, ? ,? ,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, appointmentid);
			//preparedStmt.setString(1, appointmentid);
			preparedStmt.setString(2, doctorid);
			preparedStmt.setString(3, doctorName);
			preparedStmt.setString(4, patientid);
			preparedStmt.setString(5, patientName);
			// preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(6, hospitalName);
			preparedStmt.setString(7, date);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readAppointment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>appointmentid</th><th>doctorid</th><th>doctorName</th><th>patientid</th><th>patientName</th><th>hospitalName</th><th>date</th></tr>";
			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String appointmentid = rs.getString("appointmentid");
				String doctorid = rs.getString("doctorid");
				String doctorName = rs.getString("doctorName");
				String patientid = rs.getString("patientid");
				String patientName = rs.getString("patientName");
				// String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				String hospitalName = rs.getString("hospitalName");
				String date = rs.getString("date");
				// Add into the html table
				output += "<tr><td>" + appointmentid + "</td>";
				output += "<td>" + doctorid + "</td>";
				output += "<td>" + doctorName + "</td>";
				output += "<td>" + patientid + "</td>";
				output += "<td>" + patientName + "</td>";
				output += "<td>" + hospitalName + "</td>";
				output += "<td>" + date + "</td>";
				// buttons
//				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
//						+ "<td><form method=\"post\" action=\"items.jsp\">"
//						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
//						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + appointmentid + "\">"
//						+ "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateAppointment(String appointmentid, String doctorid, String doctorName, String patientid,
			String patientName, String hospitalName, String date)

	{
		String output ="";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE appointment SET doctorid=?, doctorName=?, patientid=?, patientName=?, hospitalName=?, date=?  WHERE appointmentid=?";
			//String query = "UPDATE appointment SET doctorid=?, doctorName=?, patientid=?, patientName=?, hospitalName=?, date=?  WHERE appointmentid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, doctorid);
			preparedStmt.setString(2, doctorName);
			preparedStmt.setString(3, patientid);
			preparedStmt.setString(4, patientName);
			// preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, hospitalName);
			preparedStmt.setString(6, date);
			//preparedStmt.setString(7, appointmentid);
			// preparedStmt.setString(7, appointmentid);
			preparedStmt.setString(7, appointmentid);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating a appointment details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteAppointment(String appointmentid) {
		String output = " ";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from appointment where appointmentid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, appointmentid);
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the appointment detail.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
