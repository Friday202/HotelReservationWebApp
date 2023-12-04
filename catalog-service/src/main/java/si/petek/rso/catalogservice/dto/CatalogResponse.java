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
public class CatalogResponse {
    private Long id;
    private String name;
    private String location;
    private Float userRating;

    public static List<CatalogResponse> mapToCatalogResponses(List<Hotel> hotels){
        List<CatalogResponse> catalogResponses = new ArrayList<>();
        for (Hotel hotel : hotels) {
            catalogResponses.add(createCatalogResponseFromHotel(hotel));
        }
        return catalogResponses;
    }

    public static CatalogResponse createCatalogResponseFromHotel(Hotel hotel){
        return new CatalogResponse(hotel.getId(), hotel.getName(),hotel.getLocation(), hotel.getUserRating());
    }

}
