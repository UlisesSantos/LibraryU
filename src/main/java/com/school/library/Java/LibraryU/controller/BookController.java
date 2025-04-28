package com.school.library.Java.LibraryU.controller;

import com.school.library.Java.LibraryU.exception.BookNotFoundException;
import com.school.library.Java.LibraryU.exception.BooksNotFoundException;
import com.school.library.Java.LibraryU.exception.NullOrEmptyParametersException;
import com.school.library.Java.LibraryU.model.Books;
import com.school.library.Java.LibraryU.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("libraryu/books")
public class BookController {

    // Logger object
    private static final Logger LOGGER = LogManager.getLogger(BookController.class);

    // BookService object
    @Autowired
    private BookService bookService;

    /**
     * Endpoint to retrieve all books
     * @return List of books if found
     */
    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks(){
        LOGGER.info("Getting all books from the database");
        List<Books> books = bookService.findAllBooks();
        if(books.isEmpty()) throw new BooksNotFoundException();
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a single book by its ID
     * @param id the ID of the book
     * @return a book object if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id){
        LOGGER.info("Getting a book by its ID");
        if (id == null) throw new NullOrEmptyParametersException();
        Optional<Books> optionalBook = bookService.findBookById(id);
        if(optionalBook.isEmpty()) throw new BookNotFoundException(id);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<Books>(optionalBook.get(), HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve all unavailable books
     * @return List of books if found
     */
    @GetMapping("/unavailable")
    public ResponseEntity<List<Books>> getUnavailableBooks(){
        LOGGER.info("Getting all unavailable books from the database");
        List<Books> books = bookService.findUnavailableBooks();
        if(books.isEmpty()) throw new BooksNotFoundException();
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve all available books
     * @return List of books if found
     */
    @GetMapping("/available")
    public ResponseEntity<List<Books>> getAvailableBooks(){
        LOGGER.info("Getting all available books from the database");
        List<Books> books = bookService.findAvailableBooks();
        if(books.isEmpty()) throw new BooksNotFoundException();
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a book by its ISBN
     * @param isbn the isbn of the book
     * @return a book object if found
     */
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Books> getBookByIsbn(@PathVariable String isbn){
        LOGGER.info("Getting a book by its ISBN");
        if(isbn == null || isbn.isEmpty()) throw new NullOrEmptyParametersException();
        Optional<Books> bookOptional = bookService.findBookByIsbn(isbn);
        if (bookOptional.isEmpty()) throw new BookNotFoundException(isbn);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<Books>(bookOptional.get(), HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve all books by their name
     * @param name the name of the book
     * @return list of the books with the same name
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Books>> getBookByName(@PathVariable String name){
        LOGGER.info("Getting books by their name");
        if(name == null || name.isEmpty()) throw new NullOrEmptyParametersException();
        List<Books> booksList = bookService.findBooksByName(name);
        if (booksList.isEmpty()) throw new BooksNotFoundException(name);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<List<Books>>(booksList, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a list of books by their author
     * @param author the author of the book
     * @return list of books with the same author
     */
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Books>> getBookByAuthor(@PathVariable String author){
        LOGGER.info("Getting books by their author");
        if(author == null || author.isEmpty()) throw new NullOrEmptyParametersException();
        List<Books> booksList = bookService.findBooksByAuthor(author);
        if (booksList.isEmpty()) throw new BooksNotFoundException(author);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<List<Books>>(booksList, HttpStatus.OK);
    }

    /**
     * Endpoint to register a book
     * @param book book to register
     * @return the book registered
     */
    @PostMapping
    public ResponseEntity<Books> saveBook(@RequestBody Books book){
        LOGGER.info("Registering book in the database");
        if(book == null || book.getIsbn() == null || book.getName() == null) throw new NullOrEmptyParametersException();
        bookService.saveBook(book);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * Endpoint to update the name of a book
     * @param id the ID of a book
     * @param name the new name to update the book
     * @return object book updated
     */
    @PutMapping("/name/{id}/{name}")
    public ResponseEntity<Books> updateNameBook(@PathVariable Long id, @PathVariable String name){
        LOGGER.info("Modifying the name of a book");
        if(name == null || name.isEmpty() || id == null) throw new NullOrEmptyParametersException();
        Optional<Books> bookOptional = bookService.updateBookName(id, name);
        if(bookOptional.isEmpty()) throw new BookNotFoundException(id);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<Books>(bookOptional.get(), HttpStatus.OK);
    }

    /**
     * Endpoint to update the availability of a book
     * @param id the ID of a book
     * @param availability the new availability to update the book
     * @return object book updated
     */
    @PutMapping("/availability/{id}/{availability}")
    public ResponseEntity<Books> updateAvailabilityBook(@PathVariable Long id, @PathVariable Boolean availability){
        LOGGER.info("Modifying the availability of a book");
        if(availability == null || id == null) throw new NullOrEmptyParametersException();
        Optional<Books> bookOptional = bookService.updateBookAvailability(id, availability);
        if(bookOptional.isEmpty()) throw new BookNotFoundException(id);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<Books>(bookOptional.get(), HttpStatus.OK);
    }

    /**
     * Endpoint to update the author of a book
     * @param id the ID of a book
     * @param author the new author to update the book
     * @return object book updated
     */
    @PutMapping("/author/{id}/{author}")
    public ResponseEntity<Books> updateAuthorBook(@PathVariable Long id, @PathVariable String author){
        LOGGER.info("Modifying the author of a book");
        if(author == null || author.isEmpty() || id == null) throw new NullOrEmptyParametersException();
        Optional<Books> bookOptional = bookService.updateBookAuthor(id, author);
        if(bookOptional.isEmpty()) throw new BookNotFoundException(id);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<Books>(bookOptional.get(), HttpStatus.OK);
    }

    /**
     * Endpoint to update the ISBN of a book
     * @param id the ID of a book
     * @param isbn the new ISBN to update the book
     * @return object book updated
     */
    @PutMapping("/isbn/{id}/{isbn}")
    public ResponseEntity<Books> updateIsbnBook(@PathVariable Long id, @PathVariable String isbn){
        LOGGER.info("Modifying the isbn of a book");
        if(isbn == null || isbn.isEmpty() || id == null) throw new NullOrEmptyParametersException();
        Optional<Books> bookOptional = bookService.updateBookIsbn(id, isbn);
        if(bookOptional.isEmpty()) throw new BookNotFoundException(id);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<Books>(bookOptional.get(), HttpStatus.OK);
    }

    /**
     * Endpoint to delete a book by its ID
     * @param id the ID of a book
     * @return the eliminated book
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Books> deleteBookById(@PathVariable Long id){
        LOGGER.info("Deleting a book by its ID from the database");
        if(id == null) throw new NullOrEmptyParametersException();
        Optional<Books> bookOptional = bookService.findBookById(id);
        if (bookOptional.isEmpty()) throw new BookNotFoundException(id);
        bookService.deleteBookById(id);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<Books>(bookOptional.get(), HttpStatus.OK);
    }

    /**
     * Endpoint to delete a book by its ISBN
     * @param isbn the ISBN of a book
     * @return the eliminated book
     */
    @DeleteMapping("/isbn/{isbn}")
    public ResponseEntity<Books> deleteBookByIsbn(@PathVariable String isbn){
        LOGGER.info("Deleting a book by its ISBN from the database");
        if(isbn == null || isbn.isEmpty()) throw new NullOrEmptyParametersException();
        Optional<Books> bookOptional = bookService.findBookByIsbn(isbn);
        if(bookOptional.isEmpty()) throw new BookNotFoundException(isbn);
        bookService.deleteBookByIsbn(isbn);
        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<Books>(bookOptional.get(), HttpStatus.OK);
    }
}
