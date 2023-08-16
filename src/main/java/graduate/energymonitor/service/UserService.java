
package graduate.energymonitor.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import graduate.energymonitor.controller.dto.UserDto;
import graduate.energymonitor.entity.User;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.exception.NotFoundException;
import graduate.energymonitor.repository.UserRepository;
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

        if (repository.existsByCpf(user.getCpf()))
            throw new AlreadyExistsException(
                    String.format("User: cpf=%s, user=%s, birth=%s, gender=%s, relative=%s,  already exists",
                            user.getCpf(), user.getName(), user.getBirth(), user.getGender(), user.getRelative()));

        return repository.save(user);
    }

    @Transactional
    public User deleteUser(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        repository.delete(user);
        return user;
    }

    @Transactional
    public User updateUser(UUID id, UserDto updatedUserDto) {

        User existingUser = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        User updatedUser = updatedUserDto.returnEntityUpdated(existingUser);

        return repository.save(updatedUser);
    }

}
