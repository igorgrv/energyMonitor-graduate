package graduate.energymonitor.domains.location.controller.dto;

import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.entity.enums.BrazilStatesEnum;

public record LocationResponse(String address,
        String neighborhood,
        String city,
        BrazilStatesEnum state,
        Integer number) {

    public static LocationResponse fromEntity(Location location) {

        return new LocationResponse(location.getAddress(), location.getNeighborhood(), location.getCity(),
                location.getState(), location.getNumber());
    }
}
