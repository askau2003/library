package ek.alss.library.catalog.dto;

import jakarta.validation.constraints.NotBlank;

public record PublisherDto(
        Long id,

        @NotBlank(message = "Publisher must have a name")
        String name,

        @NotBlank(message = "Publisher must have an address")
        String address,

        @NotBlank(message = "Publisher must have contact info")
        String contactInfo) {

}