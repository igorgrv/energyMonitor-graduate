package graduate.energymonitor.domains.resident.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import graduate.energymonitor.domains.location.controller.dto.LocationResidentResponse;
import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.enums.GenderEnum;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;
import io.swagger.v3.oas.annotations.media.Schema;

// This DTO is used by UserResidentResponse, to allow the client to get a full list with User + Resident + Location
@Schema(title = "ResidentUserResponse", description = "Object that represents a Resident response")
public record ResidentLocationResponse(
        Long id,
        String cpf,
        String name,
        LocalDate birth,
        GenderEnum gender,
        RelativesEnum relative,
        List<LocationResidentResponse> locations) {

    public static ResidentLocationResponse fromEntity(Resident resident) {

        List<LocationResidentResponse> locations = new ArrayList<>();
        if (resident.getLocations() != null && !resident.getLocations().isEmpty()) {
            locations = resident.getLocations().stream().map(LocationResidentResponse::fromEntity).collect(Collectors.toList());
        }
        return new ResidentLocationResponse(
                resident.getId(), resident.getCpf(), resident.getName(), resident.getBirth(), resident.getGender(),
                resident.getRelative(), locations);
    }

}
