package ru.volkova.libraryprogect.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.volkova.libraryprogect.model.Author;
import ru.volkova.libraryprogect.repository.AuthorRepository;
import ru.volkova.libraryprogect.util.AuthorDto;
import ru.volkova.libraryprogect.util.BookDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getAuthorById(Long id){
        Author author = authorRepository.findById(id).orElseThrow();
        return convertToDto(author);
    }

    private AuthorDto convertToDto(Author author) {
        //List<BookDto> bookDtoList = author.getBooks()
                //.stream()
               // .map(book -> BookDto.builder()
              //          .genre(book.getGenre().getName())
              //          .name(book.getName())
              //          .id(book.getId())
              //          .build()
              //  ).toList();
        return AuthorDto.builder()
                .books(author.getBooks().stream().map(book -> BookDto.builder().genre(book.getGenre().getName()).name(book.getName()).id(book.getId()).build()).toList())
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    @Override
    public AuthorDto getByNameV1(String name){
        Author author = authorRepository.findAuthorByName(name).orElseThrow();
        return convertToDto(author);
    }

    @Override
    public AuthorDto getByNameV2(String name){
        Author author = authorRepository.findAuthorByNameBtSql(name).orElseThrow();
        return convertToDto(author);
    }

    @Override
    public AuthorDto getByNameV3(String name){
        Specification<Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        });
        Author author = authorRepository.findOne(specification).orElseThrow();
        return convertToDto(author);
    }
}
