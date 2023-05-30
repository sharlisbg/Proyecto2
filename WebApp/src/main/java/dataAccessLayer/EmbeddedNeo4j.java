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

    

   /**
    * getBooksbyAuthor es un metodo que obtiene los libros escritos por un escritor particular
    * @param author es el autor del libro
    * @return un Linked List de String con los libros escritos por el autor
    */
   public LinkedList<String> getBooksbyAuthor(String author)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> books = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    //Result result = tx.run( "MATCH (tom:Person {name: \"" + actor + "\"})-[:ACTED_IN]->(actorMovies) RETURN actorMovies.title");
                    Result result = tx.run( "MATCH(libro:Book{author: \"" + author+"\"}) RETURN libro.name");
                    LinkedList<String> mybooks = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 mybooks.add(registros.get(i).get("libro.name").asString());
                    }
                    
                    return mybooks;
                }
            } );
            
            return books;
        }
   }



   /** getBooksbyLastRead es un metodo que recomiendo libros basado en la ultima lectura realizada
    * @param lastread es el nombre de la ultima lectura realizada por el usuario
    * @return un LinkedList con los nombres de las recomendaciones de libros
    */
    public LinkedList<String> getBooksbyLastRead(String lastread) {
        try (Session session = driver.session()) {
            LinkedList<String> books = session.readTransaction(new TransactionWork<LinkedList<String>>() {
                @Override
                public LinkedList<String> execute(Transaction tx) {
                    String query = "MATCH (persona:Person)-[:HAS_READ]->(libro:Book{name:\"" + lastread + "\"}) " +
                            "MATCH (persona)-[rating:HAS_READ]->(librodos:Book) " +
                            "WHERE rating.rating > 3 " +
                            "RETURN librodos";
    
                    Result result = tx.run(query);
                    LinkedList<String> mybooks = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                        mybooks.add(registros.get(i).get("librodos").asString());
                    }
    
                    return mybooks;
                }
            });
    
            return books;
        }
    }





   /**
    * getBooksbyGenero es un metodo que obtiene todos los libros de una genero en especifico
    * que esten en nuestra base de datos
    * @param editorial es el nombre del genero que se desea encontrar
    * @return un LinkedList con los nombres de los libros
    */
   public LinkedList<String> getBooksbyGenero(String genre)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> books = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    //Result result = tx.run( "MATCH (tom:Person {name: \"" + actor + "\"})-[:ACTED_IN]->(actorMovies) RETURN actorMovies.title");
                    Result result = tx.run( "MATCH(book:Book {genre: \"" + genre + "\"}) RETURN book.name");
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
     * insertBook es el metodo que agrega un libro como nodo a la base de datos
     * @param author es el nombre del autor del lubro
     * @param name es el nombre del libro
     * @param editorial es el nombre de la editorial del libro
     * @param genre es el genero del libro
     * @return un String que indica que se realizo exitosamente la adicion
     */
    public String insertBook(String author, String name, String editorial, String genre) {
    	try ( Session session = driver.session() )
        {
   		 
   		 String result = session.writeTransaction( new TransactionWork<String>()
   		 
            {
                @Override
                public String execute( Transaction tx )
                {
                    //tx.run( "CREATE (Test:Movie {title:'" + title + "', released:"+ releaseYear +", tagline:'"+ tagline +"'})");
                    tx.run( "CREATE (book:Book {author:'" + author + "', name:'"+ name +"'', editorial:'"+ editorial +"', genre:'" + genre +"''})");
                    
                    return "OK";
                }
            }
   		 
   		 );
            
            return result;
        } catch (Exception e) {
        	return e.getMessage();
        }
    }

    /**
     * insertPerson es el metodo que ingresa una persona como nodo a la base de datos
     * @param name es el nombre de la persona
     * @return un string que indica que se realizo la insercion
     */
    public String insertPerson(String name) {
    	try ( Session session = driver.session() )
        {
   		 
   		 String result = session.writeTransaction( new TransactionWork<String>()
   		 
            {
                @Override
                public String execute( Transaction tx )
                {
                    //tx.run( "CREATE (Test:Movie {title:'" + title + "', released:"+ releaseYear +", tagline:'"+ tagline +"'})");
                    tx.run( "CREATE (person:Person {name:'" + name + "'})");
                    
                    return "OK";
                }
            }
   		 
   		 );
            
            return result;
        } catch (Exception e) {
        	return e.getMessage();
        }
    }


     /**
     * insertRelationshipWithRating es el metodo que genera una relación entre una persona, el libro que leyó y la calificación que le da
     * @param name es el nombre de la persona
     * @param bookName es el nombre del libro
     * @param rating es el rating que se le da al libro 
     */
    public String insertRelationshipWithRating(String personName, String bookName, int rating) {
    	try ( Session session = driver.session() )
        {
   		 
   		 String result = session.writeTransaction( new TransactionWork<String>()
   		 
            {
                @Override
                public String execute( Transaction tx )
                {
                
                	tx.run("MATCH (p:Person {name: '" +personName+ "'}), (b:Book {name: '"+ bookName +"'}) " +
                            "MERGE (p)-[:HAS_READ {rating: "+rating+"}]->(b)");
                    
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
