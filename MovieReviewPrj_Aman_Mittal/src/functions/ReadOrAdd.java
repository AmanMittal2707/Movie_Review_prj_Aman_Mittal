package functions;

import java.sql.SQLException;
import java.util.*;
public class ReadOrAdd {
	static Scanner sc = new Scanner(System.in);
	public void choice(String url, String uname, String pass, String email, String name, int ch) throws SQLException
	{
		int ch1=0;

			switch(ch)
			{
				case 1:
					System.out.println("What do you like to view?:\nEnter your choice:\n");
					System.out.println("1. All movies of a specific genre");
					System.out.println("2. Overall top 10 movies"); 
					System.out.println("3. Overall top 10 movies of a specific genre");
					System.out.println("4. Search Movies");
					System.out.println("5. All Eisting Movies");
					ch1=sc.nextInt();
					ViewReview.choice(url, uname, pass, ch1);
					break;
					
				case 2:
					System.out.println("You can ferform the following functions:\nEnter your choice:\n");
					System.out.println("1. Add a movie");
					System.out.println("2. Edit a movie");
					System.out.println("3. Add a review");
					System.out.println("4. Rate a movie");
					ch1=sc.nextInt();
					WriteReview.choice(url, uname, pass, email, name, ch1);
					break;
					
				default:
					System.out.println("You have entered Envalid Input. Session Ended.");
					System.exit(0);
			}
	}
}
