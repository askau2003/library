package ek.alss.library.catalog.model;

import jakarta.persistence.*;


@Entity
@Table(name = "edition")
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String editionNumber;
    private Integer publicationYear;
    private String format;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;

    public Edition() {
    }

    public Edition(Long id, String editionNumber, Integer publicationYear, String format, Publisher publisher, Work work) {
        this.id = id;
        this.editionNumber = editionNumber;
        this.publicationYear = publicationYear;
        this.format = format;
        this.publisher = publisher;
        this.work = work;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
}