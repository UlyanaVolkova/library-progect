package ru.volkova.libraryprogect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.volkova.libraryprogect.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookControllerMvc {
    private final BookService bookService;

    @GetMapping("/books")
    String getBooksView(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }
}
