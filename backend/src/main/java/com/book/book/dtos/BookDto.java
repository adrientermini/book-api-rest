package com.book.book.dtos;

import com.book.book.enums.GenreEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private GenreEnum genreEnum;
    private Integer releaseYear;
    private AuthorDto author;
}
