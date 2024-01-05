package si.petek.rso.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostServiceRequest {

    private String hotelId;
    private String startDate;
    private String EndDate;
    private String name;
    private String surname;
    private String email;
    private String roomId;
}
