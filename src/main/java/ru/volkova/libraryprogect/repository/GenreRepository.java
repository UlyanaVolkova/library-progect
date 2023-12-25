package ru.volkova.libraryprogect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volkova.libraryprogect.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
