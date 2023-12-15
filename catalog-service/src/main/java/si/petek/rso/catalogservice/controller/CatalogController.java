package si.petek.rso.catalogservice.controller;

import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import si.petek.rso.catalogservice.dto.CatalogResponse;
import si.petek.rso.catalogservice.healthcheck.CatalogHealthIndicator;
import si.petek.rso.catalogservice.service.CatalogService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {
    private final CatalogService catalogService;
    private final CatalogHealthIndicator catalogHealthIndicator;

    public CatalogController(CatalogService catalogService, CatalogHealthIndicator catalogHealthIndicator) {
        this.catalogService = catalogService;
        this.catalogHealthIndicator = catalogHealthIndicator;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogResponse> getAllHotels(){
        return catalogService.getAllHotels();
    }

    @GetMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public CatalogResponse getHotelById(@PathVariable Long hotelId){
        return catalogService.getHotelByID(hotelId);
    }

    @PostMapping("/actuator/health/set")
    public String simulateSickService(){
        catalogHealthIndicator.setHealthStatus(Status.DOWN);
        return "Simulating unhealthy state...";
    }

}
