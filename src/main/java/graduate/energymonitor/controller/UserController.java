
package graduate.energymonitor.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import graduate.energymonitor.controller.dto.UserDto;
import graduate.energymonitor.entity.User;
import graduate.energymonitor.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Set<User>> getAllUsers() {
        Set<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id_user}")
    public ResponseEntity getUserById(@PathVariable("id_user") Integer idUser) {
        Optional<User> users = userService.findById(idUser);

        if (users.isEmpty()){
            return ResponseEntity.badRequest().body("User not found");
        }

        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto request) {
        User user = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id_user}")
    public ResponseEntity updateUser(@PathVariable("id_user") Integer idUser, @Valid @RequestBody UserDto request) {

        Optional<User> user = userService.findById(idUser);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        userService.updateUser(user.get(), request);

        return ResponseEntity.ok().body(request);

    }

    @DeleteMapping("/{id_user}")
    public ResponseEntity<String> deleteUser(@PathVariable("id_user") Integer idUser) {
        Optional<User> user = userService.findById(idUser);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        userService.deleteUser(user.get());
        return ResponseEntity.ok().body("User was deleted");
    }
}
