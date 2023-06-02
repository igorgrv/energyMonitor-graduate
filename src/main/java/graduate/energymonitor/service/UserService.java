
package graduate.energymonitor.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graduate.energymonitor.controller.dto.UserDto;
import graduate.energymonitor.entity.User;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.exception.NotFoundException;
import graduate.energymonitor.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Set<User> findAll() {
        return repository.findAll();
    }

    public User findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User add(UserDto request) {
        User user = request.toUser();
        if (repository.exists(user))
            throw new AlreadyExistsException(String.format("User %s already exists", user));
        return repository.add(user);
    }

    public void delete(UserDto request) {
        User user = request.toUser();
        repository.delete(user);
    }

}
