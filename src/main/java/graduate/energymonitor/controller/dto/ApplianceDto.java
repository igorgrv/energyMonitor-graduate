package graduate.energymonitor.controller.dto;

import graduate.energymonitor.entity.Appliance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplianceDto(@NotBlank(message = "name is mandatory") String name,
        @NotBlank(message = "model is mandatory") String model,
        @NotNull(message = "watts is mandatory") Integer watts) {

    public Appliance toAppliance() {
        return new Appliance(name, model, watts);
    }

}
