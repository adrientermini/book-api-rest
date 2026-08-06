package com.book.book.services;

import com.book.book.dtos.AuthorDto;
import com.book.book.entities.AuthorEntity;
import com.book.book.mappers.AuthorMapper;
import com.book.book.repositories.AuthorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthorService {

    @Inject
    private AuthorRepository authorRepository;

    @Inject
    private AuthorMapper authorMapper;

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::toDto).collect(Collectors.toList());
    }

    public Optional<AuthorDto> getAuthorById(Long id) {
        return authorRepository.findById(id).map(authorMapper::toDto);
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        AuthorEntity author = authorMapper.toEntity(authorDto);
        return authorMapper.toDto(authorRepository.save(author));
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDetails) {
        AuthorEntity author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found with id " + id));

        authorMapper.updateEntityFromDto(authorDetails, author);
        return authorMapper.toDto(authorRepository.save(author));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
