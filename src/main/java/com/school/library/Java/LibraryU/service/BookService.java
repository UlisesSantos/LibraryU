package com.school.library.Java.LibraryU.service;

import com.school.library.Java.LibraryU.model.Books;
import com.school.library.Java.LibraryU.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Retrieves all books
     * @return a list of all books
     * */
    public List<Books> findAllBooks(){
        return bookRepository.findAll();
    }

    /**
     * Retrieves a single book by its ID
     * @param id The ID of the book
     * @return an Optional containing the book if found
     * */
    public Optional<Books> findBookById(Long id){
        return  bookRepository.findById(id);
    }

    /**
     * Retrieves all unavailable books
     * @return a list of unavailable books
     * */
    public List<Books> findUnavailableBooks(){
        return bookRepository.findByAvailability(false);
    }

    /**
     * Retrieves all available books
     * @return a list of available books
     * */
    public List<Books> findAvailableBooks(){
        return bookRepository.findByAvailability(true);
    }

    /**
     * Retrieves a single by its ISBN
     * @param isbn the ISBN of the book
     * @return an Optional containing the book if found
     * */
    public Optional<Books> findBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    /**
     * Retrieves all books by the name
     * @param name the name of the book
     * @return a List of all books with the same name
     */
    public List<Books> findBooksByName(String name){
        return bookRepository.findByName(name);
    }

    /**
     * Retrieves all books by the author
     * @param author the author of the book
     * @return a List of all books with the same author
     */
    public List<Books> findBooksByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    /**
     *
     * @param booksIds
     * @return
     */
    public List<Books> findAllBooksById(List<Long> booksIds){
        return bookRepository.findAllById(booksIds);
    }

    /**
     * Saves a book in to the database
     * @param book the book to save
     * */
    public void saveBook(Books book){
        bookRepository.save(book);
    }

    /**
     * Updates the name of a single book
     * @param id the ID of the book to update
     * @param name the new name to update the book
     */
    public Optional<Books> updateBookName(Long id, String name){
        Optional<Books> newBook = bookRepository.findById(id);
        Books book = null;

        if (newBook.isPresent()){
            book = newBook.get();
            book.setName(name);
            bookRepository.save(book);
        }

        return Optional.ofNullable(book);
    }

    /**
     * Updates the availability of a single book
     * @param id the ID of the book to update
     * @param availability the new availability value to update the book
     */
    public Optional<Books> updateBookAvailability(Long id, Boolean availability){
        Optional<Books> newBook = bookRepository.findById(id);
        Books book = null;

        if (newBook.isPresent()){
            book = newBook.get();
            book.setAvailability(availability);
            bookRepository.save(book);
        }

        return Optional.ofNullable(book);
    }

    /**
     * Updates the author of a single book
     * @param id the ID of the book to update
     * @param author the new author value to update the book
     */
    public Optional<Books> updateBookAuthor(Long id, String author){
        Optional<Books> newBook = bookRepository.findById(id);
        Books book = null;

        if (newBook.isPresent()){
            book = newBook.get();
            book.setAuthor(author);
            bookRepository.save(book);
        }
        return Optional.ofNullable(book);
    }

    /**
     * Updates the ISBN od a single book
     * @param id the ID of the book to update
     * @param isbn the new ISBN value to update the book
     */
    public Optional<Books> updateBookIsbn(Long id, String isbn){
        Optional<Books> newBook = bookRepository.findById(id);
        Books book = null;
        if (newBook.isPresent()){
            book = newBook.get();
            book.setIsbn(isbn);
            bookRepository.save(book);
        }

        return Optional.ofNullable(book);
    }

    /**
     * Deletes a book by its ID
     * @param id the ID of the book to delete
     */
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    /**
     * Deletes a book by its ISBN
     * @param isbn the ISBN of the book to delete
     */
    public void deleteBookByIsbn(String isbn){
        Optional<Books> newBook = bookRepository.findByIsbn(isbn);

        if(newBook.isPresent()){
            Books book = newBook.get();
            bookRepository.deleteById(book.getBookId());
        }
    }
}
