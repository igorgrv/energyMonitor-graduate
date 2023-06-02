package graduate.energymonitor.controller.dto;

import graduate.energymonitor.entity.Location;
import graduate.energymonitor.entity.enums.BrazilStatesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocationDto(@NotBlank(message = "address is mandatory") String address,
        @NotBlank(message = "neighborhood is mandatory") String neighborhood,
        @NotBlank(message = "city is mandatory") String city,
        @NotNull(message = "state is mandatory") BrazilStatesEnum state,
        @NotNull(message = "number is mandatory") Integer number) {

    public Location toLocation() {
        return new Location(address, number, neighborhood, city, state);
    }

}
