package ek.alss.library.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditionDto(
        Long id,

        @NotBlank(message = "Edition must have a edition number")
        String editionNumber,


        int year,

        @NotBlank(message = "Edition must have a format")
        String format,

        @NotNull(message = "Edition must have a publisher")
        PublisherDto publisher) {

}