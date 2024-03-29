package graduate.energymonitor.domains.appliance.controller.dto;

import java.util.List;

import graduate.energymonitor.domains.appliance.entity.Appliance;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(title = "ApplianceDTO", description = "Object that represents a data transfer object for an appliance")
public record ApplianceLocationResidentRequest(
    
    @Schema(description = "Name to identify the appliance", example = "XBOX")
    @NotBlank(message = "name is mandatory")
    @Size(min = 2, max = 50, message = "size must be between {min} and {max}")
    String name,
    
    @Schema(description = "Name to identify the appliance model", example = "Series X")
    @NotBlank(message = "model is mandatory")
    @Size(min = 2, max = 50, message = "size must be between {min} and {max}")
    String model,

    @Schema(description = "Name to identify the appliance brand", example = "Microsoft")
    @NotBlank(message = "brand is mandatory")
    @Size(min = 2, max = 50, message = "size must be between {min} and {max}")
    String brand,
    
    @Schema(description = "Number to identify the appliance watts", example = "1000")
    @NotNull(message = "watts is mandatory")
    Integer watts,

    @Schema(description = "Number to identify the location", example = "1")
    @NotNull(message = "locationId is mandatory")
    Long locationId,
    
    @Schema(description = "It's a list of resident IDs that is using this appliance", example = "[1]")
    @NotEmpty(message = "residentIds is mandatory") List<Long> residentIds) {


    public Appliance toEntity() {
        return new Appliance(this);
    }

    public Appliance returnEntityUpdated(Appliance appliance) {
        appliance.setName(name);
        appliance.setModel(model);
        appliance.setBrand(brand);
        appliance.setWatts(watts);

        return appliance;
    }

}
