package graduate.energymonitor.domains.user.controller.dto;

import graduate.energymonitor.domains.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "UserRequest", description = "Object that represents a User's response")
public record UserResponse(Long id, String username) {

  public static UserResponse fromEntity(User user) {
    return new UserResponse(user.getId(), user.getUsername());
  }

}
