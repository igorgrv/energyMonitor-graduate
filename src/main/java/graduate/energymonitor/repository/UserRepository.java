
package graduate.energymonitor.repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import graduate.energymonitor.entity.User;
import graduate.energymonitor.entity.enums.GenderEnum;
import graduate.energymonitor.entity.enums.RelativesEnum;

@Repository
public class UserRepository {

    Set<User> users = new HashSet<>();

    public UserRepository() {
        users.add(new User("Joao", LocalDate.of(1996, 3, 8), GenderEnum.MALE, RelativesEnum.FATHER));
        users.add(new User("Maria", LocalDate.of(1996, 3, 8), GenderEnum.FEMALE, RelativesEnum.MOTHER));
        users.add(new User("Vlad", LocalDate.of(1986, 3, 8), GenderEnum.MALE, RelativesEnum.SON));
    }

    public Set<User> findAll() {
        return this.users;
    }

    public Set<User> findByName(String name) {
        return this.users.stream()
                .filter(user -> user.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public User add(User user) {
        users.add(user);
        return user;
    }

    public void delete(User user) {
        users.remove(user);
    }

    public boolean exists(User user) {
        return this.users.contains(user);
    }

}
