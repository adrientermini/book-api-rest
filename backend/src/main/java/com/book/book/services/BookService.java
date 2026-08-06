package com.book.book.services;

import com.book.book.dtos.BookDto;
import com.book.book.entities.BookEntity;
import com.book.book.mappers.BookMapper;
import com.book.book.repositories.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookService {

    @Inject
    private BookRepository bookRepository;

    @Inject
    private BookMapper bookMapper;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<BookDto> getBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::toDto);
    }

    public BookDto createBook(BookDto bookDto) {
        BookEntity book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    public BookDto updateBook(Long id, BookDto bookDetails) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id " + id));

        bookMapper.updateEntityFromDto(bookDetails, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
