
package graduate.energymonitor.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/findAll")
    public ResponseEntity<Set<User>> getAllUsers() {
        Set<User> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/findBy/name/{name}")
    public ResponseEntity<User> getUserByAddress(@PathVariable String name) {
        User user = service.findByName(name);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto request) {
        User user = service.add(request);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody UserDto request) {
        service.delete(request);
        return ResponseEntity.ok().body("User was deleted");
    }
}
