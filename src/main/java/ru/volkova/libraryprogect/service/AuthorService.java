package ru.volkova.libraryprogect.service;

import ru.volkova.libraryprogect.util.AuthorCreateDto;
import ru.volkova.libraryprogect.util.AuthorDto;
import ru.volkova.libraryprogect.util.AuthorUpdateDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    AuthorDto getByNameV1(String name);

    AuthorDto getByNameV2(String name);

    AuthorDto getByNameV3(String name);

    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);

    AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);

    void deleteAuthor(Long id);
}
