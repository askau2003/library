package ek.alss.library.catalog.repository;

import ek.alss.library.catalog.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditionRepository extends JpaRepository<Edition, Long> {
}
