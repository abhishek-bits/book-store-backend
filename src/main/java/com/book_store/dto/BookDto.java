package com.book_store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String isbn;
    private String title;
    private Integer pages;
    private Integer units;
    private LocalDateTime publishedAt;
    private AuthorDto authorDto;
}
