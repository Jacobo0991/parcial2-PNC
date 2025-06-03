package com.parcial2.parcial2.controller;

import com.parcial2.parcial2.entities.Book;
import com.parcial2.parcial2.entities.dto.CreateBook;
import com.parcial2.parcial2.entities.dto.ModifyBook;
import com.parcial2.parcial2.services.BookService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody CreateBook book){ return ResponseEntity.ok(bookService.createBook(book));}

    @PutMapping
    public ResponseEntity<String> modifyBook(@RequestBody ModifyBook book){ return ResponseEntity.ok(bookService.updateBook(book));}

    @DeleteMapping
    public ResponseEntity<String> deleteBook(@RequestParam String id){ return ResponseEntity.ok(bookService.deleteBook(id));}

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String author, @RequestParam(required = false) String language, @RequestParam(required = false) String genre, @RequestParam(required = false, defaultValue = "0") Integer minPages, @RequestParam(required = false, defaultValue = "0") Integer maxPages) {
        if (author != null){
            return ResponseEntity.ok(bookService.findBooksByAuthor(author));
        }

        if(language != null){
            return ResponseEntity.ok(bookService.findBooksByLanguage(language));
        }

        if(genre != null){
            return ResponseEntity.ok(bookService.findBooksByGenre(genre));
        }
        return ResponseEntity.ok(bookService.findBooksByPagesBetween(minPages, maxPages));
    }
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable("isbn") String isbn){ return ResponseEntity.ok(bookService.findBookByIsbn(isbn));}

}
