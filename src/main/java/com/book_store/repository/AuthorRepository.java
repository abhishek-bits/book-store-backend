package com.book_store.repository;

import com.book_store.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByPhoneAndEmail(String phone, String email);

    Optional<Author> findByPhoneOrEmail(String phone, String email);
}
