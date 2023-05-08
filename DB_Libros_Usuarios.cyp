CREATE (:Book {title: 'Animal Farm', author: 'George Orwell', ranking : 0.0})
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


