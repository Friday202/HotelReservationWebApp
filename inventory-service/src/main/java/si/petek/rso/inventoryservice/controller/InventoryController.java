package si.petek.rso.inventoryservice.controller;

import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import si.petek.rso.inventoryservice.dto.InventoryResponse;
import si.petek.rso.inventoryservice.healthcheck.InventoryHealthCheck;
import si.petek.rso.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryHealthCheck inventoryHealthCheck;

    public InventoryController(InventoryService inventoryService, InventoryHealthCheck inventoryHealthCheck) {
        this.inventoryService = inventoryService;
        this.inventoryHealthCheck = inventoryHealthCheck;
    }

    @GetMapping("/{hotel_id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse getAllRoomsFromHotel(@PathVariable Long hotel_id){
        return inventoryService.getAllRoomsFromHotel(hotel_id);
    }

    @GetMapping("/{hotel_id}/{room_id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse getRoomAvailability(@PathVariable Long hotel_id, @PathVariable Long room_id){
        // Returns all dates that are taken for a particular room
        return null;
    }

    @PostMapping("/actuator/health/set")
    public String simulateSickService(){
        inventoryHealthCheck.setHealthStatus(Status.DOWN);
        return "Simulating unhealthy state.";
    }
}
