package si.petek.rso.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.petek.rso.catalogservice.model.Hotel;
import si.petek.rso.catalogservice.parser.WeatherDataParser;

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
    private String description;
    private String coordinates;

    private List<String> amenities;


    // Weather data external API call
    private List<String> maxTemp;
    private List<String> minTemp;
    private List<String> weatherConditions;
    private List<String> dates;


    public static List<CatalogResponse> mapToCatalogResponses(List<Hotel> hotels){
        List<CatalogResponse> catalogResponses = new ArrayList<>();
        for (Hotel hotel : hotels) {
            catalogResponses.add(createCatalogResponseFromHotel(hotel, null));
        }
        return catalogResponses;
    }

    public static CatalogResponse createCatalogResponseFromHotel(Hotel hotel, WeatherDataParser weatherData){
        if (weatherData == null){
            List<String> emptyString = new ArrayList<>();
            return new CatalogResponse(hotel.getId(), hotel.getName(),hotel.getLocation(), hotel.getUserRating(),
                    hotel.getDescription(), hotel.getCoordinates(), hotel.getAmenities(), emptyString,
                    emptyString ,emptyString ,emptyString);
        }
        return new CatalogResponse(hotel.getId(), hotel.getName(),hotel.getLocation(), hotel.getUserRating(),
                hotel.getDescription(), hotel.getCoordinates(), hotel.getAmenities(), weatherData.getMaxTemp(),
                weatherData.getMinTemp(), weatherData.getWeatherConditions(), weatherData.getDates());
    }
}
