package ru.volkova.libraryprogect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.volkova.libraryprogect.service.BookService;
import ru.volkova.libraryprogect.util.BookDto;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/book")
    BookDto getBookByName(@RequestParam("name") String name){
        return bookService.getByNameV1(name);
    }

    @GetMapping("/book/v2")
    BookDto getBookByNameV2(@RequestParam("name") String name){
        return bookService.getByNameV2(name);
    }

    @GetMapping("book/v3")
    BookDto getBookByNameV3(@RequestParam("name") String name){
        return bookService.getByNameV3(name);
    }


}
