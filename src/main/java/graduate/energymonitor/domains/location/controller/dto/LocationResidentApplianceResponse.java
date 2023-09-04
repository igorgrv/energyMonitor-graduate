package graduate.energymonitor.domains.location.controller.dto;

import graduate.energymonitor.domains.appliance.controller.dto.ApplianceResponse;
import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.entity.enums.BrazilStatesEnum;
import graduate.energymonitor.domains.resident.controller.dto.ResidentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// This DTO is used by LocationResidentApplianceResponse, to allow the client to get a full list with Location + Resident + Appliance
@Schema(title = "LocationResidentApplianceResponse", description = "Object that represents a Resident response")
public record LocationResidentApplianceResponse(Long id,
                                                String address,
                                                String neighborhood,
                                                String city,
                                                BrazilStatesEnum state,
                                                Integer number,
                                                List<ResidentResponse> residents,
                                                List<ApplianceResponse> appliances) {

    public static LocationResidentApplianceResponse fromEntity(Location location) {

        List<ResidentResponse> residents = new ArrayList<>();
        if (location.getResidents() != null && !location.getResidents().isEmpty()) {
            residents = location.getResidents().stream().map(ResidentResponse::fromEntity).collect(Collectors.toList());
        }

        List<ApplianceResponse> appliances = new ArrayList<>();
        if (location.getAppliances() != null && !location.getAppliances().isEmpty()) {
            appliances = location.getAppliances().stream().map(ApplianceResponse::fromEntity).collect(Collectors.toList());
        }

        return new LocationResidentApplianceResponse(
            location.getId(), location.getAddress(), location.getNeighborhood(), location.getCity(),
            location.getState(), location.getNumber(), residents, appliances);
    }

}
