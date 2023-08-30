
package graduate.energymonitor.domains.resident.controller;

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

import graduate.energymonitor.domains.resident.controller.dto.ResidentUserRequest;
import graduate.energymonitor.domains.resident.controller.dto.ResidentUserResponse;
import graduate.energymonitor.domains.resident.service.ResidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("residents")
@Tag(name = "Residents", description = "Methods for manipulating resident data")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - Resident Id not Found", content = @Content(examples = {
                @ExampleObject(summary = "Resident ID not found", value = "{\"statusCode\":404,\"message\":\"Resident ID not found\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class ResidentController {

    private final ResidentService residentService;

    @Operation(summary = "Get all the residents", description = "Method for getting all the residents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all Residents", content = @Content(schema = @Schema(implementation = ResidentUserResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<ResidentUserResponse>> getAllResidents() {
        List<ResidentUserResponse> residents = residentService.findAll();
        return ResponseEntity.ok().body(residents);
    }

    @Operation(summary = "Get a resident by ID", description = "Method to get a resident based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = ResidentUserResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("{id_resident}")
    public ResponseEntity<ResidentUserResponse> getResidentById(@PathVariable("id_resident") Long id) {
        ResidentUserResponse resident = residentService.findById(id);
        return ResponseEntity.ok().body(resident);
    }

    @Operation(summary = "Create a resident", description = "Method to crete a new resident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Resident successfully created", content = @Content(schema = @Schema(implementation = ResidentUserResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "409", description = "CONFLICT - Resident already exists", content = @Content(examples = {
                    @ExampleObject(summary = "Resident already exists", value = "{\"statusCode\":409,\"message\":\"Resident: resident=Igor Romero, birth=2023-07-10, gender=MALE, relative=FATHER,  already exists\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping
    public ResponseEntity<ResidentUserResponse> createResident(@Valid @RequestBody ResidentUserRequest request) {
        ResidentUserResponse resident = residentService.addResident(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resident);
    }

    @Operation(summary = "Update a resident", description = "Method to update anexisting resident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Resident successfully updated", content = @Content(schema = @Schema(implementation = ResidentUserResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PutMapping("{id_resident}")
    public ResponseEntity<ResidentUserResponse> updateResident(@PathVariable("id_resident") Long id,
            @Valid @RequestBody ResidentUserRequest request) {
        ResidentUserResponse updatedResident = residentService.updateResident(id, request);
        return ResponseEntity.ok().body(updatedResident);
    }

    @Operation(summary = "Delete a resident", description = "Method to delete an existing resident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Resident successfully deleted", content = @Content(schema = @Schema(implementation = ResidentUserResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @DeleteMapping("{id_resident}")
    public ResponseEntity<ResidentUserResponse> deleteResident(@PathVariable("id_resident") Long id) {
        ResidentUserResponse deletedResident = residentService.deleteResident(id);
        return ResponseEntity.ok().body(deletedResident);
    }
}
