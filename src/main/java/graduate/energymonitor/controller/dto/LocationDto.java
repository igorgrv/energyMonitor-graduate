package graduate.energymonitor.controller.dto;

import graduate.energymonitor.entity.Location;
import graduate.energymonitor.entity.enums.BrazilStatesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(title = "LocationDto", description = "Object that represents a data transfer object for a Location")
public record LocationDto(
        
        @Schema(description = "Address where the user physically lives", example = "Rua xyz")
        @NotBlank(message = "address is mandatory") String address,

        @Schema(description = "Neighborhood", example = "Vila Olímpia")
        @NotBlank(message = "neighborhood is mandatory") String neighborhood,
        
        @Schema(description = "City", example = "São Paulo")
        @NotBlank(message = "city is mandatory") String city,

        @Schema(description = "State", example = "SP")
        @NotNull(message = "state is mandatory") BrazilStatesEnum state,

        @Schema(description = "Number of the house", example = "10")
        @NotNull(message = "number is mandatory") Integer number) {

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
