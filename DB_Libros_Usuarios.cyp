CREATE (:Book {title: 'Animal Farm', author: 'George Orwell', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y la Piedra Filosofal', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y la Cámara Secreta', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y el Prisionero de Azkaban', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y el Cáliz de Fuego', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y la Orden del Fénix', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y el Misterio del Príncipe', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Harry Potter y las Reliquias de la Muerte', author: 'J.K. Rowling', ranking : 0.0})
CREATE (:Book {title: 'Cien años de soledad', author: 'Gabriel García Márquez', ranking : 0.0});

MATCH (book:Book {title: 'Animal Farm'})
CREATE (:Genre {name: 'Fábula política'})-[:HAS_BOOK]->(book);

MATCH (book:Book {title: 'Harry Potter y la Piedra Filosofal'})
CREATE (:Genre {name: 'Fantasía'})-[:HAS_BOOK]->(book);

MATCH (book:Book {title: 'Harry Potter y la Cámara Secreta'})
CREATE (:Genre {name: 'Fantasía'})-[:HAS_BOOK]->(book);

MATCH (book:Book {title: 'Harry Potter y el Prisionero de Azkaban'})
CREATE (:Genre {name: 'Fantasía'})-[:HAS_BOOK]->(book);

MATCH (book:Book {title: 'Harry Potter y el Cáliz de Fuego'})
CREATE (:Genre {name: 'Fantasía'})-[:HAS_BOOK]->(book);

MATCH (book:Book {title: 'Harry Potter y la Orden del Fénix'})
CREATE (:Genre {name: 'Fantasía'})-[:HAS_BOOK]->(book);

MATCH (book:Book {title: 'Harry Potter y el Misterio del Príncipe'})
CREATE (:Genre {name: 'Fantasía'})-[:HAS_BOOK]->(book);

MATCH (book:Book {title: 'Harry Potter y las Reliquias de la Muerte'})
CREATE (:Genre {name: 'Fantasía'})-[:HAS_BOOK]->(book);

MATCH (book:Book {title: 'Cien años de soledad'})
CREATE (:Genre {name: 'Realismo mágico'})-[:HAS_BOOK]->(book);



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

MATCH (book:Book)
WITH book, [(user)-[r:READ]->(book) WHERE r.ranking IS NOT NULL | r.ranking] AS rankings
WHERE size(rankings) > 0
SET book.ranking = REDUCE(sum = 0.0, r in rankings | sum + r) / size(rankings)
RETURN book.title, book.ranking
ORDER BY book.ranking DESC;


