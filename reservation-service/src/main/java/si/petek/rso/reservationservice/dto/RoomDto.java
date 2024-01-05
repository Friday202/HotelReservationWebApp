package si.petek.rso.reservationservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomDto {
    private long id;
    private String roomNumber;
    private List<ReservationDto> reservationDtos;
}