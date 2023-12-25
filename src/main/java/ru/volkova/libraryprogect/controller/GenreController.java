package ru.volkova.libraryprogect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.volkova.libraryprogect.service.GenreService;
import ru.volkova.libraryprogect.util.GenreDto;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre/{id}")
    GenreDto getGenreById(@PathVariable("id") Long id){
        return genreService.getGenreById(id);
    }
}
