package com.book_store.service;

import com.book_store.dto.BookDto;
import com.book_store.dto.BaseRequestDto;
import com.book_store.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getBookById(Long id);

    Optional<Book> getBookByIsbn(String isbn);

    List<Book> getBooksByAuthorId(Long authorId);

    List<Book> getAllBooks();

    List<Book> getBooksByRequest(BaseRequestDto bookRequestDto);

    Book addBook(BookDto bookDto);

    Book updateBook(BookDto bookDto);

    Book deleteBookByIsbn(String isbn);

}
