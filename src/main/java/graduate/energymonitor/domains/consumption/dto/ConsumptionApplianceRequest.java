package graduate.energymonitor.domains.consumption.dto;

import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;


// This DTO is used by the ApplianceController
@Schema(title = "ConsumptionApplianceRequest", description = "Object that represents a Consumption and Appliance data transfer object")
public record ConsumptionApplianceRequest(

    @Schema(description = "Start Of Operation", example = "2023-09-03T09:00:00.000Z")
    Instant startOfOperation,

    @Schema(description = "Start Of Operation", example = "2023-09-03T20:00:00.000Z")
    Instant endOfOperation,

    @Schema(description = "It's a appliance ID", example = "1")
    Long applianceId) {

}
