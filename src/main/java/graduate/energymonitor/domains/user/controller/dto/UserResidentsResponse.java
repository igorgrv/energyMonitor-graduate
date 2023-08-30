package graduate.energymonitor.domains.user.controller.dto;

import java.util.HashSet;
import java.util.Set;

import graduate.energymonitor.domains.resident.controller.dto.ResidentResponse;
import graduate.energymonitor.domains.user.entity.User;

public record UserResidentsResponse(Long id, String username, Set<ResidentResponse> residents) {

    public static UserResidentsResponse fromEntity(User user) {

        Set<ResidentResponse> residentsBasicResponse = new HashSet<>();
        user.getResidents().forEach(resident -> residentsBasicResponse.add(new ResidentResponse(resident)));
        return new UserResidentsResponse(user.getId(), user.getUsername(), residentsBasicResponse);
    }

}
