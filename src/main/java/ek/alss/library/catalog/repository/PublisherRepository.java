package ek.alss.library.catalog.repository;

import ek.alss.library.catalog.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
