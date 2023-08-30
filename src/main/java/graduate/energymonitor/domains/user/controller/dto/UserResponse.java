package graduate.energymonitor.domains.user.controller.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.user.entity.User;

public record UserResponse(Long id, String username, Set<Resident> residents) {

    public static List<UserResponse> fromEntity(List<User> users) {
        return users.stream().map(user -> new UserResponse(user.getId(), user.getUsername(), user.getResidents()))
                .collect(Collectors.toList());
    }

    public static UserResponse fromEntity(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getResidents());
    }

}
