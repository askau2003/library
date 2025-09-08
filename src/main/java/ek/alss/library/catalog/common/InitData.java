package ek.alss.library.catalog.common;

import ek.alss.library.catalog.model.*;
import ek.alss.library.catalog.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private final WorkRepository workRepository;
    private final EditionRepository editionRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final SubjectRepository subjectRepository;

    public InitData(WorkRepository workRepository, EditionRepository editionRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository, SubjectRepository subjectRepository) {
        this.workRepository = workRepository;
        this.editionRepository = editionRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // The Great Gatsby
        Publisher scribner = new Publisher(
                null,
                "Charles Scribner's Sons",
                "597 Fifth Avenue, Borough of Manhattan",
                "info@scribners.com"
        );
        publisherRepository.save(scribner);

        Author fScott = new Author(
                null,
                "F. Scott Fitzgerald"
        );
        authorRepository.save(fScott);

        Subject americanLiterature = new Subject(null, "American literature");
        Subject twentiethCentury = new Subject(null, "20th century");
        Subject jazzAge = new Subject(null, "Jazz Age");
        Subject classic = new Subject(null, "Classic");
        subjectRepository.saveAll(List.of(americanLiterature, twentiethCentury, jazzAge, classic));

        Work gatsby = new Work(
                null,
                "The Great Gatsby",
                WorkType.BOOK,
                "A novel set in the Jazz Age, exploring themes of wealth, love, and the American Dream.",
                List.of(fScott),
                List.of(americanLiterature,twentiethCentury,jazzAge,classic),
                new ArrayList<>()
        );
        workRepository.save(gatsby);

        Edition gatsbyEdition = new Edition(
                null,
                "First",
                1925,
                "Hardcover",
                scribner,
                gatsby
        );
        gatsby.getEditions().add(gatsbyEdition);

        editionRepository.save(gatsbyEdition);
        workRepository.save(gatsby);





        // Ed Sheeran Review - Thomas Treo
        Publisher ekstraBladet = new Publisher(
                null,
                "Ekstra Bladet",
                "Rådhuspladsen 37, 1785 København",
                "1224@eb.dk"
        );
        publisherRepository.save(ekstraBladet);

        Author thomasTreo = new Author(
                null,
                "Thomas Treo"
        );
        authorRepository.save(thomasTreo);

        Subject review = new Subject(null, "Review");
        Subject hate = new Subject(null, "Hate");
        subjectRepository.saveAll(List.of(review,hate));

        Work edSheeranReview = new Work(
                null,
                "Massepsykose med Ed Sheeran: 180.000 tager fejl i Tårnby",
                WorkType.ARTICLE,
                "A negatively charged article on Ed Sheerans concert in Taarnby",
                List.of(thomasTreo),
                List.of(review,hate),
                new ArrayList<>()
        );
        workRepository.save(edSheeranReview);

        Edition edSheeranEdition = new Edition(
                null,
                "First",
                2025,
                "E-Article",
                ekstraBladet,
                edSheeranReview
        );
        edSheeranReview.getEditions().add(edSheeranEdition);

        editionRepository.save(edSheeranEdition);

        workRepository.save(edSheeranReview);





        // Peak youtube video
        Publisher ish = new Publisher(
                null,
                "Ish",
                "USA",
                "ish13c@gmail.com"
        );
        publisherRepository.save(ish);

        Author ishAuthor = new Author(
                null,
                "ish"
        );
        authorRepository.save(ishAuthor);

        Subject inequality = new Subject(null,"Inequality");
        Subject minecraft = new Subject(null,"Minecraft");
        Subject storyTelling = new Subject(null, "Story-telling");
        Subject rolePlay = new Subject(null,"Roleplay");
        subjectRepository.saveAll(List.of(inequality,minecraft,storyTelling,rolePlay));

        Work richAndPoorVideo = new Work(
                null,
                "1000 Players Simulate Civilization: Rich & Poor",
                WorkType.VIDEO,
                "I put 1000 Minecraft players on 2 islands—one island has everything, the other has nothing.",
                List.of(ishAuthor),
                List.of(inequality,minecraft,storyTelling,rolePlay),
                new ArrayList<>()
        );
        workRepository.save(richAndPoorVideo);

        Edition ishEdition = new Edition(
                null,
                "First",
                2025,
                "Video",
                ish,
                richAndPoorVideo
        );
        richAndPoorVideo.getEditions().add(ishEdition);

        editionRepository.save(ishEdition);

        workRepository.save(richAndPoorVideo);
    }
}
