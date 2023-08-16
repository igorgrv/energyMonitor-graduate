
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

import graduate.energymonitor.controller.dto.ApplianceDto;
import graduate.energymonitor.entity.Appliance;
import graduate.energymonitor.exception.NotFoundException;
import graduate.energymonitor.service.ApplianceService;
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
@RequestMapping("appliances")
@Tag(name = "Appliances", description = "Methods for manipulating appliance data")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - Appliance Id not Found", content = @Content(examples = {
                @ExampleObject(summary = "Appliance ID not found", value = "{\"statusCode\":404,\"message\":\"Appliance ID not found\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class ApplianceController {

    private final ApplianceService applianceService;
    private static final String APPLIANCE_NOT_FOUND = "Appliance not found";

    @Operation(summary = "Get all the appliances", description = "Method for getting all the appliances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all appliances", content = @Content(schema = @Schema(implementation = ApplianceDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<Appliance>> getAllAppliances() {
        List<Appliance> appliances = applianceService.findAll();
        return ResponseEntity.ok().body(appliances);
    }

    @Operation(summary = "Get a appliance by ID", description = "Method to get a appliance based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = ApplianceDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("/{id_appliance}")
    public ResponseEntity<Appliance> getApplianceById(@PathVariable("id_appliance") UUID idAppliance) {
        Appliance appliance = applianceService.findById(idAppliance)
                .orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        return ResponseEntity.ok().body(appliance);
    }

    @Operation(summary = "Create an appliance", description = "Method to crete an new appliance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Appliance successfully created", content = @Content(schema = @Schema(implementation = Appliance.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping
    public ResponseEntity<Appliance> createAppliance(@Valid @RequestBody ApplianceDto request) {
        Appliance appliance = applianceService.addAppliance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(appliance);
    }

    @Operation(summary = "Update a appliance", description = "Method to update an existing appliance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Appliance successfully updated", content = @Content(schema = @Schema(implementation = Appliance.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PutMapping("/{id_appliance}")
    public ResponseEntity<Appliance> updateAppliance(@PathVariable("id_appliance") UUID idAppliance,
            @Valid @RequestBody ApplianceDto request) {
        Appliance updatedAppliance = applianceService.updateAppliance(idAppliance, request);
        return ResponseEntity.ok().body(updatedAppliance);
    }

    @Operation(summary = "Delete a appliance", description = "Method to delete an existing appliance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Appliance successfully deleted", content = @Content(schema = @Schema(implementation = Appliance.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @DeleteMapping("/{id_appliance}")
    public ResponseEntity<Appliance> deleteAppliance(@PathVariable("id_appliance") UUID idAppliance) {
        Appliance deletedAppliance = applianceService.deleteAppliance(idAppliance);
        return ResponseEntity.ok().body(deletedAppliance);
    }
}
