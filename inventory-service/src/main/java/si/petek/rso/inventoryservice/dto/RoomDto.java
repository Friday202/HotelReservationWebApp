package si.petek.rso.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.petek.rso.inventoryservice.model.Reservation;
import si.petek.rso.inventoryservice.model.Room;

import java.util.ArrayList;
import java.util.List;

import static si.petek.rso.inventoryservice.dto.ReservationDto.createReservationDtoFromReservation;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomDto {
    private long id;
    private String roomNumber;
    private List<ReservationDto> reservationDtos;

    public static RoomDto createRoomDtoFromRoom(Room room){
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : room.getReservations()){
            reservationDtos.add(createReservationDtoFromReservation(reservation));
        }
        return new RoomDto(room.getId(), room.getRoomNumber(), reservationDtos);
    }

}
