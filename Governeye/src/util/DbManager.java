package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import util.monitoringMail;



public class DbManager
{
	private static Connection con = null;
	private static Connection conn = null;
	
	
	// Database connection for SQL Server
	public static void setDbConnection() throws SQLException, ClassNotFoundException, AddressException, MessagingException
	{
		try{
		Class.forName(TestConfig.driver);
		con =	DriverManager.getConnection(TestConfig.dbConnectionUrl);
		
		if(!con.isClosed())
			System.out.println("Successfully connected to SQL server");
			
	}catch(Exception e){
		System.err.println("Exception: " + e.getMessage());
		monitoringMail mail = new monitoringMail();
        mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject+" - (Script failed with Error, SQL database connection not established)", TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);			
		}
		
		
	}
	

	
	
	
	// Query list for SQL
		
	public static List<String> getQuery(String query) throws SQLException{
		
		
		Statement St = con.createStatement();
		ResultSet rs = St.executeQuery(query);
		List<String> values = new ArrayList<String>();
		while(rs.next()){
		
			values.add(rs.getString(1));
			
		}
		return values;
	}
	
	
	// Query list for MySQL
	
	
	
	
	
	
	
	
	
	
	public static Connection getConnection()
	{
		return con;
			}
}
