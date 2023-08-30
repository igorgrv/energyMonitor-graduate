
package graduate.energymonitor.domains.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.energymonitor.domains.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
