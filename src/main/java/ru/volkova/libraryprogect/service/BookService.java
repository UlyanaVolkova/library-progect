package ru.volkova.libraryprogect.service;

import ru.volkova.libraryprogect.util.BookDto;

public interface BookService {
    BookDto getByNameV1(String name);

    BookDto getByNameV2(String name);

    BookDto getByNameV3(String name);
}
