import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchSwanson")
public class SearchSwanson extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SearchSwanson() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword");
      search(keyword, response);
   }

   void search(String keyword, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "You Should Try These Games:";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<style>\n" +
            "body { font-family: Arial, sans-serif; }\n" +
            "h1 { font-family: 'Times New Roman', serif; }\n" +
            "ul { list-style-type: disc; text-align: left; }\n" +
            "</style>\n" +
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"left\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnectionSwanson.getDBConnection();
         connection = DBConnectionSwanson.connection;

         if (keyword.isEmpty()) {
            String selectSQL = "SELECT * FROM MyTableSwansonTE";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
            String selectSQL = "SELECT * FROM MyTableSwansonTE WHERE GAMEGENRE LIKE ?";
            String genre = "%" + keyword + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1,genre);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String gameTitle = rs.getString("gametitle").trim();
            String gameRating = rs.getString("gamerating").trim();
            String gameYear = rs.getString("gameyear").trim();
            String gameGenre = rs.getString("gameGenre").trim();
            String singleplayer = rs.getString("singleplayer").trim();
            String multiplayer = rs.getString("multiplayer").trim();
            String xbox = rs.getString("xbox").trim();
            String pc = rs.getString("pc").trim();
            String playStation = rs.getString("playstation").trim();
            String ninSwitch = rs.getString("Switch").trim();
            
            if (keyword.isEmpty() || gameGenre.contains(keyword)) {
               out.println("Suggestion ID: " + id + ", " + "<br>");
               out.println("Game Title: " + gameTitle + ", " + "<br>");
               out.println("Game Rating: " + gameRating + ", " + "<br>");
               out.println("Game Year: " + gameYear + ", " + "<br>");
               out.println("Game Genre: " + gameGenre + ", " + "<br>");
               out.println("Singleplayer?: " + singleplayer + ", " + "<br>");
               out.println("Multiplayer?: " + multiplayer + ", " + "<br>");
               out.println("Xbox: " + xbox + ", " + "<br>");
               out.println("PC: " + pc + ", " + "<br>");
               out.println("PlayStation: " + playStation + ", " + "<br>");
               out.println("Nintendo Switch: " + ninSwitch + "<br>" + "<br>");
            }
         }
         out.println("<a href=/webprojectCSCI4830-TE-swanson/searchSwanson.html>Search Data</a> <br>");
         out.println("</body></html>");
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
