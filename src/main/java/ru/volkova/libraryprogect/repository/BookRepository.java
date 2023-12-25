package ru.volkova.libraryprogect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volkova.libraryprogect.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
