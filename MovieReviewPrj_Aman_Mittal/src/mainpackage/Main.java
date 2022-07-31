package mainpackage;
import functions.*;
import java.sql.SQLException;
import java.util.*;

public class Main extends AddNewUser {
	static Scanner sc=new Scanner(System.in);
	static String q_user = "CREATE TABLE users ("
            + "User_id INT NOT NULL AUTO_INCREMENT, "
            + "User_name VARCHAR(64) NOT NULL, Email_id VARCHAR(64) NOT NULL UNIQUE, PRIMARY KEY (User_id))", 
            
            q_movie = "Create table movie ("
            		+ "Movie_id INT Auto_increment NOT NULL, "
            		+ "Movie_name varchar(100) Not Null, Summary varchar(500), "
            		+ "Genre varchar(200), Cast varchar(200), Average_rating float,"
            		+ "User_id int DEFAULT 1, FOREIGN KEY (User_id) REFERENCES users(User_id), PRIMARY KEY (Movie_id))",

    		q_review = "CREATE TABLE review ("
    	            + "Review_id INT NOT NULL AUTO_INCREMENT, Movie_id INT NOT NULL DEFAULT 1,"
    	            + "Reviews VARCHAR(500),"
    	            + "User_id int DEFAULT 1, FOREIGN KEY (User_id) REFERENCES users(User_id), FOREIGN KEY (Movie_id) REFERENCES movie(Movie_id), PRIMARY KEY (Review_id))",        		
            		
            q_rating = "CREATE TABLE rating ("
    	            + "Rating_id INT NOT NULL AUTO_INCREMENT, Movie_id INT NOT NULL  DEFAULT 1,"
    	            + "Ratings INT, User_id INT DEFAULT 1, Email_id VARCHAR(64) NOT NULL UNIQUE DEFAULT '', "
    	            + "FOREIGN KEY (User_id) REFERENCES users(User_id),"
    	            + "FOREIGN KEY (Email_id) REFERENCES users(Email_id),"
    	            + "FOREIGN KEY (Movie_id) REFERENCES movie(Movie_id), PRIMARY KEY (Rating_id))";
	public static void main(String[] args)  
	{
		String port="3306", user="root", name="AMAN", pass1="password";
		int ch=0;
		char[] pass = null;
		String db_URL = "jdbc:mysql://localhost:", db="aman_movie_review", url="";

		try
		{

			System.out.print("Initializing database...\n");			
			
			System.out.print("Port number: 3306 by default\n");
			
			System.out.print("Database username: root by default ");	
			
			System.out.print("\nEnter your database password: ");			
			pass1 = sc.nextLine();
			
			db_URL+=port+"/";	
			url+=db_URL + db;		
			
			ValidatingDatabase.dbexists(db_URL, user, pass1,db);
			
			System.out.println("Checking tables....");
			ValidatingTables.create(url, user, pass1,q_user);	
			ValidatingTables.create(url, user, pass1,q_movie);
			ValidatingTables.create(url, user, pass1,q_review);	
			ValidatingTables.create(url, user, pass1,q_rating);	
			System.out.println("Database connection successfull! \nPress Enter...");
			sc.nextLine();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	
		System.out.println("If you are an existing user? Type y or n");
		String ch1=sc.next();
		String em="";
		if(ch1.equals("y") || ch1.equals("Y") || ch1.equals("Yes") || ch1.equals("yes")  )
		{	
			sc.nextLine();
			System.out.println("Enter your name: ");
			name = sc.nextLine();
			System.out.println("Enter your email id: ");
			em = sc.next();
			System.out.println("\nVerifying Credentials");
			ViewUser.check(url, user, pass1, em, name);
		}
		else if(ch1.equals("n") || ch1.equals("N") || ch1.equals("NO") || ch1.equals("No"))
		{
			sc.nextLine();
			System.out.println("Enter your name: ");
			name = sc.nextLine();
			System.out.println("Enter your email id: ");
			em=sc.next();
			System.out.println("Creating New ID");
			AddNewUser.users1(url,user,pass1,name,em);
			em = AddNewUser.str;		// if email is modified, str stores the modified email
			System.out.println("Your Login Credentials:\nUsername: "+ name 
					+"\nEmail Id: " + em);
		}
		else
		{
			System.out.println("Your Input is invalid..terminating application!");
			System.exit(0);
		}
		System.out.println("Please press Enter to continue...");
		sc.nextLine();			
		sc.nextLine();			
		
		char cont='y';
		do{
			System.out.println("Type 1 to View or 2 to Edit:");
			ch=sc.nextInt();
			ReadOrAdd obj = new ReadOrAdd();
			try {
				obj.choice(url, user, pass1, em, name, ch);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("\nDo you want to read or rewrite your review? \nIf yes, Type 'y' otherwise type anything");
			sc.nextLine();
			cont = sc.next().charAt(0);
			
			if(!(cont == 'y' || cont=='Y'))
			{
				System.out.println("Bye! We Thank You for using our service.");
				System.gc();
			}
		}while(cont == 'y' || cont=='Y');
	}

}

