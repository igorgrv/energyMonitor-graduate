
package graduate.energymonitor.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graduate.energymonitor.controller.dto.UserDto;
import graduate.energymonitor.entity.User;
import graduate.energymonitor.exception.NotFoundException;
import graduate.energymonitor.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "users")
@Tag(name = "Users", description = "Methods for manipulating user data")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class UserController {

    private static final String USER_NOT_FOUND = "User not found";
    private final UserService userService;

    @Operation(summary = "Get all the users", description = "Method for getting all the users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(examples = {
                    @ExampleObject(summary = "Get all the users", value = "[{\"idUser\":\"5ed71cf4-d013-4fe4-805d-4ad13f9f3b77\",\"cpf\":\"009.117.190-32\",\"name\":\"Joao\",\"birth\":\"1996-03-08\",\"gender\":\"MALE\",\"relative\":\"FATHER\"},{\"idUser\":\"5ed71cf4-d013-4fe4-805d-4ad13f9f3b77\",\"cpf\":\"009.117.190-32\",\"name\":\"Maria\",\"birth\":\"1996-03-08\",\"gender\":\"FEMALE\",\"relative\":\"MOTHER\"},{\"idUser\":\"5ed71cf4-d013-4fe4-805d-4ad13f9f3b77\",\"cpf\":\"009.117.190-32\",\"name\":\"Vlad\",\"birth\":\"1986-03-08\",\"gender\":\"MALE\",\"relative\":\"SON\"}]")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "Get a user by ID", description = "Method to get a user based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(examples = {
                    @ExampleObject(summary = "Get the user by ID", value = "{\"idUser\":\"5ed71cf4-d013-4fe4-805d-4ad13f9f3b77\",\"cpf\":\"009.117.190-32\",\"name\":\"Joao\",\"birth\":\"1996-03-08\",\"gender\":\"MALE\",\"relative\":\"FATHER\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - User Id not Found", content = @Content(examples = {
                    @ExampleObject(summary = "User ID not found", value = "{\"statusCode\":404,\"message\":\"User ID not found\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/{id_user}")
    public ResponseEntity<User> getUserById(@PathVariable("id_user") UUID id) {
        User user = userService.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Create a user", description = "Method to crete a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SUCCESS - User successfully created", content = @Content(examples = {
                    @ExampleObject(summary = "User successfully created", value = "{\"idUser\":\"5ed71cf4-d013-4fe4-805d-4ad13f9f3b77\",\"cpf\":\"009.117.190-32\",\"name\":\"Joao\",\"birth\":\"1996-03-08\",\"gender\":\"MALE\",\"relative\":\"FATHER\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - User Id not Found", content = @Content(examples = {
                    @ExampleObject(summary = "User ID not found", value = "{\"statusCode\":404,\"message\":\"User ID not found\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "409", description = "CONFLICT - User already exists", content = @Content(examples = {
                    @ExampleObject(summary = "User already exists", value = "{\"statusCode\":409,\"message\":\"User: user=Igor Romero, birth=2023-07-10, gender=MALE, relative=FATHER,  already exists\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto request) {
        User user = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Update a user", description = "Method to update an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - User successfully updated", content = @Content(examples = {
                    @ExampleObject(summary = "User successfully updated", value = "{\"idUser\":\"5ed71cf4-d013-4fe4-805d-4ad13f9f3b77\",\"cpf\":\"009.117.190-32\",\"name\":\"Joao\",\"birth\":\"1996-03-08\",\"gender\":\"MALE\",\"relative\":\"FATHER\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - User Id not Found", content = @Content(examples = {
                    @ExampleObject(summary = "User ID not found", value = "{\"statusCode\":404,\"message\":\"User ID not found\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PutMapping("/{id_user}")
    public ResponseEntity<User> updateUser(@PathVariable("id_user") UUID id, @Valid @RequestBody UserDto request) {
        User updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok().body(updatedUser);
    }

    @Operation(summary = "Delete a user", description = "Method to delete an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - User successfully deleted", content = @Content(examples = {
                    @ExampleObject(summary = "User successfully deleted", value = "{\"idUser\":\"5ed71cf4-d013-4fe4-805d-4ad13f9f3b77\",\"cpf\":\"009.117.190-32\",\"name\":\"Joao\",\"birth\":\"1996-03-08\",\"gender\":\"MALE\",\"relative\":\"FATHER\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - User Id not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
                    @ExampleObject(summary = "User ID not found", value = "{\"statusCode\":404,\"message\":\"User ID not found\"}")
            }))
    })
    @DeleteMapping("/{id_user}")
    public ResponseEntity<User> deleteUser(@PathVariable("id_user") UUID id) {
        User deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok().body(deletedUser);
    }
}
