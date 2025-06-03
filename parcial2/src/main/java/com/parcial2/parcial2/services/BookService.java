package com.parcial2.parcial2.services;

import com.parcial2.parcial2.entities.Book;
import com.parcial2.parcial2.entities.dto.CreateBook;
import com.parcial2.parcial2.entities.dto.ModifyBook;
import com.parcial2.parcial2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(CreateBook book) {
        if (!book.getTitle().matches("^[A-Za-z]+$")) {
            throw new RuntimeException("Invalid title");
        }
        if(!book.getIsbn().matches("^(\\d+-?)+\\d+$")) {
            throw new RuntimeException("Invalid isbn");
        }
        if(book.getPages() < 10){
            throw new RuntimeException("Invalid page number");
        }
        if(book.getPublicationYear() < 1900 || book.getPublicationYear() > Year.now().getValue()){
            throw new RuntimeException("Invalid publication year");
        }
        Book newBook = new Book();
        newBook.setIsbn(book.getIsbn());
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setPublicationYear(book.getPublicationYear());
        newBook.setLanguage(book.getLanguage());
        newBook.setPages(book.getPages());
        newBook.setGenre(book.getGenre());
        bookRepository.save(newBook);
        return newBook;
    }

    public Book findBookByIsbn(String isbn) {
        if (!isbn.matches("^(\\d+-?)+\\d+$")) {
            throw new RuntimeException("Invalid ISBN");
        }
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (!book.isPresent()) {
            throw new RuntimeException("Book not found");
        }
        return book.get();
    }

    public List<Book> findBooksByLanguage(String language) {
        List<Book> languageBooks = bookRepository.findByLanguage(language);

        return languageBooks;
    }

    public List<Book> findBooksByGenre(String genre) {
        List<Book> genreBooks = bookRepository.findByGenre(genre);
        return genreBooks;
    }

    public List<Book> findBooksByPagesBetween(Integer pages1, Integer pages2) {
        List<Book> pagesBook = bookRepository.findByPagesGreaterThanEqualAndPagesLessThanEqual(pages1, pages2);
        return pagesBook;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> authorBooks = bookRepository.findByAuthor(author);
        return authorBooks;
    }
    public String updateBook(ModifyBook book) {
        UUID bookId = UUID.fromString(book.getId());
        Optional<Book> oldBook = bookRepository.findById(bookId);
        if (!oldBook.isPresent()) {
            throw new RuntimeException("Book not found");
        }

        Book modifiedBook = oldBook.get();
        String title = book.getTitle();
        String language = book.getLanguage();
        modifiedBook.setTitle((title == null || title.isEmpty() || title.isBlank()) ? modifiedBook.getTitle() : title);
        modifiedBook.setLanguage((language == null || language.isEmpty() || language.isBlank()) ? modifiedBook.getLanguage() : language);
        bookRepository.save(modifiedBook);
        return "Book modified";
    }

    public String deleteBook(String id) {
        UUID bookId = UUID.fromString(id);
        Optional<Book> oldBook = bookRepository.findById(bookId);
        if (!oldBook.isPresent()) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.delete(oldBook.get());
        return "Book deleted";
    }
}
