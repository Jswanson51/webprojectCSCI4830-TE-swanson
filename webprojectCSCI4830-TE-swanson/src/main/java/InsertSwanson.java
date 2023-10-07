
/**
 * @file InsertSwanson.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertSwanson")
public class InsertSwanson extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertSwanson() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String gameTitle = request.getParameter("gameTitle");
      String gameRating = request.getParameter("gameRating");
      String gameYear = request.getParameter("gameYear");
      String gameGenre = request.getParameter("gameGenre");
      String singleplayer = request.getParameter("singleplayer");
      String multiplayer = request.getParameter("multiplayer");
      String xbox = request.getParameter("xbox");
      String pc = request.getParameter("PC");
      String playStation = request.getParameter("playStation");
      String ninSwitch = request.getParameter("switch");
      
      Connection connection = null;
      String insertSql = " INSERT INTO MyTableSwansonTE (id, GAMETITLE, GAMERATING, GAMEYEAR, GAMEGENRE, SINGLEPLAYER, MULTIPLAYER, XBOX, PC, PLAYSTATION, SWITCH) values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      try {
         DBConnectionSwanson.getDBConnection();
         connection = DBConnectionSwanson.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, gameTitle);
         preparedStmt.setString(2, gameRating);
         preparedStmt.setString(3, gameYear);
         preparedStmt.setString(4, gameGenre);
         preparedStmt.setString(5, singleplayer);
         preparedStmt.setString(6, multiplayer);
         preparedStmt.setString(7, xbox);
         preparedStmt.setString(8, pc);
         preparedStmt.setString(9, playStation);
         preparedStmt.setString(10, ninSwitch);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Game Info into DB Table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Game Title</b>: " + gameTitle + "\n" + //
            "  <li><b>Game Rating</b>: " + gameRating + "\n" + //
            "  <li><b>Game Year</b>: " + gameYear + "\n" + //
            "  <li><b>Game Genre</b>: " + gameGenre + "\n" + //
            "  <li><b>Singleplayer</b>: " + singleplayer + "\n" + //
            "  <li><b>Multiplayer</b>: " + multiplayer + "\n" + //
            "  <li><b>Xbox</b>: " + xbox + "\n" + //
            "  <li><b>PC</b>: " + pc + "\n" + //
            "  <li><b>PlayStation</b>: " + playStation + "\n" + //
            "  <li><b>Nintendo Switch</b>: " + ninSwitch + "\n" + //
            
            "</ul>\n");

      out.println("<a href=/webprojectCSCI4830-TE-swanson/search_swanson.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
