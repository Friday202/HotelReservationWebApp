package si.petek.rso.inventoryservice.service;

import org.springframework.stereotype.Service;
import si.petek.rso.inventoryservice.dto.HotelDto;
import si.petek.rso.inventoryservice.dto.InventoryResponse;
import si.petek.rso.inventoryservice.dto.PostServiceRequest;
import si.petek.rso.inventoryservice.dto.RoomDto;
import si.petek.rso.inventoryservice.inventory.InventoryRepository;
import si.petek.rso.inventoryservice.model.Hotel;
import si.petek.rso.inventoryservice.model.Reservation;
import si.petek.rso.inventoryservice.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static si.petek.rso.inventoryservice.dto.HotelDto.createHotelDtoFromHotel;
import static si.petek.rso.inventoryservice.dto.RoomDto.createRoomDtoFromRoom;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<RoomDto> getAvailableRoomsFromHotel(long hotelId, String startDate, String endDate) {
        // Returns a list of all available rooms given the date in a given hotel
        Optional<Hotel> foundHotel = inventoryRepository.findById(hotelId);
        if (foundHotel.isPresent()){
            HotelDto hotelDto = createHotelDtoFromHotel(foundHotel.get());
            return hotelDto.getAvailableRoomsForDate(startDate, endDate);
        }
        return null;
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

    public void reserveRoom(PostServiceRequest postServiceRequest){

        // post service has information for date so ut that in database
        System.out.println(postServiceRequest.getRoomId());

        Optional<Hotel> foundHotel = inventoryRepository.findById(Long.valueOf(postServiceRequest.getHotelId()));
        if (foundHotel.isPresent()){
            Hotel hotel = foundHotel.get();
            for (Room room : hotel.getRooms()){
                if (room.getId() == Long.valueOf(postServiceRequest.getRoomId())){

                    if (!createRoomDtoFromRoom(room).isRoomAvailable(postServiceRequest.getStartDate(), postServiceRequest.getEndDate())){
                        return;
                    }

                    Reservation newReservation = new Reservation();
                    newReservation.setRoom(room);
                    newReservation.setEndDate(postServiceRequest.getEndDate());
                    newReservation.setStartDate(postServiceRequest.getStartDate());
                    newReservation.setHotel(hotel);

                    room.getReservations().add(newReservation);
                    inventoryRepository.save(hotel);
                    break;
                }
            }
        }
    }

}
