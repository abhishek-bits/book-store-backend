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
public class BaseRequestDto {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Integer fromPages;
    private Integer toPages;
}
