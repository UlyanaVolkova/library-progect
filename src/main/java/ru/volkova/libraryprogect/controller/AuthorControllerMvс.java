package ru.volkova.libraryprogect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.volkova.libraryprogect.service.AuthorService;

@Controller
@RequiredArgsConstructor
public class AuthorControllerMvс {
    private final AuthorService authorService;
    @GetMapping("/authors")
    String getAuthorsView(Model model){
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }
}
