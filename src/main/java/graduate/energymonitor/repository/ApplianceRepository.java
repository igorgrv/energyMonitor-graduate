
package graduate.energymonitor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.entity.Appliance;

public interface ApplianceRepository extends JpaRepository<Appliance, UUID> {
}
