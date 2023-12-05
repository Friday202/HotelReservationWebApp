package si.petek.rso.inventoryservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import si.petek.rso.inventoryservice.dto.InventoryResponse;
import si.petek.rso.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
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
}
