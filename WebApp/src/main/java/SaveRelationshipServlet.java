import dataAccessLayer.EmbeddedNeo4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SaveRelationshipServlet
 */
@WebServlet("/SaveRelationshipServlet")
public class SaveRelationshipServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRelationshipServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject myResponse = new JSONObject();

        JSONArray insertionResult = new JSONArray();
        
        String personName = request.getParameter("personName");
        String bookName = request.getParameter("bookName");
        int rating = Integer.parseInt(request.getParameter("rating"));

        try (EmbeddedNeo4j neo4jDriver = new EmbeddedNeo4j("bolt://44.192.100.207:7687", "neo4j", "load-aptitudes-humans")) 
        {
            String resultado = neo4jDriver.insertRelationshipWithRating(personName, bookName, rating);

            myResponse.put("resultado", resultado);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            myResponse.put("resultado", "Error: " + e.getMessage());
        }

        out.println(myResponse);
        out.flush();
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
