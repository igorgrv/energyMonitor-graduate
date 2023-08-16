package graduate.energymonitor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.entity.Location;

public interface LocationRepository extends JpaRepository<Location, UUID> {

}
