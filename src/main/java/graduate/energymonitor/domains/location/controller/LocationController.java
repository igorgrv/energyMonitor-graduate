package graduate.energymonitor.domains.location.controller;

import java.util.List;

import graduate.energymonitor.domains.location.controller.dto.LocationResidentApplianceResponse;
import graduate.energymonitor.domains.location.controller.dto.LocationResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

import graduate.energymonitor.domains.location.controller.dto.LocationResidentRequest;
import graduate.energymonitor.domains.location.controller.dto.LocationResidentResponse;
import graduate.energymonitor.domains.location.service.LocationService;
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
@RequestMapping("locations")
@Tag(name = "Locations", description = "Methods for manipulating location data")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - Location Id not Found", content = @Content(examples = {
                @ExampleObject(summary = "Location ID not found", value = "{\"statusCode\":404,\"message\":\"Location ID not found\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class LocationController {

    private final LocationService locationService;

    @Operation(summary = "Get all the locations", description = "Method for getting all the locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all locations", content = @Content(array = @ArraySchema(schema = @Schema(implementation = LocationResponse.class)), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<LocationResponse>> getAllLocations() {
        List<LocationResponse> locations = locationService.findAll();
        return ResponseEntity.ok().body(locations);
    }

    @Operation(summary = "Get a location by ID", description = "Method to get a location based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = LocationResidentApplianceResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("/{id_location}")
    public ResponseEntity<LocationResidentApplianceResponse> getLocationById(@PathVariable("id_location") Long idLocation) {
        LocationResidentApplianceResponse location = locationService.findById(idLocation);
        return ResponseEntity.ok().body(location);
    }

    @Operation(summary = "Create an location", description = "Method to crete an new location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Location successfully created", content = @Content(schema = @Schema(implementation = LocationResidentResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping
    public ResponseEntity<LocationResidentResponse> createLocation(@Valid @RequestBody LocationResidentRequest request) {
        LocationResidentResponse location = locationService.addLocation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(location);
    }

    @Operation(summary = "Update a location", description = "Method to update an existing location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Location successfully updated", content = @Content(schema = @Schema(implementation = LocationResidentResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PutMapping("/{id_location}")
    public ResponseEntity<LocationResidentResponse> updateLocation(@PathVariable("id_location") Long idLocation,
            @Valid @RequestBody LocationResidentRequest request) {
        LocationResidentResponse updatedLocation = locationService.updateLocation(idLocation, request);
        return ResponseEntity.ok().body(updatedLocation);

    }

    @Operation(summary = "Delete a location", description = "Method to delete an existing location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Location successfully deleted", content = @Content(schema = @Schema(implementation = LocationResidentResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @DeleteMapping("/{id_location}")
    public ResponseEntity<LocationResidentResponse> deleteLocation(@PathVariable("id_location") Long idLocation) {
        LocationResidentResponse deletedLocation = locationService.deleteLocation(idLocation);
        return ResponseEntity.ok().body(deletedLocation);
    }
}
