package graduate.energymonitor.domains.appliance.controller.dto;

import graduate.energymonitor.domains.appliance.entity.Appliance;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(title = "ApplianceResponse", description = "Object that represents a data transfer object for an appliance")
public record ApplianceResponse(Long id,
    String name,
    String model,
    String brand,
    Integer watts) {

     public static ApplianceResponse fromEntity(Appliance appliance) {
        return new ApplianceResponse(appliance.getId(), appliance.getName(), appliance.getModel(), appliance.getBrand(), appliance.getWatts());
    }

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
