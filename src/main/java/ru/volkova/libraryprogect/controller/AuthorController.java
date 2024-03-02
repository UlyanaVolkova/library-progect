package ru.volkova.libraryprogect.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.volkova.libraryprogect.service.AuthorService;
import ru.volkova.libraryprogect.util.AuthorCreateDto;
import ru.volkova.libraryprogect.util.AuthorDto;
import ru.volkova.libraryprogect.util.AuthorUpdateDto;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "library-users")
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

    @PostMapping("/author/create")
    AuthorDto createAuthor(@RequestBody @Valid AuthorCreateDto authorCreateDto){
        return authorService.createAuthor(authorCreateDto);
    }

    @PutMapping("/author/update")
    AuthorDto updateAuthor(@RequestBody @Valid AuthorUpdateDto authorUpdateDto){
        return authorService.updateAuthor(authorUpdateDto);
    }

    @DeleteMapping("/author/delete/{id}")
    void deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
    }

}
