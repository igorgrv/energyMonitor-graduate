package graduate.energymonitor.domains.user.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank(message = "username is mandatory")
    @Size(min = 5, max = 20, message = "size must be between {min} and {max}")
    @Schema(description = "username to log in to the system", example = "fiaphouses")
    String username,

    @NotBlank(message = "cannot be null or empty")
    @Size(min = 8, max = 15, message = "must have {min} characters")
    @Schema(description = "password to log in to the system", example = "12345678")
    String password
) {

}
