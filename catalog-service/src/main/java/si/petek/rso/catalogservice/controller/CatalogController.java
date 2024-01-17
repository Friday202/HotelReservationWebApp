package si.petek.rso.catalogservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.annotations.Source;
import org.springframework.boot.actuate.health.Status;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import si.petek.rso.catalogservice.dto.BasicHotelInfoResponse;
import si.petek.rso.catalogservice.dto.CatalogResponse;
import si.petek.rso.catalogservice.healthcheck.CatalogHealthIndicator;
import si.petek.rso.catalogservice.metrics.CatalogMetrics;
import si.petek.rso.catalogservice.model.Hotel;
import si.petek.rso.catalogservice.service.CatalogService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CatalogController {
    private final CatalogService catalogService;
    private final CatalogHealthIndicator catalogHealthIndicator;

    private final CatalogMetrics catalogMetrics;

    public CatalogController(CatalogService catalogService, CatalogHealthIndicator catalogHealthIndicator, CatalogMetrics catalogMetrics, RestTemplate restTemplate) {
        this.catalogService = catalogService;
        this.catalogHealthIndicator = catalogHealthIndicator;
        this.catalogMetrics = catalogMetrics;
        this.catalogMetrics.setCatalogRepository(this.catalogService.getCatalogRepository());
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all hotels basic info.", description = "Gets all basic information from all hotels in database.")
    public List<BasicHotelInfoResponse> getAllHotelsBasicInfo(){
        catalogMetrics.incrementCatalogCounter();
        return catalogService.getAllHotelsBasicInfo();
    }

    @GetMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get hotel by ID", description = "Gets all information from one hotel in database by ID.")
    public CatalogResponse getHotelById(@PathVariable Long hotelId){
        return catalogService.getHotelByID(hotelId);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all hotels", description = "Gets all information from all hotels in database.")
    public List<CatalogResponse> getAllHotels(){
        return catalogService.getAllHotels();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates hotel", description = "Creates a hotel by hotel data structure.")
    public String createHotel(@RequestBody Hotel hotel){
        catalogService.createHotel(hotel);
        return "Created a new hotel.";
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Updates hotel", description = "Updates hotel by hotel data structure.")
    public String updateHotel(@RequestBody Hotel hotel){
        catalogService.updateHotel(hotel);
        return "Updated an existing hotel.";
    }

    @DeleteMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete hotel", description = "Deletes a hotel by ID.")
    public String deleteHotel(@PathVariable Long hotelId){
        catalogService.deleteHotel(hotelId);
        return "Successfully deleted hotel.";
    }

    /* Health & Metrics */

    @PostMapping("/actuator/health/set")
    @Operation(summary = "Set service health", description = "Simulates a sick service.")
    public String simulateSickService(){
        catalogHealthIndicator.setHealthStatus(Status.DOWN);
        return "Simulating unhealthy state..";
    }

    @GetMapping("/actuator/metrics/counter")
    @Operation(summary = "Get counter", description = "Retrieves the value count from the counter.")
    public double getCounterAmount(){
        return catalogMetrics.getCatalogCounterCount();
    }

    @GetMapping("/actuator/metrics/databasesize")
    @Operation(summary = "Get database size", description = "Retrieves the size of the database.")
    public long getDatabaseSize(){
        return catalogMetrics.getDatabaseSize();
    }


    /* GraphQL */
    @QueryMapping
    public Hotel hotelById(@Argument String id) {
        return catalogService.hotelById(id);
    }
}
