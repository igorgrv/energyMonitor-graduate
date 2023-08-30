package graduate.energymonitor.domains.location.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.domains.location.entity.Location;

public interface LocationRepository extends JpaRepository<Location, UUID> {

}
