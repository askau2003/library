package ek.alss.library.catalog.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "work")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private WorkType workType;
    private String details;

    @ManyToMany
    private List<Author> authors;

    @ManyToMany
    private List<Subject> subjects;

    @OneToMany(mappedBy = "work")
    private List<Edition> editions = new ArrayList<>();

    public Work() {
    }

    public Work(Long id, String title, WorkType workType, String details, List<Author> authors, List<Subject> subjects, List<Edition> edition) {
        this.id = id;
        this.title = title;
        this.workType = workType;
        this.details = details;
        this.authors = authors;
        this.subjects = subjects;
        this.editions = edition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public void setEditions(List<Edition> editions) {
        this.editions = editions;
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
        edition.setWork(this);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
 }
