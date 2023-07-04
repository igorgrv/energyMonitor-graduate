
package graduate.energymonitor.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graduate.energymonitor.controller.dto.UserDto;
import graduate.energymonitor.entity.User;
import graduate.energymonitor.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Set<User>> getAllUsers() {
        Set<User> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{name}")
    public ResponseEntity getUserByAddress(@PathVariable String name) {
        Set<User> users = service.findByName(name);

        if (users.isEmpty()){
            return ResponseEntity.badRequest().body("User not found");
        }

        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto request) {
        User user = service.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@Valid @RequestBody UserDto request) {
        if(service.findLocation(request)){
            service.delete(request);
            return ResponseEntity.ok().body("User was deleted");
        }
        return ResponseEntity.badRequest().body("User not found");
    }
}
