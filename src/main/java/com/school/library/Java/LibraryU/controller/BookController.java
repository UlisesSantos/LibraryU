package com.school.library.Java.LibraryU.controller;

import com.school.library.Java.LibraryU.common.LogConfig;
import com.school.library.Java.LibraryU.model.Books;
import com.school.library.Java.LibraryU.service.BookService;
import org.hibernate.boot.model.internal.CreateKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@RestController
@RequestMapping("libraryu/books")
public class BookController {

    // Logger object
    private static final Logger LOGGER = LogManager.getLogger(BookController.class);

    // BookService object
    @Autowired
    private BookService bookService;

    /**
     *
     * @param headers
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllBooks(@RequestHeader HttpHeaders headers){
        LogConfig.initLog4j2();
        List<Books> books = null;
        ResponseEntity<?> respEnt = null;

        LOGGER.info("Getting all books from the DataBase. Headers {}", headers);
        try{
            books = bookService.getAllBooks();

            if(books.isEmpty()){
                respEnt = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            respEnt = new ResponseEntity<List<Books>>(books, HttpStatus.OK);

        }catch(CannotCreateTransactionException e){
            respEnt = new ResponseEntity<>("DataBase Service not Responding",HttpStatus.SERVICE_UNAVAILABLE);
            LOGGER.info(e);
        }

        return respEnt;
    }

    /**
     *
     * @param headers
     * @return
     */
    @GetMapping("/unavailable")
    public ResponseEntity<?> findUnavailableBooks(@RequestHeader HttpHeaders headers){
        ResponseEntity<?> respEnt = null;
        List<Books> books = null;
        try{
            books = bookService.getUnavailableBooks();
            respEnt = new ResponseEntity<List<Books>>(books, HttpStatus.OK);
        }catch (Exception e){
            respEnt = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respEnt;
    }

    /**
     *
     * @param headers
     * @return
     */
    @GetMapping("/available")
    public ResponseEntity<?> findAvailableBooks(@RequestHeader HttpHeaders headers){
        ResponseEntity<?> respEnt = null;
        List<Books> books = null;
        try{
            books = bookService.getAvailableBooks();
            respEnt = new ResponseEntity<List<Books>>(books, HttpStatus.OK);
        }catch (Exception e){
            respEnt = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respEnt;
    }

    /**
     *
     * @param book
     * @return
     */
    @PostMapping
    public ResponseEntity<?> saveBook(@RequestBody Books book){
        LogConfig.initLog4j2();
        LOGGER.info("Registering Book in the DataBase");
        ResponseEntity<?> respEnt = null;

        try{
            if(book == null || book.getIsbn() == null || book.getName() == null){
                return  respEnt = new ResponseEntity<>("Parameters Error", HttpStatus.BAD_REQUEST);
            }
            bookService.saveBook(book);
        } catch (CannotCreateTransactionException e){
            respEnt = new ResponseEntity<>("DataBase Service not Responding",HttpStatus.SERVICE_UNAVAILABLE);
            LOGGER.info(e);
        } catch (DataIntegrityViolationException e){
            respEnt = new ResponseEntity<>("Duplicated Book", HttpStatus.CONFLICT);
            LOGGER.info(e);
        }
        return respEnt = new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param name
     * @return
     */
    @PutMapping("/{id}/{name}")
    public ResponseEntity<?> updateNameBook(@PathVariable Long id, @PathVariable String name){
        ResponseEntity<?> respEnt = null;
        try{
            if(name == null || id == null){
                return  respEnt = new ResponseEntity<>("Parameters Error", HttpStatus.BAD_REQUEST);
            }

            bookService.updateBookName(id, name);
        } catch (Exception e){
            respEnt = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return respEnt;
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBookByIsbn(@PathVariable String isbn){
        ResponseEntity<?> respEnt = null;

        try{

            if(isbn == null){
                return respEnt = new ResponseEntity<>("Parameters Error", HttpStatus.BAD_REQUEST);
            }

            bookService.deleteBookByIsbn(isbn);
        }catch (Exception e){
            respEnt = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return respEnt;
    }
}
