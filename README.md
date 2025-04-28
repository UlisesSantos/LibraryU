# LibraryU - API Documentation

## Description

LibraryU is an API that allows managing a university's book library. 
The API Books provides functionalities to register, update, delete, and query books by various criteria, such as name, author, ISBN, availability, etc.

## Requirements

### JVM Configuration

This project uses Spring Boot, so you need to have your JVM configured properly to run it. You can set the environment variables on your machine as follows:

```bash
-DLOGS_PATH=env/local/logs/ -DINSTANCE_NAME=InstanceLibrary -DPROPERTIES_PATH=env/local/
```

# LibraryU API Books Documentation

This document provides details on the available endpoints for the LibraryU book management system.

## Base URL

All endpoints are prefixed with: `libraryu/books`

## Endpoints

### Get Operations

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| `GET` | `/` | Retrieve all books | None |
| `GET` | `/{id}` | Retrieve a book by ID | `id`: Book ID (Long) |
| `GET` | `/unavailable` | Retrieve all unavailable books | None |
| `GET` | `/available` | Retrieve all available books | None |
| `GET` | `/isbn/{isbn}` | Retrieve a book by ISBN | `isbn`: Book ISBN (String) |
| `GET` | `/name/{name}` | Retrieve books by name | `name`: Book name (String) |
| `GET` | `/author/{author}` | Retrieve books by author | `author`: Author name (String) |

### Post Operations

| Method | Endpoint | Description | Body |
|--------|----------|-------------|------|
| `POST` | `/` | Register a new book | Book object with required fields (isbn, name) |

### Put Operations

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| `PUT` | `/name/{id}/{name}` | Update book name | `id`: Book ID (Long), `name`: New name (String) |
| `PUT` | `/availability/{id}/{availability}` | Update book availability | `id`: Book ID (Long), `availability`: New availability status (Boolean) |
| `PUT` | `/author/{id}/{author}` | Update book author | `id`: Book ID (Long), `author`: New author (String) |
| `PUT` | `/isbn/{id}/{isbn}` | Update book ISBN | `id`: Book ID (Long), `isbn`: New ISBN (String) |

### Delete Operations

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| `DELETE` | `/id/{id}` | Delete a book by ID | `id`: Book ID (Long) |
| `DELETE` | `/isbn/{isbn}` | Delete a book by ISBN | `isbn`: Book ISBN (String) |

## Response Formats

All successful responses return HTTP status code 200 (OK) with the relevant data:
- Single book endpoints return a Book object
- Multiple book endpoints return a List of Book objects

## Error Handling

The API may return the following error responses:

| Error | Description |
|-------|-------------|
| `BookNotFoundException` | When a requested book is not found |
| `BooksNotFoundException` | When no books are found for a given query |
| `NullOrEmptyParametersException` | When required parameters are null or empty |

## Book Object Structure

```json
{
  "id": 1,
  "isbn": "978-3-16-148410-0",
  "name": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "available": true
}
```

## Example Requests

### Get all books
```
GET libraryu/books/
```

### Get a book by ID
```
GET libraryu/books/1
```

### Register a new book
```
POST libraryu/books/
Body:
{
  "isbn": "978-3-16-148410-0",
  "name": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "available": true
}
```

### Update a book's availability
```
PUT libraryu/books/availability/1/false
```

### Delete a book by ISBN
```
DELETE libraryu/books/isbn/978-3-16-148410-0
```
