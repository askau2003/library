package ek.alss.library.catalog.dto;

import jakarta.validation.constraints.*;

public record AuthorDto(
        Long id,

        @NotBlank(message = "Author must have a name")
        String name) {
}