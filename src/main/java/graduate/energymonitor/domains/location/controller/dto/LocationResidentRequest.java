package graduate.energymonitor.domains.location.controller.dto;

import java.util.List;

import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.entity.enums.BrazilStatesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(title = "LocationRequest", description = "Object that represents a Location's data transfer object")
public record LocationResidentRequest(
        
        @Schema(description = "Address where the user physically lives", example = "Rua xyz")
        @NotBlank(message = "address is mandatory") String address,

        @Schema(description = "Neighborhood", example = "Vila Olímpia")
        @NotBlank(message = "neighborhood is mandatory") String neighborhood,
        
        @Schema(description = "City", example = "São Paulo")
        @NotBlank(message = "city is mandatory") String city,

        @Schema(description = "State", example = "SP")
        @NotNull(message = "state is mandatory") BrazilStatesEnum state,

        @Schema(description = "Number of the house", example = "10")
        @NotNull(message = "number is mandatory") Integer number,
        
        @Schema(description = "It's a list of resident IDs that live in this Location", example = "[1]")
        @NotEmpty(message = "residentIds is mandatory") List<Long> residentIds) {

    public Location toLocation() {
        return new Location(address, number, neighborhood, city, state);
    }

     public Location returnEntityUpdated(Location location) {
        location.setAddress(address);
        location.setCity(city);
        location.setNeighborhood(neighborhood);
        location.setNumber(number);
        location.setState(state);
        location.getResidents().clear();

        return location;
    }

}
