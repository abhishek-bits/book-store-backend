package com.book_store.service.impl;

import com.book_store.dto.AuthorDto;
import com.book_store.entity.Author;
import com.book_store.repository.AuthorRepository;
import com.book_store.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> getAuthorByPhoneAndEmail(String phone, String email) {
        return authorRepository.findByPhoneAndEmail(phone, email);
    }

    @Override
    public Author addAuthor(AuthorDto authorDto) {

        Optional<Author> authorOptional = authorRepository.findByPhoneOrEmail(authorDto.getPhone(), authorDto.getEmail());

        if(authorOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author with phone or email already exists");
        }

        return authorRepository.save(Author.builder()
                        .name(authorDto.getName())
                        .phone(authorDto.getPhone())
                        .email(authorDto.getEmail())
                        .dob(authorDto.getDob())
                        .build());
    }

    @Override
    public Author deleteAuthorById(Long id) {
        return null;
    }
}
