package functions;


import java.util.*;
import java.sql.*;

public class ViewReview extends View {
	static Scanner sc = new Scanner(System.in);
	public static void choice(String url, String uname, String pass, int ch) throws SQLException
	{
		Connection con = null;
		ResultSet rs = null, rs4 =null;
		String q="", gen="", mid, movie="", qra="", qre="";
		int mid1=0;
		
		switch(ch)
		{
			case 1:				
				q="select distinct movie_name from movie where Genre = '";
				System.out.println("Type genre of the movie: ");
				gen = sc.nextLine();
				q = q + gen + "'";
				try {
					rs = res(url, uname, pass, q);
					System.out.println("Movies of '" + gen + "' genre: \n");
					while(rs.next())
					{
						System.out.println("\t" + rs.getString(1));
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}					
				break;
				
			case 2:
				q = "select distinct movie_name from movie ORDER BY Average_rating DESC LIMIT 10";				
				try {
					rs = res(url, uname, pass, q);
					System.out.println("Top 10 Movies Overall:\n");
					while(rs.next())
					{
						System.out.println("\t" + rs.getString(1));
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}	
				break;
				
			case 3:
				q ="select distinct movie_name from movie where Genre = '";
				System.out.println("Type genre of the movie: ");
				gen = sc.nextLine();
				q = q + gen + "' ORDER BY Average_rating DESC LIMIT 10";
				try {
					rs = res(url, uname, pass, q);
					System.out.println("Top 10 Movies of '" + gen + "' genre: \n");
					while(rs.next())
					{
						System.out.println("\t" + rs.getString(1));
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}		
				break;
				
			case 4:
				int i=1;
				q ="select movie.Movie_name, movie.Genre, movie.Cast, movie.Summary, movie.Average_rating, review.Reviews "
						+ "from movie INNER JOIN review ON movie.User_id = review.User_id where movie.Genre = '";
				System.out.println("Type the movie name or genre of the movie:");
				gen = sc.nextLine();
				q = q + gen + "' OR movie.Movie_name = '" + gen + "'";
				try {
					rs = res(url, uname, pass, q);
					
					
					System.out.println("\tMovie\t|\t" + "Genre\t|\t" + "Casts\t|\t" + "Summary\t|\t" + "Average Rating\t|\t" + "Reviews\n");
					while(rs.next())
					{
						movie = rs.getString(1);
						mid="Select Movie_id from movie where Movie_name = '"+ 
								movie +"'";
						rs4 = res(url, uname, pass, mid);
						rs4.next();
						mid1 = rs4.getInt(1);
						rs4=null;
						qre = "Select Reviews from review where Movie_id = " + mid1;
						rs4 = res(url, uname, pass, qre);
						rs4.next();
						
						System.out.println(i + ".\t" + rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) 
								+ "\t\t" + rs.getString(4) + "\t\t" + rs.getFloat(5) + "\t\t" + rs.getString(6));
						while(rs4.next())
						{
							System.out.println("\t\t\t\t\t\t\t\t\t\t\t" + rs4.getString(1));
						}
						i++;
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}		
				break;	
			
			case 5:
				System.out.println("Result:");
				q = "Select Movie_name, Average_rating from movie";
				System.out.println("\tMovie\t|\tAverage Ratings");
				try {
					rs = res(url, uname, pass, q);
					while(rs.next())
					{
						System.out.println("\t" + rs.getString(1) + "\t \t" + rs.getFloat(2));
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			
			default:
				System.out.println("You have given invalid input. End.");
				System.exit(0);
		}
		if(con != null)
			con.close();
	}
}

