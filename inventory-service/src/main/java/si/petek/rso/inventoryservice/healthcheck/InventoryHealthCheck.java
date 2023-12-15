package si.petek.rso.inventoryservice.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class InventoryHealthCheck implements HealthIndicator {
    private Status healthStatus = Status.UP;

    @Override
    public Health health(){
        return new Health.Builder(healthStatus).build();
    }

    public void setHealthStatus(Status healthStatus){
        this.healthStatus = healthStatus;
    }
}
