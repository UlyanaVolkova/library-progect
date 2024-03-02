package ru.volkova.libraryprogect.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.volkova.libraryprogect.model.Author;
import ru.volkova.libraryprogect.model.Book;
import ru.volkova.libraryprogect.model.Genre;
import ru.volkova.libraryprogect.repository.BookRepository;
import ru.volkova.libraryprogect.repository.GenreRepository;
import ru.volkova.libraryprogect.util.AuthorDto;
import ru.volkova.libraryprogect.util.BookCreateDto;
import ru.volkova.libraryprogect.util.BookDto;
import ru.volkova.libraryprogect.util.BookUpdateDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    @Override
    public BookDto getByNameV1(String name){
        log.info("Try to find book by name {}", name);
        Optional<Book> book = bookRepository.findBookByName(name);
        if (book.isPresent()) {
            BookDto bookDto = convertEntityToDto(book.get());
            log.info("Book: {}", bookDto.toString());
            return bookDto;
        } else {
            log.error("Book with name {} not found", name);
            throw new NoSuchElementException("No value present");
        }
    }
    private BookDto convertEntityToDto(Book book){
        return BookDto.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
                .build();
    }

    @Override
    public BookDto getByNameV2(String name){
        log.info("Try to find book by name {}", name);
        Optional<Book> book = bookRepository.findBookByName(name);
        if (book.isPresent()) {
            BookDto bookDto = convertEntityToDto(book.get());
            log.info("Book: {}", bookDto.toString());
            return bookDto;
        } else {
            log.error("Book with name {} not found", name);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public BookDto getByNameV3(String name){
        Specification<Book> specification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        });
        Book book = bookRepository.findOne(specification).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto createBook (BookCreateDto bookCreateDto){
        Genre genre = genreRepository.findById(bookCreateDto.getGenreId()).orElseThrow();
        Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
        book.setGenre(genre);
        book = bookRepository.save(book);
        BookDto bookDto = convertEntityToDto(book);
        return bookDto;
    }

    private Book convertDtoToEntity(BookCreateDto bookCreateDto){
        Genre genre = genreRepository.findById(bookCreateDto.getGenreId()).orElseThrow();
        return Book.builder()
                .name(bookCreateDto.getName())
                .genre(genre)
                .build();
    }

    @Override
    public BookDto updateBook(BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow();
        Genre genre = genreRepository.findById(bookUpdateDto.getGenreId()).orElseThrow();
        book.setName(bookUpdateDto.getName());
        book.setGenre(genre);
        Book savedBook = bookRepository.save(book);
        BookDto bookDto = convertEntityToDto(savedBook);
        return bookDto;
    }

    @Override
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
}
