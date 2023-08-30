
package graduate.energymonitor.domains.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.user.controller.dto.UserResidentsRequest;
import graduate.energymonitor.domains.user.controller.dto.UserResidentsResponse;
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
    public List<UserResidentsResponse> findAll() {
        List<User> users = repository.findAll();
        return users.stream().map(UserResidentsResponse::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResidentsResponse findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return UserResidentsResponse.fromEntity(user);
    }

    @Transactional
    public UserResidentsResponse addUser(UserResidentsRequest dto) {

        String username = dto.username();
        if (repository.findByUsername(username).isPresent())
            throw new AlreadyExistsException(
                    String.format("User: username=%s already exists", username));

        User user = repository.save(new User(dto));
        return UserResidentsResponse.fromEntity(user);
    }

    @Transactional
    public UserResidentsResponse deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        repository.delete(user);
        return UserResidentsResponse.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    @Transactional
    public UserResidentsResponse updatePassword(Long id, String newPassword) {

        User existingUser = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        existingUser.setPassword(newPassword);
        User updatedUser = repository.save(existingUser);

        User user = repository.save(updatedUser);
        return UserResidentsResponse.fromEntity(user);
    }

}
