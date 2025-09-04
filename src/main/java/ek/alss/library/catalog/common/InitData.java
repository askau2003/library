package ek.alss.library.catalog.common;

import ek.alss.library.catalog.model.Edition;
import ek.alss.library.catalog.model.Publisher;
import ek.alss.library.catalog.model.Work;
import ek.alss.library.catalog.model.WorkType;
import ek.alss.library.catalog.repository.EditionRepository;
import ek.alss.library.catalog.repository.PublisherRepository;
import ek.alss.library.catalog.repository.WorkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InitData implements CommandLineRunner {

    private final WorkRepository workRepository;
    private final EditionRepository editionRepository;
    private final PublisherRepository publisherRepository;

    public InitData(WorkRepository workRepository, EditionRepository editionRepository, PublisherRepository publisherRepository) {
        this.workRepository = workRepository;
        this.editionRepository = editionRepository;
        this.publisherRepository = publisherRepository;
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


        Work gatsby = new Work(
                null,
                "The Great Gatsby",
                WorkType.BOOK,
                "A novel set in the Jazz Age, exploring themes of wealth, love, and the American Dream.",
                "F. Scott Fitzgerald",
                "American literature, 20th century, Jazz Age, Classic",
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

        Work edSheeranReview = new Work(
                null,
                "Massepsykose med Ed Sheeran: 180.000 tager fejl i Tårnby",
                WorkType.ARTICLE,
                "A negatively charged article on Ed Sheerans concert in Taarnby",
                "Thomas Treo",
                "Review, Hate",
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

        Work richAndPoorVideo = new Work(
                null,
                "1000 Players Simulate Civilization: Rich & Poor",
                WorkType.VIDEO,
                "I put 1000 Minecraft players on 2 islands—one island has everything, the other has nothing.",
                "ish",
                "Inequality, Minecraft, Story-telling, Roleplay",
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
