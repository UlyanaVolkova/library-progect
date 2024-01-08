package ru.volkova.libraryprogect.service;

import ru.volkova.libraryprogect.util.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    AuthorDto getByNameV1(String name);

    AuthorDto getByNameV2(String name);

    AuthorDto getByNameV3(String name);
}
