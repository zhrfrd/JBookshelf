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














## Database Configuration Guide

This project supports two databases:
- H2 for local development and learning.
- PostgreSQL for production setups.

Spring profiles are used to switch between them.

### H2 Configuration (Development)

#### Configuration file

`application-h2.yml`

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:jbookshelfdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ""

  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true

  h2:
    console:
      enabled: true
      path: /h2-console
```

#### Running with H2

```bash
mvn spring-boot:run -Dspring.profiles.active=h2
```

#### H2 Console

```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:jbookshelfdb
```

### PostgreSQL Configuration (Production)

#### 1 Install PostgreSQL

##### macOS (Homebrew)

```bash
brew install postgresql
brew services start postgresql
```

##### Docker

```bash
docker run --name jbookshelf-postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:16
```

#### 2 Create Database and User

Connect as an admin user:

```bash
psql -U postgres
```

Then run:

```sql
CREATE ROLE jbookshelf WITH LOGIN PASSWORD 'secret';
CREATE DATABASE jbookshelf OWNER jbookshelf;
GRANT ALL PRIVILEGES ON DATABASE jbookshelf TO jbookshelf;
```

Exit:

```sql
\q
```

#### 3 PostgreSQL Configuration File

`application-postgres.yml`

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/jbookshelf
    username: jbookshelf
    password: secret

  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: false
```

#### 4 Activate PostgreSQL Profile

##### Option A — via `application.yml`

```yaml
spring:
  profiles:
    active: postgres
```

##### Option B — via command line

```bash
mvn spring-boot:run -Dspring.profiles.active=postgres
```

## Environment Variables

Database credentials are not committed to the repository.

The application reads PostgreSQL connection details from environment variables which allows 
the same codebase to be used safely across local development, CI, and production environments.

### Required variables (PostgreSQL)

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

### Example (local development)

Write on your terminal:

```bash
export DB_URL=jdbc:postgresql://localhost:5132/myUrl
export DB_USERNAME=myUsername
export DB_PASSWORD=myPassword
```

Then run the app:

```
mvn spring-boot:run
```

## Next improvements
- [ ] PostgreSQL migration
- [ ] Unit & integration testing
- [ ] Authentication & authorization
- [ ] API versioning
- [ ] OpenAPI / Swagger documentation