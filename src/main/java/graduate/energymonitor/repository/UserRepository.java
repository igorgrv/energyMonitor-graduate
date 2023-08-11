
package graduate.energymonitor.repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
        users.add(new User(1,"Joao", LocalDate.of(1996, 3, 8), GenderEnum.MALE, RelativesEnum.FATHER));
        users.add(new User(2,"Maria", LocalDate.of(1996, 3, 8), GenderEnum.FEMALE, RelativesEnum.MOTHER));
        users.add(new User(3,"Vlad", LocalDate.of(1986, 3, 8), GenderEnum.MALE, RelativesEnum.SON));
    }

    public Set<User> findAll() {
        return this.users.stream()
            .sorted(Comparator.comparing(User::getIdUser))
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Optional<User> findById(Integer idUser) {
        return this.users.stream()
            .filter(location -> location.getIdUser().equals(idUser)).findFirst();
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public boolean exists(User user) {
        Optional<User> userFound = this.users.stream().filter(userExist -> userExist.identifyBy(user.getName(),
            user.getBirth(),
            user.getGender(),
            user.getRelative())).findFirst();

        if (userFound.isEmpty()){
            return false;
        }
        return true;
    }

    public void updateUser(User user, User userUpdated) {
        this.users.remove(user);
        userUpdated.setIdUser(user.getIdUser());
        this.addUser(userUpdated);
    }

}
