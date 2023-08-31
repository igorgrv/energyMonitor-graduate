package graduate.energymonitor.domains.user.controller.dto;

import java.util.HashSet;
import java.util.Set;

import graduate.energymonitor.domains.resident.controller.dto.ResidentResponse;
import graduate.energymonitor.domains.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
@Schema(title = "UserResidentsResponse", description = "Object that represents a User and Residents response")
public record UserResidentsResponse(Long id, String username, Set<ResidentResponse> residents) {

    public static UserResidentsResponse fromEntity(User user) {
        Set<ResidentResponse> residentsResponse = new HashSet<>();
        if (user.getResidents() != null && !user.getResidents().isEmpty()) {
            user.getResidents().forEach(resident -> residentsResponse.add(new ResidentResponse(resident)));
        }

        return new UserResidentsResponse(user.getId(), user.getUsername(), residentsResponse);
    }

}
