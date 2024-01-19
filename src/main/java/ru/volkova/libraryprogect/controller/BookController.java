package ru.volkova.libraryprogect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.volkova.libraryprogect.service.BookService;
import ru.volkova.libraryprogect.util.BookCreateDto;
import ru.volkova.libraryprogect.util.BookDto;
import ru.volkova.libraryprogect.util.BookUpdateDto;

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

    @PostMapping("/book/create")
    BookDto createBook(@RequestBody BookCreateDto bookCreateDto){
        return bookService.createBook(bookCreateDto);
    }

    @PutMapping("/book/update")
    BookDto updateBook(@RequestBody BookUpdateDto bookUpdateDto){
        return bookService.updateBook(bookUpdateDto);
    }

    @DeleteMapping("/book/delete/{id}")
    void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}
