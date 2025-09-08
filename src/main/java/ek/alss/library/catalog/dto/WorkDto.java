package ek.alss.library.catalog.dto;

import ek.alss.library.catalog.model.WorkType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WorkDto(
        Long id,

        @NotBlank(message = "Work must have a name")
        String title,

        @NotNull(message = "Work must have a worktype")
        WorkType workType,

        @NotBlank(message = "Work must have details")
        String details,

        @Size(min=1, message = "Work must have atleast 1 author")
        List<AuthorDto> authors,

        @Size(min=1, message = "Work must have atleast 1 edition")
        List<EditionDto> editions,

        @Size(min=1, message = "Work must have atleast 1 subject")
        List<SubjectDto> subjects) {

}