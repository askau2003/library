package ek.alss.library.catalog.dto;

import ek.alss.library.catalog.model.*;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static WorkDto toDto(Work work) {
        List<EditionDto> editions = new ArrayList<>();

        for (var edition : work.getEditions()) {
            editions.add(toDto(edition));
        }

        List<AuthorDto> authors = new ArrayList<>();

        for (var author : work.getAuthors()) {
            authors.add(toDto(author));
        }

        List<SubjectDto> subjects = new ArrayList<>();

        for (var subject : work.getSubjects()) {
            subjects.add(toDto(subject));
        }

        return new WorkDto(
                work.getId(),
                work.getTitle(),
                work.getWorkType(),
                work.getDetails(),
                authors,
                editions,
                subjects
        );
    }

    public static Work toEntity(WorkDto workDto) {
        Work work = new Work();

        work.setId(workDto.id());
        work.setTitle(workDto.title());
        work.setWorkType(workDto.workType());
        work.setDetails(workDto.details());

        for (var authorDto : workDto.authors()) {
            var author = toEntity(authorDto);
            work.addAuthor(author);
        }

        for (var editionDto : workDto.editions()) {
            var edition = toEntity(editionDto);
            work.addEdition(edition);
        }

        for (var subjectDto : workDto.subjects()) {
            var subject = toEntity(subjectDto);
            work.addSubject(subject);
        }

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

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getName()
        );
    }

    public static Author toEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.id());
        author.setName(authorDto.name());
        return author;
    }

    public static SubjectDto toDto(Subject subject) {
        return new SubjectDto(
                subject.getId(),
                subject.getName()
        );
    }

    public static Subject toEntity(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setId(subjectDto.id());
        subject.setName(subjectDto.name());
        return subject;
    }
}
