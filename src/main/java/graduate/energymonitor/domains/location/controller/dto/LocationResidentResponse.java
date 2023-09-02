package graduate.energymonitor.domains.location.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.entity.enums.BrazilStatesEnum;
import graduate.energymonitor.domains.resident.controller.dto.ResidentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "LocationResponse", description = "Object that represents a Location's response")
public record LocationResidentResponse(
        String address,
        String neighborhood,
        String city,
        BrazilStatesEnum state,
        Integer number,
        List<ResidentResponse> residents) {

    public static LocationResidentResponse fromEntity(Location location) {
        location.getResidents().forEach(resident -> resident.getLocations().add(location));
        
        List<ResidentResponse> residents = new ArrayList<>();
        if (location.getResidents() != null && !location.getResidents().isEmpty()) {
            residents = location.getResidents()
                .stream()
                .map(ResidentResponse::fromEntity)
                .collect(Collectors.toList());
        }

        return new LocationResidentResponse(location.getAddress(), location.getNeighborhood(), location.getCity(),
                location.getState(), location.getNumber(), residents);
    }

}
