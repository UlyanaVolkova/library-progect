package ru.volkova.libraryprogect.util;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.volkova.libraryprogect.model.Genre;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookCreateDto {
    @NotBlank(message = "Необходимо указать название")
    private String name;
    @NotBlank(message = "Необходимо указать жанр")
    private Long genreId;
}
