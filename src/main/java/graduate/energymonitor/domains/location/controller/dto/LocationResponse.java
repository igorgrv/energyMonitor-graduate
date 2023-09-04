package graduate.energymonitor.domains.location.controller.dto;

import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.entity.enums.BrazilStatesEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "LocationResponse", description = "Object that represents a Location's response")
public record LocationResponse(Long id,
        String address,
        String neighborhood,
        String city,
        BrazilStatesEnum state,
        Integer number) {

    public static LocationResponse fromEntity(Location location) {

        return new LocationResponse(location.getId(), location.getAddress(), location.getNeighborhood(), location.getCity(),
                location.getState(), location.getNumber());
    }
}
