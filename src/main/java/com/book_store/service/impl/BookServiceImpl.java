package com.book_store.service.impl;

import com.book_store.dto.BookDto;
import com.book_store.dto.BaseRequestDto;
import com.book_store.entity.Author;
import com.book_store.entity.Book;
import com.book_store.repository.BookRepository;
import com.book_store.service.AuthorService;
import com.book_store.service.BookService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.of(bookRepository.getReferenceById(id));
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByRequest(BaseRequestDto bookRequestDto) {
        throw new RuntimeException("Functionality does not exist");
    }

    @Transactional // Allow Rollback on failures and avoid partial DB Writes.
    @Override
    public Book addBook(BookDto bookDto) {

        Optional<Book> bookOptional = bookRepository.findBookByIsbn(bookDto.getIsbn());

        if(bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book with Isbn already exists.");
        }

        // If we have not received any info related to author
        // then throw exception
        if(StringUtils.isEmpty(bookDto.getAuthorDto().getPhone()) || StringUtils.isEmpty(bookDto.getAuthorDto().getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author Phone and Email are mandatory.");
        }

        // Check if this author exists.
        Optional<Author> authorOptional = authorService.getAuthorByPhoneAndEmail(
                bookDto.getAuthorDto().getPhone(), bookDto.getAuthorDto().getEmail());

        Author author = authorOptional.orElseGet(() -> authorService.addAuthor(bookDto.getAuthorDto()));

        return bookRepository.save(Book.builder()
                .title(bookDto.getTitle())
                .authorId(author.getId())
                .isbn(bookDto.getIsbn())
                .pages(bookDto.getPages())
                .units(bookDto.getUnits())
                .publishedAt(bookDto.getPublishedAt())
                .build());
    }

    @Transactional // allow rollback on failures and avoid partial DB writes.
    @Override
    public Book updateBook(BookDto bookDto) {

        // We could update any property of book entity
        // OR, we are replacing the current author with a new / already existing one.

        Optional<Book> bookOptional = bookRepository.findBookByIsbn(bookDto.getIsbn());

        Book book = bookOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with Isbn: " + bookDto.getIsbn() + " not found."));

        // If we have not received any info related to author
        // then throw exception
        if(StringUtils.isEmpty(bookDto.getAuthorDto().getPhone()) || StringUtils.isEmpty(bookDto.getAuthorDto().getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author Phone and Email are mandatory.");
        }

        Optional<Author> authorOptional = authorService.getAuthorByPhoneAndEmail(
                bookDto.getAuthorDto().getPhone(), bookDto.getAuthorDto().getEmail());

        if(authorOptional.isPresent()) {
            if(book.getAuthorId() != authorOptional.get().getId()) {
                // Just replace authorId
                book.setAuthorId(authorOptional.get().getId());
            }
        } else {

            // Create new author with the given details.
            Author author = authorService.addAuthor(bookDto.getAuthorDto());

            book.setAuthorId(author.getId());

        }

        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setPages(bookDto.getPages());
        book.setUnits(bookDto.getUnits());
        book.setPublishedAt(bookDto.getPublishedAt());

        return bookRepository.save(book);
    }

    @Override
    public Book deleteBookByIsbn(String isbn) {

        Optional<Book> bookOptional = bookRepository.findBookByIsbn(isbn);

        Book book = bookOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with Isbn: " + isbn + " not found."));

        bookRepository.delete(book);

        return book;
    }
}
