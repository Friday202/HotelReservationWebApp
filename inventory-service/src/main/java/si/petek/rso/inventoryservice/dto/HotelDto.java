package si.petek.rso.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.petek.rso.inventoryservice.model.Hotel;
import si.petek.rso.inventoryservice.model.Room;

import java.util.ArrayList;
import java.util.List;

import static si.petek.rso.inventoryservice.dto.RoomDto.createRoomDtoFromRoom;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HotelDto {
    private long id;
    private List<RoomDto> roomDtos;

    public List<RoomDto> getAvailableRoomsForDate(String startDate, String endDate){
        List<RoomDto> availableRoomDtos = new ArrayList<>();
        for(RoomDto roomDto : roomDtos){
            if (roomDto.isRoomAvailable(startDate, endDate)){
                availableRoomDtos.add(roomDto);
            }
        }
        return  availableRoomDtos;
    }

    public static HotelDto createHotelDtoFromHotel(Hotel hotel){
        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : hotel.getRooms()){
            roomDtos.add(createRoomDtoFromRoom(room));
        }
        return new HotelDto(hotel.getId(), roomDtos);
    }
}
