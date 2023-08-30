package graduate.energymonitor.domains.user.controller.dto;

import java.util.Set;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(title = "UserDTO", description = "Object that represents a data transfer object for a user")
public record UserResidentsRequest(

    @NotBlank(message = "username is mandatory")
    @Size(min = 5, max = 20, message = "size must be between {min} and {max}")
    @Schema(description = "username to log in to the system", example = "fiaphouses")
    String username,

    @NotBlank(message = "cannot be null or empty")
    @Size(min = 8, max = 15, message = "must have {min} characters")
    @Schema(description = "password to log in to the system", example = "12345678")
    String password,
    
    Set<Resident> residents) {

    public static User toEntity(UserResidentsRequest dto) {
        return new User(dto);
    }

}
