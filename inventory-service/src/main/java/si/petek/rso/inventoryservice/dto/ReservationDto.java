package si.petek.rso.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.petek.rso.inventoryservice.model.Reservation;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReservationDto {
    private Long id;
    private String startDate;
    private String endDate;

    public static ReservationDto createReservationDtoFromReservation(Reservation reservation){
        return new ReservationDto(reservation.getId(), reservation.getStartDate(), reservation.getEndDate());
    }
}
