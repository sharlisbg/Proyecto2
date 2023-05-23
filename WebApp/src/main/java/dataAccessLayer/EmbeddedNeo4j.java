/**
 * 
 */
package dataAccessLayer;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.summary.ResultSummary;

import static org.neo4j.driver.Values.parameters;

import java.util.LinkedList;
import java.util.List;
/**
 * @author Administrator
 *
 */
public class EmbeddedNeo4j implements AutoCloseable{

    private final Driver driver;
    

    public EmbeddedNeo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void printGreeting( final String message )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "CREATE (a:Greeting) " +
                                                     "SET a.message = $message " +
                                                     "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
    }
    
    /*public LinkedList<String> getActors()
    {
    	 try ( Session session = driver.session() )
         {
    		 
    		 
    		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
             {
                 @Override
                 public LinkedList<String> execute( Transaction tx )
                 {
                     Result result = tx.run( "MATCH (people:Person) RETURN people.name");
                     LinkedList<String> myactors = new LinkedList<String>();
                     List<Record> registros = result.list();
                     for (int i = 0; i < registros.size(); i++) {
                    	 //myactors.add(registros.get(i).toString());
                    	 myactors.add(registros.get(i).get("people.name").asString());
                     }
                     
                     return myactors;
                 }
             } );
             
             return actors;
         }
    }*/

    /**
     * El metodo getBooks obtiene en un LinkedList los nombres de todos los libros
     * @return un Linked List con los nombres de los libros
     */
    public LinkedList<String> getBooks()
    {
    	 try ( Session session = driver.session() )
         {
    		 
    		 
    		 LinkedList<String> books = session.readTransaction( new TransactionWork<LinkedList<String>>()
             {
                 @Override
                 public LinkedList<String> execute( Transaction tx )
                 {
                     Result result = tx.run( "MATCH (book:Book) RETURN book.name");
                     LinkedList<String> mybooks = new LinkedList<String>();
                     List<Record> registros = result.list();
                     for (int i = 0; i < registros.size(); i++) {
                    	 //myactors.add(registros.get(i).toString());
                    	 mybooks.add(registros.get(i).get("book.name").asString());
                     }
                     
                     return mybooks;
                 }
             } );
             
             return books;
         }
    }

    
    
    /*public LinkedList<String> getMoviesByActor(String actor)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (tom:Person {name: \"" + actor + "\"})-[:ACTED_IN]->(actorMovies) RETURN actorMovies.title");
                    LinkedList<String> myactors = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 myactors.add(registros.get(i).get("actorMovies.title").asString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
   }*/

   /** getBooksbyLastRead es un metodo que recomiendo libros basado en la ultima lectura realizada
    * @param lastread es el nombre de la ultima lectura realizada por el usuario
    * @return un LinkedList con los nombres de las recomendaciones de libros
    */
   public LinkedList<String> getBooksbyLastRead(String lastread)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> books = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    //Result result = tx.run( "MATCH (tom:Person {name: \"" + actor + "\"})-[:ACTED_IN]->(actorMovies) RETURN actorMovies.title");
                    Result result = tx.run( "MATCH(persona:Person) -[HAS_READ]-> (book:Book{name: \"" + lastread + "\"})");
                    LinkedList<String> mybooks = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 mybooks.add(registros.get(i).get("book.name").asString());
                    }
                    
                    return mybooks;
                }
            } );
            
            return books;
        }
   }





   /**
    * getBooksbyEditorial es un metodo que obtiene todos los libros de una editorial en especifico
    * que esten en nuestra base de datos
    * @param editorial es el nombre de la editorial que se desea encontrar
    * @return un LinkedList con los nombres de los libros
    */
   public LinkedList<String> getBooksbyEditorial(String editorial)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> books = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    //Result result = tx.run( "MATCH (tom:Person {name: \"" + actor + "\"})-[:ACTED_IN]->(actorMovies) RETURN actorMovies.title");
                    Result result = tx.run( "MATCH(book:Book {editorial: \"" + editorial + "\"}) RETURN book.name");
                    LinkedList<String> mybooks = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 mybooks.add(registros.get(i).get("book.name").asString());
                    }
                    
                    return mybooks;
                }
            } );
            
            return books;
        }
   }

     
    public String insertMovie(String title, int releaseYear, String tagline) {
    	try ( Session session = driver.session() )
        {
   		 
   		 String result = session.writeTransaction( new TransactionWork<String>()
   		 
            {
                @Override
                public String execute( Transaction tx )
                {
                    tx.run( "CREATE (Test:Movie {title:'" + title + "', released:"+ releaseYear +", tagline:'"+ tagline +"'})");
                    
                    return "OK";
                }
            }
   		 
   		 );
            
            return result;
        } catch (Exception e) {
        	return e.getMessage();
        }
    }

}
