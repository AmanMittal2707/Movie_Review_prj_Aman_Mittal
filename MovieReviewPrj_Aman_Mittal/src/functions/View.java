package functions;


import java.sql.*;

public class View {
	public static ResultSet res(String url, String uname, String pass, String q) throws SQLException
	{
		Connection con = null;
		ResultSet rs = null;
		try{
			con = DriverManager.getConnection(url, uname, pass);				
			Statement stmt = con.createStatement();

			rs = stmt.executeQuery(q);			
		}
		catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		return rs;
		
		
	}
}
