package ru.volkova.libraryprogect.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.volkova.libraryprogect.model.Book;
import ru.volkova.libraryprogect.model.Genre;
import ru.volkova.libraryprogect.repository.BookRepository;
import ru.volkova.libraryprogect.repository.GenreRepository;
import ru.volkova.libraryprogect.util.BookCreateDto;
import ru.volkova.libraryprogect.util.BookDto;
import ru.volkova.libraryprogect.util.BookUpdateDto;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    @Override
    public BookDto getByNameV1(String name){
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto(book);
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
        Book book = bookRepository.findBookByNameBySql(name).orElseThrow();
        return convertEntityToDto(book);
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
}
