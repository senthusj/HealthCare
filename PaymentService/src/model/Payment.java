package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	private Connection connect()  
	{  
		Connection con = null; 
	
		try   
		{     
			Class.forName("com.mysql.jdbc.Driver");          
			//Provide the correct details: DBServer/DBName, username, password     
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospitalhealth","root","");   
			}   
		catch (Exception e)
		{e.printStackTrace();} 
		
		return con;  
	}
	
	public String insertpaymentdetails(String paymentID,String CARD_NUMBER, String EXPIRATIONEXP_DATE,String CV_CODE, String COUPON_CODE)  
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
		  if (con == null)
		  {return "Error while connecting to the database for inserting."; } 
	 
		  // create a prepared statement    
		  String query = " insert into payment (`payno`,`paymentID`,`CARD_NUMBER`,`EXPIRATIONEXP_DATE`,`CV_CODE`,`COUPON_CODE`)"      
				  + " values (?, ?, ?, ?, ?, ?)"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values   
	   preparedStmt.setInt(1, 0);
	   preparedStmt.setString(2, paymentID);  
	   preparedStmt.setString(3, CARD_NUMBER);   
	   preparedStmt.setString(4, EXPIRATIONEXP_DATE);    
	   preparedStmt.setString(5, CV_CODE);    
	   preparedStmt.setString(6, COUPON_CODE); 
	   
	   preparedStmt.execute();    
	   con.close(); 
	   
	   output = "Inserted successfully";  
	   }  
	  catch (Exception e)   
	  {    
		  output = "Error while inserting the payment details.";
		  System.err.println(e.getMessage());  
	  } 
	 
	  return output;  
	}
	public String readpaymentdetails()
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)
	   {return "Error while connecting to the database for reading."; } 
	 
	   // Prepare the html table to be displayed   
	   output = "<table border=\"1\"><tr><th>paymentId</th><th>CARD_NUMBER</th><th>EXPIRATIONEXP_DATE</th><th>CV_CODE</th><th>COUPON_CODE</th></tr>"; 
	 
	   String query = "select * from payment";
	   Statement stmt = con.createStatement();
	   ResultSet rs = stmt.executeQuery(query); 
	 
	   // iterate through the rows in the result set    
	   while (rs.next())    
	   {      
		   String paymentID = rs.getString("paymentID");
	   	   String CARD_NUMBER = rs.getString("CARD_NUMBER"); 
		   String EXPIRATIONEXP_DATE=rs.getString("EXPIRATIONEXP_DATE");
		   String CV_CODE = rs.getString("CV_CODE");
		   String COUPON_CODE = rs.getString("COUPON_CODE"); 
	   
	 
	    	// Add into the html table     
	   		output += "<tr>"+"<td>" + paymentID + "</td>"; 
	   		output += "<td>" + CARD_NUMBER  + "</td>";
	   		output += "<td>" + EXPIRATIONEXP_DATE  + "</td>";
	   		output += "<td>" + CV_CODE + "</td>";
	   		output += "<td>" + COUPON_CODE + "</td>"; 
	   		
	   	// buttons     
	   		output +="<input name=\"paymentID\" type=\"hidden\" value=\"" + paymentID      
	   				+ "\">" + "</form></td></tr>"; 
	   		
	   } 
	 
	   con.close(); 
	 
	   // Complete the html table
	   output += "</table>";  
	   }  
		catch (Exception e)   
		{    
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());  
		} 
	 
	  return output;  
	  }
	
	public String updatepaymentdetails(String payno,String paymentID,String CARD_NUMBER , String EXPIRATIONEXP_DATE, String CV_CODE , String COUPON_CODE)
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
		  if (con == null)
		  {return "Error while connecting to the database for updating."; } 
	 
	   // create a prepared statement    
		  String query = "UPDATE payment SET `paymentID`=?,CARD_NUMBER=?,EXPIRATIONEXP_DATE=?,CV_CODE=?,COUPON_CODE=?      WHERE payno=?"; 
	 
		  PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values 
		  preparedStmt.setString(1, paymentID);
		  preparedStmt.setString(2, CARD_NUMBER);
		  preparedStmt.setString(3, EXPIRATIONEXP_DATE);
		  preparedStmt.setString(4, CV_CODE);
		  preparedStmt.setString(5, COUPON_CODE);
		  preparedStmt.setInt(6, Integer.parseInt(payno));
		  
	 
	   // execute the statement    
		  preparedStmt.execute();
		  con.close(); 
	 
	   output = "Updated successfully";
	   }   
	  catch (Exception e)
	  {    output = "Error while updating the payment details.";
	  System.err.println(e.getMessage());   
	  } 
	 
	  return output;  
	  } 
	
	
}