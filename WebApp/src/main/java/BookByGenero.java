import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

import org.json.simple.JSONArray;

/**
 * Servlet implementation class BookByGenero
 */
@WebServlet("/BookByGenero")
public class BookByGenero extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookByGenero() {
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
	 	
	 	JSONArray LibrosGenre = new JSONArray();
	 	
	 	String myGenre = request.getParameter("genre");
	 	 try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://44.213.125.209:7687", "neo4j", "maintenance-glossary-goal" ) )
	        {
			 	LinkedList<String> mygenrs = greeter.getBooksbyGenero(myGenre);
			 	
			 	for (int i = 0; i < mygenrs.size(); i++) {
                      LibrosGenre.add(mygenrs.get(i));
			 	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
	 	myResponse.put("conteo", LibrosGenre.size()); //Guardo la cantidad de generos extraídos
	 	myResponse.put("Generos", LibrosGenre);
	 	out.println(myResponse);
	 	out.flush();  
	 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
