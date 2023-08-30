package graduate.energymonitor.domains.appliance.entity.dto;

import graduate.energymonitor.domains.appliance.entity.Appliance;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(title = "ApplianceDTO", description = "Object that represents a data transfer object for an appliance")
public record ApplianceDto(
    
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
    Integer watts) {

    public Appliance toAppliance() {
        return new Appliance(name, model, brand, watts);
    }

    public Appliance returnEntityUpdated(Appliance appliance) {
        appliance.setName(name);
        appliance.setModel(model);
        appliance.setBrand(brand);
        appliance.setWatts(watts);

        return appliance;
    }

}
