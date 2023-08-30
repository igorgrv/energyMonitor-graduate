
package graduate.energymonitor.domains.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.user.entity.User;
import graduate.energymonitor.domains.user.entity.dto.UserDto;
import graduate.energymonitor.domains.user.repository.UserRepository;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private static final String USER_NOT_FOUND = "User not found";

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    @Transactional
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

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    @Transactional
    public User updateUser(UUID id, UserDto updatedUserDto) {

        User existingUser = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        User updatedUser = updatedUserDto.returnEntityUpdated(existingUser);

        return repository.save(updatedUser);
    }

}
