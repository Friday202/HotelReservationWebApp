package si.petek.rso.catalogservice.controller;

import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import si.petek.rso.catalogservice.dto.BasicHotelInfoResponse;
import si.petek.rso.catalogservice.dto.CatalogResponse;
import si.petek.rso.catalogservice.healthcheck.CatalogHealthIndicator;
import si.petek.rso.catalogservice.metrics.CatalogMetrics;
import si.petek.rso.catalogservice.service.CatalogService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
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
    public List<BasicHotelInfoResponse> getAllHotelsBasicInfo(){
        catalogMetrics.incrementCatalogCounter();
        return catalogService.getAllHotelsBasicInfo();
    }

    @GetMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public CatalogResponse getHotelById(@PathVariable Long hotelId){
        return catalogService.getHotelByID(hotelId);
    }

    /* Health & Metrics */

    @PostMapping("/actuator/health/set")
    public String simulateSickService(){
        catalogHealthIndicator.setHealthStatus(Status.DOWN);
        return "Simulating unhealthy state..";
    }

    @GetMapping("/actuator/metrics/counter")
    public double getCounterAmount(){
        return catalogMetrics.getCatalogCounterCount();
    }

    @GetMapping("/actuator/metrics/databasesize")
    public long getDatabaseSize(){
        return catalogMetrics.getDatabaseSize();
    }
}
