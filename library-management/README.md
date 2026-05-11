# Library Management CRUD App

A Spring Boot REST API for managing a library's book collection, stored locally using an H2 in-memory database.

## Tech Stack
- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- H2 In-Memory Database
- Maven

## Run the Application

```bash
cd library-management
./mvnw spring-boot:run
```

The server starts at `http://localhost:8080`.

To browse the database: open `http://localhost:8080/h2-console`  
(JDBC URL: `jdbc:h2:mem:librarydb`, username: `sa`, password: *(empty)*)

## API Documentation (Swagger / OpenAPI)

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

---

## API Endpoints

### Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| POST | `/api/books` | Add a new book |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |
| GET | `/api/books/search?title=` | Search books by title |
| GET | `/api/books/author?name=` | Search books by author |
| GET | `/api/books/genre/{genre}` | Filter books by genre |
| GET | `/api/books/available` | Get all available books |

---

## Example Requests

### Add a Book
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Spring Boot in Action",
    "author": "Craig Walls",
    "isbn": "978-1617292545",
    "genre": "Technology",
    "publishedYear": 2016,
    "available": true
  }'
```

### Get All Books
```bash
curl http://localhost:8080/api/books
```

### Get Book by ID
```bash
curl http://localhost:8080/api/books/1
```

### Update a Book
```bash
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Spring Boot in Action",
    "author": "Craig Walls",
    "isbn": "978-1617292545",
    "genre": "Technology",
    "publishedYear": 2016,
    "available": false
  }'
```

### Delete a Book
```bash
curl -X DELETE http://localhost:8080/api/books/1
```

### Search by Title
```bash
curl "http://localhost:8080/api/books/search?title=clean"
```

### Get Available Books
```bash
curl http://localhost:8080/api/books/available
```

---

## Book Model

```json
{
  "id": 1,
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "genre": "Technology",
  "publishedYear": 2008,
  "available": true
}
```
