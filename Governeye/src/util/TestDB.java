

package util;

import java.sql.*;




public class TestDB {

	private static Connection con = null;
	public static void main(String[] args) {
		
		
		try {

			//Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Database1.accdb");
    System.out.println("CONNECTION ESTABLISHED....");

            if(!con.isClosed())
    			System.out.println("Successfully connected to SQL server");

            Statement stmt = con.createStatement();

            stmt.execute("select * from table1"); // execute query in table student

            ResultSet rs = stmt.getResultSet(); // get any Result that came from our query

            if (rs != null)
             while ( rs.next() ){

                System.out.println("Name: " + rs.getString("FName") + " ID: "+rs.getString("ID") + "  LName: " + rs.getString("LName"));
                }

                stmt.close();
                con.close();
            }
            catch (Exception err) {
                System.out.println("ERROR: " + err);
            }

	}

}
