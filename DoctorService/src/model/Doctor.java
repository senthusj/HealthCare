package model;

import java.sql.*; 

public class Doctor {

	private Connection connect()
	 {
			 Connection con = null;
			 try
			 {
				 Class.forName("com.mysql.jdbc.Driver");
			
				 //Provide the correct details: DBServer/DBName, username, password
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/doctor", "root", "");
			 }
			 catch (Exception e)
			 {e.printStackTrace();}
			 
			 return con;
	}
	
	public String insertDoctor(String id, String firstname, String lastname, String phone, String desc)
	 {
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 String query = " insert into doc(`Dno`,`Did`,`Dfirstname`,`Dlastname`,`Dphone`,`Ddes`)" + " values(?, ?, ?, ?, ?, ?)";
			
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, id);
			 preparedStmt.setString(3, firstname);
			 preparedStmt.setString(4, lastname);
			 preparedStmt.setInt(5, Integer.parseInt(phone));
			 preparedStmt.setString(6, desc);
			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting the item.";
			 System.err.println(e.getMessage());
		 }
			 return output;
	}
	
	public String readDoctors()
   {
		   String output = "";
		   
		   try
		   {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
			 // Prepare the html table to be displayed
			 output = "<table border=\"1\"><tr><th>Doctor Code</th><th>Doctor First Name</th><th>Doctor Last Name</th><th>Doctor Phone no</th><th>Doctor Description</th></tr>";
			
			 String query = "select * from doc";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
					 String doctorNo = Integer.toString(rs.getInt("Dno"));
					 String doctorCode = rs.getString("Did");
					 String dfirstName = rs.getString("Dfirstname");
					 String dlastName = rs.getString("Dlastname");
					 String dphone = Integer.toString(rs.getInt("Dphone"));
					 String ddes = rs.getString("Ddes");
					 
					 
					 // Add into the html table
					 output += "<tr><td>" + doctorCode + "</td>";
					 output += "<td>" + dfirstName + "</td>";
					 output += "<td>" + dlastName + "</td>";
					 output += "<td>" + dphone + "</td>";
					 output += "<td>" + ddes + "</td>";
					 
					 
			 }
			 
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
		 }
		 catch (Exception e)
		 {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		 }
			 return output;
	}
	public String updateDoctor(String dno, String id, String firstname, String lastname, String phone, String desc)
	{
		String output = "";
		
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE doc SET Did=?, Dfirstname=?, Dlastname=?, Dphone=?, Ddes=? WHERE Dno=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, id);
			 preparedStmt.setString(2, firstname);
			 preparedStmt.setString(3, lastname);
			 preparedStmt.setInt(4, Integer.parseInt(phone));
			 preparedStmt.setString(5, desc);
			 preparedStmt.setInt(6, Integer.parseInt(dno));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
			 
		}
		catch (Exception e)
		{
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
		}
			 return output;
	}
	
	public String deleteDoctor(String dno)
	{
			String output = "";
			try
			 {
					 Connection con = connect();
					 
					 if (con == null)
					 {return "Error while connecting to the database for deleting."; }
					 
					 // create a prepared statement
					 String query = "delete from doc where Dno=?";
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(dno));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
					 output = "Error while deleting the item.";
					 System.err.println(e.getMessage());
			 }
			 return output;
		 }
}
