package graduate.energymonitor.domains.user.controller.dto;

import graduate.energymonitor.domains.user.entity.User;

public record UserResponse(Long id, String username) {

  public static UserResponse fromEntity(User user) {
    return new UserResponse(user.getId(), user.getUsername());
  }

}
