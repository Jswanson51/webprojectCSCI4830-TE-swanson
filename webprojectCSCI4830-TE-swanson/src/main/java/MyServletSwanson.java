

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServletSwanson
 */
@WebServlet("/MyServletSwanson")
public class MyServletSwanson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServletSwanson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<h1>Hello World! Welcome to the Video Game Recomendation Database.</h1>");
		response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	     
	      out.println("<a href=/webprojectCSCI4830-TE-swanson/searchSwanson.html>Search Games</a> <br>");
	      out.println("</body></html>");
	      out.println("<a href=/webprojectCSCI4830-TE-swanson/insertSwanson.html>Suggest Games</a> <br>");
	      out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
