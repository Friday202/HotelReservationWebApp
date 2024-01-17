package si.petek.rso.catalogservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import si.petek.rso.catalogservice.dto.BasicHotelInfoResponse;
import si.petek.rso.catalogservice.dto.CatalogResponse;
import si.petek.rso.catalogservice.model.Hotel;
import si.petek.rso.catalogservice.parser.WeatherDataParser;
import si.petek.rso.catalogservice.repository.CatalogRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static si.petek.rso.catalogservice.dto.BasicHotelInfoResponse.mapToBasicHotelInfoResponse;
import static si.petek.rso.catalogservice.dto.CatalogResponse.createCatalogResponseFromHotel;
import static si.petek.rso.catalogservice.dto.CatalogResponse.mapToCatalogResponses;

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

    @RateLimiter(name="catalogRateLimiter", fallbackMethod = "rateLimitFallback")
    //@CircuitBreaker(name = "catalogCircuitBreaker", fallbackMethod = "circuitBreakerFallback")
    public List<CatalogResponse> getAllHotels(){
        System.out.println("Called");
        return mapToCatalogResponses(catalogRepository.findAll());
    }

    public List<CatalogResponse> rateLimitFallback(Exception e){
        List<CatalogResponse> catalogResponses = new ArrayList<CatalogResponse>();
        CatalogResponse catalogResponse = new CatalogResponse();
        catalogResponse.setName("Rate limit exceeded: " + e.getMessage());
        catalogResponses.add(catalogResponse);
        return catalogResponses;
    }

    public List<CatalogResponse> circuitBreakerFallback(Exception e){
        return new ArrayList<CatalogResponse>();
    }

    public void createHotel(Hotel hotel){
        catalogRepository.save(hotel);
    }

    public void updateHotel(Hotel hotel){
        Optional<Hotel> foundHotel = catalogRepository.findById(hotel.getId());
        if (foundHotel.isPresent()){
            Hotel existingHotel = foundHotel.get();
            existingHotel.setAmenities(hotel.getAmenities());
            existingHotel.setName(hotel.getName());
            existingHotel.setCoordinates(hotel.getCoordinates());
            existingHotel.setLocation(hotel.getLocation());
            existingHotel.setDescription(hotel.getDescription());
            catalogRepository.save(existingHotel);
        }
    }

    public void deleteHotel(Long id){
        Optional<Hotel> foundHotel = catalogRepository.findById(id);
        if (foundHotel.isPresent()){
            catalogRepository.delete(foundHotel.get());
        }
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

    public Hotel hotelById(String Id){
        Optional<Hotel> foundHotel = catalogRepository.findById(Long.valueOf(Id));
        if (foundHotel.isPresent()){
            return foundHotel.get();
        }
        return null;
    }

    public CatalogRepository getCatalogRepository(){
        return catalogRepository;
    }
}
