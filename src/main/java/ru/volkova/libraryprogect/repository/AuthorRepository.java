package ru.volkova.libraryprogect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volkova.libraryprogect.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
