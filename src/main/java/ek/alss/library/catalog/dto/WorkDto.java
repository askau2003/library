package ek.alss.library.catalog.dto;

import ek.alss.library.catalog.model.Author;
import ek.alss.library.catalog.model.Subject;
import ek.alss.library.catalog.model.WorkType;

import java.util.List;

public record WorkDto(Long id, String title, WorkType workType, String details, List<AuthorDto> authors, List<EditionDto> editions, List<SubjectDto> subjects) {

}