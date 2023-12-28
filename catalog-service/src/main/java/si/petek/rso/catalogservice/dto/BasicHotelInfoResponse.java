package si.petek.rso.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.petek.rso.catalogservice.model.Hotel;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BasicHotelInfoResponse {
    private Long id;
    private String name;
    private String location;
    private Float userRating;
    public static List<BasicHotelInfoResponse> mapToBasicHotelInfoResponse(List<Hotel> hotels){
        List<BasicHotelInfoResponse> basicHotelInfoResponses = new ArrayList<>();
        for (Hotel hotel : hotels){
            basicHotelInfoResponses.add(createBasicHotelInfoResponse(hotel));
        }
        return basicHotelInfoResponses;
    }
    public static BasicHotelInfoResponse createBasicHotelInfoResponse(Hotel hotel){
        return new BasicHotelInfoResponse(hotel.getId(), hotel.getName(), hotel.getLocation(), hotel.getUserRating());
    }
}
