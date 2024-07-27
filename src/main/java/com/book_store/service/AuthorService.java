package com.book_store.service;

import com.book_store.dto.AuthorDto;
import com.book_store.entity.Author;

import java.util.Optional;

public interface AuthorService {

    Optional<Author> getAuthorById(Long id);

    Optional<Author> getAuthorByPhoneAndEmail(String phone, String email);

    Author addAuthor(AuthorDto authorDto);

    Author deleteAuthorById(Long id);
}
