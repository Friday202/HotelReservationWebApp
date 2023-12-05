package si.petek.rso.inventoryservice.service;

import org.springframework.stereotype.Service;
import si.petek.rso.inventoryservice.dto.HotelDto;
import si.petek.rso.inventoryservice.dto.InventoryResponse;
import si.petek.rso.inventoryservice.inventory.InventoryRepository;
import si.petek.rso.inventoryservice.model.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static si.petek.rso.inventoryservice.dto.HotelDto.createHotelDtoFromHotel;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryResponse getAllRoomsFromHotel(Long hotel_id){
        // Returns list of all rooms in a given hotel
        Optional<Hotel> foundHotel = inventoryRepository.findById(hotel_id);
        if (foundHotel.isPresent()){
            List<HotelDto> hotelDtos = new ArrayList<>();
            HotelDto hotelDto = createHotelDtoFromHotel(foundHotel.get());
            hotelDtos.add(hotelDto);
            return new InventoryResponse(hotelDtos);
        }
        return null;
    }
}
