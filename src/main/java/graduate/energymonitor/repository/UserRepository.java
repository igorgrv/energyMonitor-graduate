
package graduate.energymonitor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByCpf(String cpf);

}
