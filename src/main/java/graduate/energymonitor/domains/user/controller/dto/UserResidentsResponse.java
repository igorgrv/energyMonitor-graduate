package graduate.energymonitor.domains.user.controller.dto;

import java.util.HashSet;
import java.util.Set;

import graduate.energymonitor.domains.resident.controller.dto.ResidentResponse;
import graduate.energymonitor.domains.user.entity.User;

public record UserResidentsResponse(Long id, String username, Set<ResidentResponse> residents) {

    public static UserResidentsResponse fromEntity(User user) {
        Set<ResidentResponse> residents = new HashSet<>();
        if (!user.getResidents().isEmpty()) {
            user.getResidents().forEach(resident -> {
                residents.add(ResidentResponse.fromEntity(resident));
            });
        }
        return new UserResidentsResponse(user.getId(), user.getUsername(), residents);
    }

}
