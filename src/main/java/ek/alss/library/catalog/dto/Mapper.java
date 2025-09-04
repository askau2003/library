package ek.alss.library.catalog.dto;

import ek.alss.library.catalog.model.Edition;
import ek.alss.library.catalog.model.Publisher;
import ek.alss.library.catalog.model.Work;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static WorkDto toDto(Work work) {
        List<EditionDto> editions = new ArrayList<>();

        for (var edition : work.getEditions()) {
            editions.add(toDto(edition));
        }

        return new WorkDto(
                work.getId(),
                work.getTitle(),
                work.getWorkType(),
                work.getDetails(),
                work.getAuthors(),
                editions,
                work.getSubjects()
        );
    }

    public static Work toEntity(WorkDto workDto) {
        Work work = new Work();

        work.setId(workDto.id());
        work.setTitle(workDto.title());
        work.setWorkType(workDto.workType());
        work.setDetails(workDto.details());
        work.setAuthors(workDto.author());

        for (var editionDto : workDto.editions()) {
            var edition = toEntity(editionDto);
            work.addEdition(edition);
        }

        work.setSubjects(workDto.subjects());

        return work;
    }

    public static EditionDto toDto(Edition edition) {
        return new EditionDto(edition.getId(),
                edition.getEditionNumber(),
                edition.getPublicationYear(),
                edition.getFormat(),
                toDto(edition.getPublisher())
        );
    }

    public static Edition toEntity(EditionDto editionDto) {
        Edition edition = new Edition();
        edition.setId(editionDto.id());
        edition.setEditionNumber(editionDto.editionNumber());
        edition.setPublicationYear(editionDto.year());
        edition.setFormat(editionDto.format());
        edition.setPublisher(toEntity(editionDto.publisher()));

        return edition;
    }

    public static PublisherDto toDto(Publisher publisher) {
        return new PublisherDto(
                publisher.getId(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getContactInfo()
        );
    }

    public static Publisher toEntity(PublisherDto publisherDto) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherDto.id());
        publisher.setName(publisherDto.name());
        publisher.setAddress(publisherDto.address());
        publisher.setContactInfo(publisherDto.contactInfo());
        return publisher;
    }
}
