package ek.alss.library.catalog.repository;

import ek.alss.library.catalog.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository <Work, Long> {

    List<Work> findByTitleContaining(String title);
}
