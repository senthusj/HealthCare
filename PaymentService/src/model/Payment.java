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
	
	
}