package si.petek.rso.inventoryservice.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import si.petek.rso.inventoryservice.model.Hotel;

public interface InventoryRepository extends JpaRepository<Hotel, Long> {
}
