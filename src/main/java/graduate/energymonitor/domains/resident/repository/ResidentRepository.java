
package graduate.energymonitor.domains.resident.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.domains.resident.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

    boolean existsByCpf(String cpf);

}
