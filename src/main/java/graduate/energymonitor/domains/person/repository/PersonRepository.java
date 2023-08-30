
package graduate.energymonitor.domains.person.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.domains.person.entity.Person;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    boolean existsByCpf(String cpf);

}
