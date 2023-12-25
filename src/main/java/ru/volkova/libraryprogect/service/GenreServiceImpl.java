package ru.volkova.libraryprogect.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.volkova.libraryprogect.model.Author;
import ru.volkova.libraryprogect.model.Book;
import ru.volkova.libraryprogect.model.Genre;
import ru.volkova.libraryprogect.repository.GenreRepository;
import ru.volkova.libraryprogect.util.AuthorDto;
import ru.volkova.libraryprogect.util.BookDto;
import ru.volkova.libraryprogect.util.GenreDto;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;


    @Override
    public GenreDto getGenreById(Long id){
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
    }

    private GenreDto convertToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .author(book.getAuthors().stream().map(author -> AuthorDto.builder().name(author.getName()).surname(author.getSurname()).id(author.getId()).build()).toList())
                        .build()
                ).toList();
        return GenreDto.builder()
                .books(bookDtoList)
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
