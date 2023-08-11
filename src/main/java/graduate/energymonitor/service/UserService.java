
package graduate.energymonitor.service;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import graduate.energymonitor.controller.dto.UserDto;
import graduate.energymonitor.entity.User;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Set<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Integer idUser) {
        return repository.findById(idUser);
    }

    public User addUser(UserDto request) {
        User user = request.toUser();

        if (repository.exists(user))
            throw new AlreadyExistsException
                (String.format("User: user=%s, birth=%s, gender=%s, relative=%s,  already exists", user.getName()
                    ,user.getBirth()
                    ,user.getGender()
                    ,user.getRelative()));

        user.setIdUser(new Random().nextInt(Integer.MAX_VALUE));
        return repository.addUser(user);
    }

    public void deleteUser(User user) {
        repository.deleteUser(user);
    }

    public void updateUser(User user, UserDto request) {
        User userUpdated = request.toUser();
        repository.updateUser(user, userUpdated);
    }

}
