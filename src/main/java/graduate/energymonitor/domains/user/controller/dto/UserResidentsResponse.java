package graduate.energymonitor.domains.user.controller.dto;

import java.util.HashSet;
import java.util.Set;

import graduate.energymonitor.domains.resident.controller.dto.ResidentLocationResponse;
import graduate.energymonitor.domains.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
@Schema(title = "UserResidentsResponse", description = "Object that represents a User and Residents response")
public record UserResidentsResponse(Long id, String username, Set<ResidentLocationResponse> residents) {

    public static UserResidentsResponse fromEntity(User user) {
        Set<ResidentLocationResponse> residentsResponse = new HashSet<>();
        if (user.getResidents() != null && !user.getResidents().isEmpty()) {
            user.getResidents().forEach(resident -> residentsResponse.add(ResidentLocationResponse.fromEntity(resident)));
        }

        return new UserResidentsResponse(user.getId(), user.getUsername(), residentsResponse);
    }

}
