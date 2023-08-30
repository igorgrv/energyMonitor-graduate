package graduate.energymonitor.domains.user.controller.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import graduate.energymonitor.domains.resident.controller.dto.ResidentBasicResponse;
import graduate.energymonitor.domains.user.entity.User;

public record UserResponse(Long id, String username, Set<ResidentBasicResponse> residents) {

    public static List<UserResponse> fromEntity(List<User> users) {

        Set<ResidentBasicResponse> residentsBasicResponse = new HashSet<>();
        users.stream().forEach(user -> user.getResidents()
                .forEach(resident -> residentsBasicResponse.add(new ResidentBasicResponse(resident))));

        return users.stream().map(user -> new UserResponse(user.getId(), user.getUsername(), residentsBasicResponse))
                .collect(Collectors.toList());
    }

    public static UserResponse fromEntity(User user) {

        Set<ResidentBasicResponse> residentsBasicResponse = new HashSet<>();
        user.getResidents().forEach(resident -> residentsBasicResponse.add(new ResidentBasicResponse(resident)));
        return new UserResponse(user.getId(), user.getUsername(), residentsBasicResponse);
    }

}
