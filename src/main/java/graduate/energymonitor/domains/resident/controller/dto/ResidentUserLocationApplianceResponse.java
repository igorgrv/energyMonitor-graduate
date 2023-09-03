package graduate.energymonitor.domains.resident.controller.dto;

import graduate.energymonitor.domains.appliance.controller.dto.ApplianceResponse;
import graduate.energymonitor.domains.location.controller.dto.LocationResponse;
import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.enums.GenderEnum;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;
import graduate.energymonitor.domains.user.controller.dto.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// This DTO is used by ResidentUserLocationApplianceResponse, to allow the client to get a full list with User + Resident + Location + Appliance
@Schema(title = "ResidentUserLocationApplianceResponse", description = "Object that represents a Resident response")
public record ResidentUserLocationApplianceResponse(
        Long id,
        String cpf,
        String name,
        LocalDate birth,
        GenderEnum gender,
        RelativesEnum relative,
        String username,
        List<LocationResponse> locations,
        List<ApplianceResponse> appliances) {

    public static ResidentUserLocationApplianceResponse fromEntity(Resident resident) {

        List<LocationResponse> locations = new ArrayList<>();
        if (resident.getLocations() != null && !resident.getLocations().isEmpty()) {
            locations = resident.getLocations().stream().map(LocationResponse::fromEntity).collect(Collectors.toList());
        }

        List<ApplianceResponse> appliances = new ArrayList<>();
        if (resident.getAppliances() != null && !resident.getAppliances().isEmpty()) {
            appliances = resident.getAppliances().stream().map(ApplianceResponse::fromEntity).collect(Collectors.toList());
        }

        return new ResidentUserLocationApplianceResponse(
                resident.getId(), resident.getCpf(), resident.getName(), resident.getBirth(), resident.getGender(),
                resident.getRelative(), resident.getUser().getUsername(), locations, appliances);
    }

}
