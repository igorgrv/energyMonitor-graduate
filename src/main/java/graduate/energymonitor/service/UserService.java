
package graduate.energymonitor.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graduate.energymonitor.controller.dto.UserDto;
import graduate.energymonitor.entity.User;
import graduate.energymonitor.exception.NotFoundException;
import graduate.energymonitor.repository.UserRepository;
import jakarta.validation.Valid;

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

    public User add(@Valid UserDto request) {
        User user = request.toUser();
        return repository.add(user);
    }

    public void delete(@Valid UserDto request) {
        User user = request.toUser();
        repository.delete(user);
    }

}
