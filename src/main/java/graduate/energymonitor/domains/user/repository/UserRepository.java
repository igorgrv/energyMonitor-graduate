
package graduate.energymonitor.domains.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.domains.user.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

  boolean existByUsername(String username);

}
