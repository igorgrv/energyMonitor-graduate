
package graduate.energymonitor.domains.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.user.controller.dto.UserRequest;
import graduate.energymonitor.domains.user.controller.dto.UserResidentsResponse;
import graduate.energymonitor.domains.user.controller.dto.UserResponse;
import graduate.energymonitor.domains.user.entity.User;
import graduate.energymonitor.domains.user.repository.UserRepository;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private static final String USER_NOT_FOUND = "User not found - ID: ";

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        List<User> users = repository.findAll();
        return users.stream().map(UserResponse::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResidentsResponse findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND + id));
        return UserResidentsResponse.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND + username));
    }

    @Transactional
    public UserResponse addUser(@Valid UserRequest request) {

        String username = request.username();
        if (repository.findByUsername(username).isPresent())
            throw new AlreadyExistsException(
                    String.format("User: username=%s already exists", username));

        User user = repository.save(new User(request));
        return UserResponse.fromEntity(user);
    }

    @Transactional
    public UserResidentsResponse deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND + id));
        repository.delete(user);
        return UserResidentsResponse.fromEntity(user);
    }

    @Transactional
    public UserResidentsResponse updatePassword(Long id, String newPassword) {

        User existingUser = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND + id));
        existingUser.setPassword(newPassword);
        User updatedUser = repository.save(existingUser);

        User user = repository.save(updatedUser);
        return UserResidentsResponse.fromEntity(user);
    }

}
