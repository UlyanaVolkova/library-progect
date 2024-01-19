package ru.volkova.libraryprogect.service;

import ru.volkova.libraryprogect.util.BookCreateDto;
import ru.volkova.libraryprogect.util.BookDto;
import ru.volkova.libraryprogect.util.BookUpdateDto;

public interface BookService {
    BookDto getByNameV1(String name);

    BookDto getByNameV2(String name);

    BookDto getByNameV3(String name);

    BookDto createBook(BookCreateDto bookCreateDto);
    BookDto updateBook(BookUpdateDto bookUpdateDto);
    void deleteBook(Long id);
}
