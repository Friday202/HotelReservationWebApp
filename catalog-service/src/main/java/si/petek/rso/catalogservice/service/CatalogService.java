package si.petek.rso.catalogservice.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import si.petek.rso.catalogservice.dto.BasicHotelInfoResponse;
import si.petek.rso.catalogservice.dto.CatalogResponse;
import si.petek.rso.catalogservice.model.Hotel;
import si.petek.rso.catalogservice.parser.WeatherDataParser;
import si.petek.rso.catalogservice.repository.CatalogRepository;

import java.util.List;
import java.util.Optional;

import static si.petek.rso.catalogservice.dto.BasicHotelInfoResponse.mapToBasicHotelInfoResponse;
import static si.petek.rso.catalogservice.dto.CatalogResponse.createCatalogResponseFromHotel;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;
    private final RestTemplate restTemplate;

    public CatalogService(CatalogRepository catalogRepository, RestTemplate restTemplate) {
        this.catalogRepository = catalogRepository;
        this.restTemplate = restTemplate;
    }

    public List<BasicHotelInfoResponse> getAllHotelsBasicInfo(){
        return mapToBasicHotelInfoResponse(catalogRepository.findAll());
    }

    public CatalogResponse getHotelByID(Long id){
        Optional<Hotel> foundHotel = catalogRepository.findById(id);
        CatalogResponse catalogResponse = new CatalogResponse();
        if (foundHotel.isPresent()){
            catalogResponse = createCatalogResponseFromHotel(foundHotel.get(),
                    getWeatherData(foundHotel.get().getCoordinates()));
        }
        return catalogResponse;
    }

    private WeatherDataParser getWeatherData(String coordinates){
        String[] coords = coordinates.split(";");
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + coords[0] +
                "&longitude=" + coords[1] +"&current=temperature_2m" +
                "&daily=weather_code,temperature_2m_max,temperature_2m_min";
        String externalResponse = restTemplate.getForObject(url, String.class);
        //System.out.println(externalResponse);
        return new WeatherDataParser(externalResponse);
    }

    public CatalogRepository getCatalogRepository(){
        return catalogRepository;
    }
}
