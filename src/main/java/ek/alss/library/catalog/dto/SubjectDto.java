package ek.alss.library.catalog.dto;

import jakarta.validation.constraints.NotBlank;

public record SubjectDto(
        Long id,

        @NotBlank(message = "Subject must have a name")
        String name) {
}
