package si.petek.rso.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReservationDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
}