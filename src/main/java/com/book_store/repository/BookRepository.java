package com.book_store.repository;

import com.book_store.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findBooksByAuthorId(Long authorId);

}
