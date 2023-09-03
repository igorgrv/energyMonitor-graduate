
package graduate.energymonitor.domains.appliance.controller;

import java.util.List;

import graduate.energymonitor.domains.consumption.dto.ConsumptionApplianceRequest;
import graduate.energymonitor.domains.consumption.dto.ConsumptionApplianceResponse;
import graduate.energymonitor.domains.consumption.service.ConsumptionService;
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

import graduate.energymonitor.domains.appliance.controller.dto.ApplianceLocationResidentRequest;
import graduate.energymonitor.domains.appliance.controller.dto.ApplianceLocationResidentResponse;
import graduate.energymonitor.domains.appliance.controller.dto.ApplianceResponse;
import graduate.energymonitor.domains.appliance.service.ApplianceService;
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
    private final ConsumptionService consumptionService;

    @Operation(summary = "Get all the appliances", description = "Method for getting all the appliances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all appliances", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ApplianceResponse.class)), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<ApplianceResponse>> getAllAppliances() {
        List<ApplianceResponse> appliances = applianceService.findAll();
        return ResponseEntity.ok().body(appliances);
    }

    @Operation(summary = "Get a appliance by ID", description = "Method to get a appliance based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = ApplianceLocationResidentResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("/{id_appliance}")
    public ResponseEntity<ApplianceLocationResidentResponse> getApplianceById(
            @PathVariable("id_appliance") Long idAppliance) {
        ApplianceLocationResidentResponse appliance = applianceService.findById(idAppliance);
        return ResponseEntity.ok().body(appliance);
    }

    @Operation(summary = "Create an appliance", description = "Method to crete an new appliance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Appliance successfully created", content = @Content(schema = @Schema(implementation = ApplianceLocationResidentRequest.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping
    public ResponseEntity<ApplianceLocationResidentResponse> createAppliance(
            @Valid @RequestBody ApplianceLocationResidentRequest request) {
        ApplianceLocationResidentResponse appliance = applianceService.addAppliance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(appliance);
    }

    @Operation(summary = "Save an appliance consumption", description = "Method to save an new consumption")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "SUCCESS - Consumption successfully saved", content = @Content(schema = @Schema(implementation = ConsumptionApplianceResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping("/consumptions")
    public ResponseEntity<ConsumptionApplianceResponse> saveConsumption(
        @Valid @RequestBody ConsumptionApplianceRequest request) {
        ConsumptionApplianceResponse consumption = consumptionService.saveConsumption(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(consumption);
    }

    @Operation(summary = "Update a appliance", description = "Method to update an existing appliance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Appliance successfully updated", content = @Content(schema = @Schema(implementation = ApplianceResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PutMapping("/{id_appliance}")
    public ResponseEntity<ApplianceResponse> updateAppliance(@PathVariable("id_appliance") Long idAppliance,
            @Valid @RequestBody ApplianceResponse request) {
        ApplianceResponse updatedAppliance = applianceService.updateAppliance(idAppliance, request);
        return ResponseEntity.ok().body(updatedAppliance);
    }

    @Operation(summary = "Delete a appliance", description = "Method to delete an existing appliance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Appliance successfully deleted", content = @Content(schema = @Schema(implementation = ApplianceResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @DeleteMapping("/{id_appliance}")
    public ResponseEntity<ApplianceResponse> deleteAppliance(@PathVariable("id_appliance") Long idAppliance) {
        ApplianceResponse deletedAppliance = applianceService.deleteAppliance(idAppliance);
        return ResponseEntity.ok().body(deletedAppliance);
    }
}
