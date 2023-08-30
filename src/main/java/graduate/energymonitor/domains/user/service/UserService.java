
package graduate.energymonitor.domains.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.user.controller.dto.UserRequest;
import graduate.energymonitor.domains.user.controller.dto.UserResponse;
import graduate.energymonitor.domains.user.entity.User;
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
    public List<UserResponse> findAll() {
        List<User> users = repository.findAll();
        return UserResponse.fromEntity(users);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return UserResponse.fromEntity(user);
    }

    @Transactional
    public UserResponse addUser(UserRequest dto) {

        String username = dto.username();
        if (repository.findByUsername(username).isPresent())
            throw new AlreadyExistsException(
                    String.format("User: username=%s already exists", username));

        User user = repository.save(new User(dto));
        return UserResponse.fromEntity(user);
    }

    @Transactional
    public UserResponse deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        repository.delete(user);
        return UserResponse.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    @Transactional
    public UserResponse updatePassword(Long id, String newPassword) {

        User existingUser = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        existingUser.setPassword(newPassword);
        User updatedUser = repository.save(existingUser);

        User user = repository.save(updatedUser);
        return UserResponse.fromEntity(user);
    }

}
