CREATE (:Book {name: 'Animal Farm', author: 'George Orwell', editorial : 'Penguin', genre: 'Ficcion'})
CREATE (:Book {name: 'La Fiesta del Chivo', author: 'Mario Vargas Llosa', editorial : 'Penguin', genre: 'Novela Historica'})
CREATE (:Book {name: 'Tiempos Recios', author: 'Mario Vargas Llosa', editorial : 'Penguin', genre: 'Novela Historica'})
CREATE (:Book {name: 'La Casa Verde', author: 'Mario Vargas Llosa', editorial : 'Penguin', genre: 'Novela'})
CREATE (:Book {name: 'Carta al Padre', author: 'Franz Kafka', editorial : 'Penguin', genre: 'No Ficcion'})
CREATE (:Book {name: 'La Metamorfosis', author: 'Franz Kafka', editorial : 'Penguin', genre: 'Novela Corta'})
CREATE (:Book {name: 'El Nombre de la Rosa', author: 'Umberto Eco', editorial : 'Penguin', genre: 'Novela Historica'})
CREATE (:Book {name: '100 años de Soledad', author: 'Gabriel Garcia Marquez', editorial : 'Diana', genre: 'Ficcion'})
CREATE (:Book {name: 'El Pensamiento de Socrates', author: 'A.E. Taylor', editorial : 'Fondo de Cultura Economica', genre: 'Filosofia'})
CREATE (:Book {name: 'Gorgias and Timaeus', author: 'Plato', editorial : 'PDover', genre: 'Filosofia'})
CREATE (:Book {name: 'The Republic', author: 'Plato', editorial : 'Dover', genre: 'Filosofia'})
CREATE (:Book {name: 'Six Great Dialogues', author: 'Plato', editorial : 'Dover', genre: 'Filosofia'})
CREATE (:Book {name: 'Cartas a Lucilio', author: 'Seneca', editorial : 'Ariel', genre: 'Filosofia'})
CREATE (:Book {name: 'Manual de Vida', author: 'Epicteto', editorial : 'Alianza', genre: 'Filosofia'})
CREATE (:Book {name: 'Un Mundo Feliz', author: 'Aldo Huxley', editorial : 'Penguin', genre: 'Novela'})
CREATE (:Book {name: 'Narciso y Goldmundo', author: 'Herman Hesse', editorial : 'Seix-Barral', genre: 'Novela'})
CREATE (:Book {name: 'Luz de Agosto', author: 'Willaim Faulkner', editorial : 'Seix-Barral', genre: 'Novela'})
CREATE (:Book {name: 'La Nausea', author: 'Jean-Paul Sartre', editorial : 'Seix-Barral', genre: 'Filosofia'})
CREATE (:Book {name: 'Crimen y Castigo', author: 'Fyodor Dostoievski', editorial : 'Austral', genre: 'Novela'})
CREATE (:Book {name: 'Ana Karenina', author: 'Leo Tolstoi', editorial : 'Penguin', genre: 'Novela'})
CREATE (:Book {name: 'El Conde de Montecristo', author: 'Alexandre Dumas', editorial : 'Penguin', genre: 'Novela'})
CREATE (:Book {name: 'Los Tres Mosqueteros', author: 'Alexandre Dumas', editorial : 'Penguin', genre: 'Novela'})
CREATE (:Book {name: 'El Lazarillo de Tormes', author: 'Desconocido', editorial : 'Penguin', genre: 'Picaresca'})
CREATE (:Book {name: 'El Hidalgo Don Quijote de la Mancha', author: 'Miguel de Cervantes Saavedra', editorial : 'Mestas', genre: 'Novela'})

























CREATE (:Book {title: 'Harry Potter y la Piedra Filosofal', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y la Cámara Secreta', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y el Prisionero de Azkaban', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y el Cáliz de Fuego', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y la Orden del Fénix', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y el Misterio del Príncipe', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y las Reliquias de la Muerte', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Cien años de soledad', author: 'Gabriel García Márquez', ranking : 0.0})
CREATE (:Genre {name: 'Fábula política'})
CREATE (:Genre {name: 'Fantasía'})
CREATE (:Genre {name: 'Realismo mágico'});

MATCH (book:Book {title: 'Animal Farm'})
MATCH (genre:Genre {name: 'Fábula política'})
CREATE (book)-[:BELONG_TO]->(genre);

MATCH (book:Book {title: 'Harry Potter y la Piedra Filosofal'})
MATCH (genre:Genre {name: 'Fantasía'})
CREATE (book)-[:BELONG_TO]->(genre);

MATCH (book:Book {title: 'Harry Potter y la Cámara Secreta'})
MATCH (genre:Genre {name: 'Fantasía'})
CREATE (book)-[:BELONG_TO]->(genre);

MATCH (book:Book {title: 'Harry Potter y el Prisionero de Azkaban'})
MATCH (genre:Genre {name: 'Fantasía'})
CREATE (book)-[:BELONG_TO]->(genre);

MATCH (book:Book {title: 'Harry Potter y el Cáliz de Fuego'})
MATCH (genre:Genre {name: 'Fantasía'})
CREATE (book)-[:BELONG_TO]->(genre);

MATCH (book:Book {title: 'Harry Potter y la Orden del Fénix'})
MATCH (genre:Genre {name: 'Fantasía'})
CREATE (book)-[:BELONG_TO]->(genre);

MATCH (book:Book {title: 'Harry Potter y el Misterio del Príncipe'})
MATCH (genre:Genre {name: 'Fantasía'})
CREATE (book)-[:BELONG_TO]->(genre);

MATCH (book:Book {title: 'Harry Potter y las Reliquias de la Muerte'})
MATCH (genre:Genre {name: 'Fantasía'})
CREATE (book)-[:BELONG_TO]->(genre);

MATCH (book:Book {title: 'Cien años de soledad'})
MATCH (genre:Genre {name: 'Realismo mágico'})
CREATE (book)-[:BELONG_TO]->(genre);


CREATE (Sharis:User {name:'Sharis'}),
(Leonel:User {name:'Leonel'}),
(Allan:User {name:'Allan'}), 
(Pedro:User {name:'Pedro'});


MATCH (book:Book)
WHERE book.title IN ['Harry Potter y la Piedra Filosofal', 'Harry Potter y la Cámara Secreta', 'Harry Potter y el Prisionero de Azkaban', 'Harry Potter y el Cáliz de Fuego']
MATCH (user:User)
WHERE user.name = 'Sharis'
CREATE (user)-[:READ {ranking:4}]->(book);

MATCH (book:Book)
WHERE book.title IN ['Harry Potter y la Piedra Filosofal', 'Harry Potter y la Cámara Secreta']
MATCH (user:User)
WHERE user.name = 'Leonel'
CREATE (user)-[:READ {ranking:5}]->(book);

MATCH (book:Book)
WHERE book.title IN ['Cien años de soledad']
MATCH (user:User)
WHERE user.name = 'Allan'
CREATE (user)-[:READ {ranking:4}]->(book);

MATCH (book:Book)
WHERE book.title IN ['Cien años de soledad']
MATCH (user:User)
WHERE user.name = 'Pedro'
CREATE (user)-[:READ {ranking:3}]->(book);

MATCH (u1:User {name: 'Leonel'}), (u2:User {name: 'Sharis'})
CREATE (u1)-[:FOLLOWS]->(u2);
MATCH (u3:User {name: 'Allan'}), (u4:User {name: 'Pedro'})
CREATE (u3)-[:FOLLOWS]->(u4);

MATCH (book:Book)
WITH book, [(user)-[r:READ]->(book) WHERE r.ranking IS NOT NULL | r.ranking] AS rankings
WHERE size(rankings) > 0
SET book.ranking = REDUCE(sum = 0.0, r in rankings | sum + r) / size(rankings)
RETURN book.title, book.ranking
ORDER BY book.ranking DESC;


