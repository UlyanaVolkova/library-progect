package ru.volkova.libraryprogect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.volkova.libraryprogect.service.AuthorService;
import ru.volkova.libraryprogect.util.AuthorDto;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/author")
    AuthorDto getByNameV1(@RequestParam("name") String name){
        return authorService.getByNameV1(name);
    }

    @GetMapping("/author/v2")
    AuthorDto getByNameV2(@RequestParam("name") String name) {
        return authorService.getByNameV2(name);
    }

    @GetMapping("/author/v3")
    AuthorDto betByNameV3(@RequestParam("name") String name){
        return authorService.getByNameV3(name);
    }
}
