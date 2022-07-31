package functions;


import java.sql.*;

public class ValidatingDatabase {
	public static void dbexists(String url, String uname, String pass, String db) throws ClassNotFoundException, SQLException
	{
		Connection con = null;
		
		try {
			System.out.println("\nValidating Database...");			
			 con = DriverManager.getConnection(url, uname, pass);
		    Statement smt = con.createStatement();
		    String sql = "create database if not exists " + db;		    
		    smt.executeUpdate(sql);		    		    
		} 
		catch (SQLException sqlException) {
		    if (sqlException.getErrorCode() == 1007)	
		    {		        
		        System.out.println(sqlException.getMessage());
		    } 
		    else {
		        
		        sqlException.printStackTrace();
		        System.exit(0);
		    }
		}
		finally{
			if(con!=null)
				con.close();
		}
		
		System.out.println("Completed.\n");
		
	}
}

