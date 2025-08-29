package ek.alss.library.catalog.common;

import ek.alss.library.catalog.model.Work;
import ek.alss.library.catalog.model.WorkType;
import ek.alss.library.catalog.repository.WorkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private final WorkRepository workRepository;

    public InitData(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Work gatsby = new Work(
                null,
                "The Great Gatsby",
                WorkType.BOOK,
                "A novel set in the Jazz Age, exploring themes of wealth, love, and the American Dream.",
                "F. Scott Fitzgerald",
                "American literature, 20th century, Jazz Age, Classic"
        );
        workRepository.save(gatsby);

        Work edSheeranReview = new Work(
                null,
                "Massepsykose med Ed Sheeran: 180.000 tager fejl i Tårnby",
                WorkType.ARTICLE,
                "A negatively charged article on Ed Sheerans concert in Taarnby",
                "Thomas Treo",
                "Review, Hate"
        );
        workRepository.save(edSheeranReview);

        Work richAndPoorVideo = new Work(
                null,
                "1000 Players Simulate Civilization: Rich & Poor",
                WorkType.VIDEO,
                "I put 1000 Minecraft players on 2 islands—one island has everything, the other has nothing.",
                "ish",
                "Inequality, Minecraft, Story-telling, Roleplay"
        );
        workRepository.save(richAndPoorVideo);
    }
}
