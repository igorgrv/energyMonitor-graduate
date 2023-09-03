
package graduate.energymonitor.domains.user.controller;

import java.util.List;

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

import graduate.energymonitor.domains.user.controller.dto.UserRequest;
import graduate.energymonitor.domains.user.controller.dto.UserResidentsResponse;
import graduate.energymonitor.domains.user.controller.dto.UserResponse;
import graduate.energymonitor.domains.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("users")
@Tag(name = "Users", description = "Methods for manipulating user data")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - User Id not Found", content = @Content(examples = {
                @ExampleObject(summary = "User ID not found", value = "{\"statusCode\":404,\"message\":\"User ID not found\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class UserController {

    private final UserService service;

    @Operation(summary = "Get all the users", description = "Method for getting all the users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all Users", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "Get a user by ID", description = "Method to get a user based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = UserResidentsResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("{id_user}")
    public ResponseEntity<UserResidentsResponse> getUserById(@PathVariable("id_user") Long id) {
        UserResidentsResponse user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Create a user", description = "Method to crete a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - User successfully created", content = @Content(schema = @Schema(implementation = UserResidentsResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "409", description = "CONFLICT - User already exists", content = @Content(examples = {
                    @ExampleObject(summary = "User already exists", value = "{\"statusCode\":409,\"message\":\"User: user=Igor Romero, birth=2023-07-10, gender=MALE, relative=FATHER,  already exists\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        UserResponse user = service.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Update a password", description = "Method to update user password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Password successfully changed", content = @Content(schema = @Schema(implementation = UserResidentsResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PutMapping("{id_user}")
    public ResponseEntity<UserResidentsResponse> updatePassword(@PathVariable("id_user") Long id,
            @Valid @RequestBody String password) {
        UserResidentsResponse updatedUser = service.updatePassword(id, password);
        return ResponseEntity.ok().body(updatedUser);
    }

    @Operation(summary = "Delete a user", description = "Method to delete an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - User successfully deleted", content = @Content(schema = @Schema(implementation = UserResidentsResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @DeleteMapping("{id_user}")
    public ResponseEntity<UserResidentsResponse> deleteUser(@PathVariable("id_user") Long id) {
        UserResidentsResponse deletedUser = service.deleteUser(id);
        return ResponseEntity.ok().body(deletedUser);
    }
}
