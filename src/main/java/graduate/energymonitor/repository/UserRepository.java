
package graduate.energymonitor.repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import graduate.energymonitor.entity.GenderEnum;
import graduate.energymonitor.entity.User;

@Repository
public class UserRepository {

    Set<User> users = new HashSet<>();

    public UserRepository() {
        users.add(new User("Joao", LocalDate.of(1996, 3, 8), GenderEnum.MALE));
        users.add(new User("Maria", LocalDate.of(1996, 3, 8), GenderEnum.FEMALE));
        users.add(new User("Vlad", LocalDate.of(1986, 3, 8), GenderEnum.MALE));
    }

    public Set<User> findAll() {
        return this.users;
    }

    public Optional<User> findByName(String name) {
        return this.users.stream()
                .filter(user -> user.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase()))
                .findFirst();
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
