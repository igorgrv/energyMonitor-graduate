package graduate.energymonitor.domains.resident.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import graduate.energymonitor.domains.location.controller.dto.LocationResponse;
import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.enums.GenderEnum;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;
import graduate.energymonitor.domains.user.controller.dto.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

// This DTO is used by the ResidentController as a response
@Schema(title = "ResidentUserResponse", description = "Object that represents a Resident and User response")
public record ResidentUserLocationResponse(
        Long id,
        String cpf,
        String name,
        LocalDate birth,
        GenderEnum gender,
        RelativesEnum relative,
        UserResponse user,
        List<LocationResponse> locations) {

    public static ResidentUserLocationResponse fromEntity(Resident resident) {
        UserResponse userResponse = new UserResponse(resident.getUser().getId(), resident.getUser().getUsername());

        List<LocationResponse> locations = new ArrayList<>();
        if (resident.getLocations() != null && !resident.getLocations().isEmpty()) {
            locations = resident.getLocations().stream().map(LocationResponse::fromEntity)
                    .collect(Collectors.toList());
        }

        return new ResidentUserLocationResponse(resident.getId(), resident.getCpf(), resident.getName(),
                resident.getBirth(),
                resident.getGender(), resident.getRelative(), userResponse, locations);
    }

}
