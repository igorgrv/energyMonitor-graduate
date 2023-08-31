package graduate.energymonitor.domains.location.controller.dto;

import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.entity.enums.BrazilStatesEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "LocationResponse", description = "Object that represents a Location's response")
public record LocationResponse(
        String address,
        String neighborhood,
        String city,
        BrazilStatesEnum state,
        Integer number) {

    public Location toLocation() {
        return new Location(address, number, neighborhood, city, state);
    }

    public Location returnEntityUpdated(Location location) {
        location.setAddress(address);
        location.setCity(city);
        location.setNeighborhood(neighborhood);
        location.setNumber(number);
        location.setState(state);

        return location;
    }

}
