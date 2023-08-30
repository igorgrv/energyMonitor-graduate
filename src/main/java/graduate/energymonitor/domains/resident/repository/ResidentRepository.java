
package graduate.energymonitor.domains.resident.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.domains.resident.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident, UUID> {

    boolean existsByCpf(String cpf);

}
