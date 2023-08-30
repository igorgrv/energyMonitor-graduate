
package graduate.energymonitor.domains.appliance.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.domains.appliance.entity.Appliance;

public interface ApplianceRepository extends JpaRepository<Appliance, UUID> {
}
