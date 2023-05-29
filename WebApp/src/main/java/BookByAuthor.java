

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
 * Servlet implementation class BookByAuthor
 */
@WebServlet("/BookByAuthor")
public class BookByAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookByAuthor() {
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
	 	
	 	JSONArray LibrosAutor = new JSONArray();
	 	
	 	String myAuthor = request.getParameter("author");
	 	 try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://44.192.100.207:7687", "neo4j", "load-aptitudes-humans" ) )
	        {
			 	LinkedList<String> myauthors = greeter.getBooksbyAuthor(myAuthor);
			 	
			 	for (int i = 0; i < myauthors.size(); i++) {
                      LibrosAutor.add(myauthors.get(i));
			 	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
	 	myResponse.put("conteo", LibrosAutor.size()); //Guardo la cantidad de autores
	 	myResponse.put("libros", LibrosAutor);
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
