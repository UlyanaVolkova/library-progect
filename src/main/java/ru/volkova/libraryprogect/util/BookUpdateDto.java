package ru.volkova.libraryprogect.util;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookUpdateDto {
    private Long id;
    @NotBlank(message = "Необходимо указать название")
    private String name;
    @NotBlank(message = "Необходимо указать жанр")
    private Long genreId;
}
