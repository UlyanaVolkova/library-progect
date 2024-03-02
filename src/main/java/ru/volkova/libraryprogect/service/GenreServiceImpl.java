package ru.volkova.libraryprogect.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.volkova.libraryprogect.model.Author;
import ru.volkova.libraryprogect.model.Book;
import ru.volkova.libraryprogect.model.Genre;
import ru.volkova.libraryprogect.repository.GenreRepository;
import ru.volkova.libraryprogect.util.AuthorDto;
import ru.volkova.libraryprogect.util.BookDto;
import ru.volkova.libraryprogect.util.GenreDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;


    @Override
    public GenreDto getGenreById(Long id){
        log.info("Try to find genre by id {}", id);
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            GenreDto genreDto = convertToDto(genre.get());
            log.info("Genre: {}", genreDto.toString());
            return genreDto;
        } else {
            log.error("Genre with id {} not found", id);
            throw new NoSuchElementException("No value present");
        }
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
