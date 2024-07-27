package com.book_store.controller;

import com.book_store.dto.ApiResponse;
import com.book_store.dto.BookDto;
import com.book_store.service.BookService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RequestMapping("/api/v1/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    ResponseEntity<ApiResponse> createBook(@RequestBody BookDto bookDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.builder()
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Book created successfully.")
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("book", bookService.addBook(bookDto)))
                        .build());
    }

    @GetMapping("/{isbn}")
    ResponseEntity<ApiResponse> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Book fetched successfully.")
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("book", bookService.getBookByIsbn(isbn)))
                        .build());
    }

    @GetMapping
    ResponseEntity<ApiResponse> getBookByAuthorId(@RequestParam("authorId") Long authorId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Books fetched successfully.")
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("books", bookService.getBooksByAuthorId(authorId)))
                        .build());
    }

    @GetMapping("/list")
    ResponseEntity<ApiResponse> getAllBooks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Books fetched successfully.")
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("books", bookService.getAllBooks( )))
                        .build());
    }

    @DeleteMapping("/{isbn}")
    ResponseEntity<ApiResponse> deleteBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Book deleted successfully.")
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("book", bookService.deleteBookByIsbn(isbn)))
                        .build());
    }

    @PutMapping
    ResponseEntity<ApiResponse> updateBook(@RequestBody BookDto bookDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Book updated successfully.")
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("book", bookService.updateBook(bookDto)))
                        .build());
    }
}
