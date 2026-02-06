# JBookshelf

JBookshelf is a RESTful Library Manager API built with **Spring Boot**.  
The project is designed as a personal learning exercise.

## Features

- Create, read, update, delete books.
- Partial updates via `PATCH`.
- Full replacement via `PUT`.
- Pagination & sorting.
- Input validation with clear error messages.
- Clean JSON error responses.

## Architecture

The project follows a layered architecture:

```
Controller → Service → Repository → Database
```

## Tech Stack

- Java 17
- Spring Boot 3
  - Spring Web
  - Spring Data JPA
  - Bean Validation
  - H2 Database
- Maven

## DTOs used

| DTO | Purpose |
|----|-------|
| `BookRequest` | Create (POST) |
| `BookReplaceRequest` | Replace (PUT) |
| `BookUpdateRequest` | Partial update (PATCH) |
| `BookResponse` | Output |

## Endpoints

### Create book
```
POST /api/books
```

```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "9780132350884",
  "publishedYear": 2008
}
```

### Get all books (paginated)
```
GET /api/books?page=0&size=10&sort=title,asc
```

### Get book by ID
```
GET /api/books/{id}
```

### Replace book (PUT)
```
PUT /api/books/{id}
```

```json
{
  "title": "Clean Code (2nd Edition)",
  "author": "Robert C. Martin",
  "isbn": "9780132350884",
  "publishedYear": 2026
}
```

### Partial update (PATCH)
```
PATCH /api/books/{id}
```

```json
{
  "title": "Clean Code (Updated)"
}
```

### Delete book
```
DELETE /api/books/{id}
```

## Error Handling

The API uses centralized error handling via `@ControllerAdvice`.

### Example: validation error

```json
{
  "status": 400,
  "error": "Validation failed",
  "errors": {
    "title": "Title must not be empty",
    "publishedYear": "Published year is required"
  }
}
```

### Example: resource not found

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Book not found"
}
```

## Running the project

```bash
mvn spring-boot:run
```

### H2 Console
```
http://localhost:8080/h2-console
```

JDBC URL:
```
jdbc:h2:mem:jbookshelfdb
```

---

## Next improvements
-[ ] PostgreSQL migration
-[ ] Unit & integration testing
-[ ] Authentication & authorization
-[ ] API versioning
-[ ] OpenAPI / Swagger documentation