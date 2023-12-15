package si.petek.rso.catalogservice.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;
import si.petek.rso.catalogservice.repository.CatalogRepository;

@Component
public class CatalogMetrics {

    private final Counter catalogCounter;
    private CatalogRepository catalogRepository;

    public CatalogMetrics(MeterRegistry registry) {
        this.catalogCounter = Counter.builder("catalog.endpoint.hits")
                .description("Counts the number of hits on the endpoint")
                .register(registry);
        catalogRepository = null;
    }

    public void incrementCatalogCounter() {
        catalogCounter.increment();
    }

    public void setCatalogRepository(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public long getDatabaseSize(){
        if (catalogRepository != null){
            return catalogRepository.count();
        }
        return -1;
    }

    public double getCatalogCounterCount(){
        return catalogCounter.count();
    }
}
