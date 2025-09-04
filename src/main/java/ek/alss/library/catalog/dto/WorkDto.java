package ek.alss.library.catalog.dto;

import ek.alss.library.catalog.model.WorkType;

import java.util.List;

public record WorkDto(Long id, String title, WorkType workType, String details, String author, List<EditionDto> editions, String subjects) {

}