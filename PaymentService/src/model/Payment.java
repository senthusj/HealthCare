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
	
}