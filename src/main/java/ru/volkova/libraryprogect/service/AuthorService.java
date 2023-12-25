package ru.volkova.libraryprogect.service;

import ru.volkova.libraryprogect.util.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);
}
