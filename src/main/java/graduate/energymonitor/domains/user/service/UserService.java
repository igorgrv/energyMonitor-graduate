
package graduate.energymonitor.domains.user.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import graduate.energymonitor.domains.user.entity.User;
import graduate.energymonitor.domains.user.entity.dto.UserDto;
import graduate.energymonitor.domains.user.repository.UserRepository;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private static final String USER_NOT_FOUND = "User not found";

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return repository.findById(id);
    }

    public User addUser(UserDto request) {
        User user = request.toUser();

        if (repository.existByUsername(user.getUsername()))
            throw new AlreadyExistsException(
                    String.format("User: username=%s already exists", user.getUsername()));

        return repository.save(user);
    }

    @Transactional
    public User deleteUser(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        repository.delete(user);
        return user;
    }

    // @Transactional
    // public User updateUser(UUID id, UserDto updatedUserDto) {

    // User existingUser = repository.findById(id).orElseThrow(() -> new
    // NotFoundException(USER_NOT_FOUND));
    // User updatedUser = updatedUserDto.returnEntityUpdated(existingUser);

    // return repository.save(updatedUser);
    // }

}
