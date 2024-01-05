package si.petek.rso.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.petek.rso.inventoryservice.model.Reservation;
import java.time.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReservationDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    public static ReservationDto createReservationDtoFromReservation(Reservation reservation){
        return new ReservationDto(reservation.getId(),
                LocalDate.parse(reservation.getStartDate()),
                LocalDate.parse(reservation.getEndDate()));
    }
}
