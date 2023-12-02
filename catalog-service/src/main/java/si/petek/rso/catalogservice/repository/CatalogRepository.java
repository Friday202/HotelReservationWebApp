package si.petek.rso.catalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.petek.rso.catalogservice.model.Hotel;

public interface CatalogRepository extends JpaRepository<Hotel, Long> {
}