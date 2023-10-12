import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServletDBSwanson")
public class MyServletDBSwanson extends HttpServlet {
   private static final long serialVersionUID = 1L;
   static String url = "jdbc:mysql://ec2-18-189-170-201.us-east-2.compute.amazonaws.com:3306/MyDBSwansonTE?allowPublicKeyRetrieval=true&useSSL=false";
   static String user = "jenswanson_remote";
   static String password = "csci4830";
   static Connection connection = null;

   public MyServletDBSwanson() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      response.getWriter().println("-------- MySQL JDBC Connection Testing ------------<br>");
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");//("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("Where is your MySQL JDBC Driver?");
         e.printStackTrace();
         return;
      }
      response.getWriter().println("MySQL JDBC Driver Registered!<br>");
      connection = null;
      try {
         connection = DriverManager.getConnection(url, user, password);
      } catch (SQLException e) {
         System.out.println("Connection Failed! Check output console");
         e.printStackTrace();
         return;
      }
      if (connection != null) {
         response.getWriter().println("You made it, take control your database now!<br>");
      } else {
         System.out.println("Failed to make connection!");
      }
      try {
         String selectSQL = "SELECT * FROM MyTableSwansonTE";
        
         response.getWriter().println(selectSQL + "<br>");
         response.getWriter().println("------------------------------------------<br>");
         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
         
         ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()) {
        	 String id = rs.getString("ID"); 
        	 String gameTitle = rs.getString("GAMETITLE"); 
        	 String gameRating = rs.getString("GAMERATING"); 
        	 String gameYear = rs.getString("GAMEYEAR"); 
        	 String gameGenre = rs.getString("GAMEGENRE"); 
        	 String singleplayer = rs.getString("SINGLEPLAYER"); 
        	 String multiplayer = rs.getString("MULTIPLAYER"); 
        	 String xbox = rs.getString("XBOX"); 
        	 String pc = rs.getString("PC");
        	 String playStation = rs.getString("PLAYSTATION"); 
        	 String ninSwitch = rs.getString("SWITCH"); 
        	 response.getWriter().append("ENTRY ID: " + id + ", "); 
        	 response.getWriter().append("GAME TITLE: " + gameTitle + ", "); 
        	 response.getWriter().append("GAME RATING: " + gameRating + ", "); 
        	 response.getWriter().append("GAME YEAR: " + gameYear + ", "); 
        	 response.getWriter().append("GAME GENRE: " + gameGenre + ", "); 
        	 response.getWriter().append("SINGLEPLAYER: " + singleplayer + ", "); 
        	 response.getWriter().append("MULTIPLAYER: " + multiplayer + ", "); 
        	 response.getWriter().append("XBOX: " + xbox + ", "); 
        	 response.getWriter().append("PC: " + pc + ", ");
        	 response.getWriter().append("PLAYSTATION: " + playStation + ", "); 
        	 response.getWriter().append("NINTENDO SWITCH: " + ninSwitch + "<br>");
        	 
        	 }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}