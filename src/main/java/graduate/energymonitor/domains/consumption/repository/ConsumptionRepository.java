package graduate.energymonitor.domains.consumption.repository;

import graduate.energymonitor.domains.consumption.entity.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {

}
