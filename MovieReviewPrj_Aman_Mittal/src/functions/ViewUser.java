package functions;


import java.sql.*;

public class ViewUser extends View {
	public static void check(String url, String uname, String pass, String email, String name) {
		Connection con = null;
		String em="";
		try {					
			String q = "SELECT EXISTS(select * from users where email_id ='" + email + "' AND User_name = '" + name +"');";
			ResultSet rs = res(url, uname, pass, q);
			rs.next();
			if(rs.getInt(1)==0 )
			{
				System.out.print("You are not an existing user. \nAdding you into our database...\n");
				AddNewUser.users1(url, uname, pass, name, email);
				em = AddNewUser.str;
				System.out.println("User Added successfully. \nYour Login Credentials: \n\nUsername: "+ name 
						+"\nEmail Id: " + em);
			}
			else
				System.out.println("Verified!");
			if(con!=null)
				con.close();
		} 
		catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}	
}
