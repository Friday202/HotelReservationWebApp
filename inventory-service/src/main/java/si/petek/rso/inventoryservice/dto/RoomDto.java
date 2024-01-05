package si.petek.rso.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.petek.rso.inventoryservice.model.Reservation;
import si.petek.rso.inventoryservice.model.Room;

import java.time.LocalDate;
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

    public boolean isRoomAvailable(String startDate, String endDate){
        LocalDate newStartDate = LocalDate.parse(startDate);
        LocalDate newEndDate = LocalDate.parse(endDate);
        for (ReservationDto reservationDto: reservationDtos) {
            if (!(newEndDate.isBefore(reservationDto.getStartDate()) || newStartDate.isAfter(reservationDto.getEndDate()))) {
                // If the new reservation overlaps with any existing reservation, the room is not available
                return false;
            }
        }
        return true;
    }

    public static RoomDto createRoomDtoFromRoom(Room room){
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : room.getReservations()){
            reservationDtos.add(createReservationDtoFromReservation(reservation));
        }
        return new RoomDto(room.getId(), room.getRoomNumber(), reservationDtos);
    }

}
