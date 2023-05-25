package graduate.energymonitor.controller.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import graduate.energymonitor.entity.GenderEnum;
import graduate.energymonitor.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record UserDto(@NotBlank(message = "name is mandatory") String name,
        @NotNull(message = "birth is mandatory") @DateTimeFormat(pattern = "YYYY-MM-dd") @Past LocalDate birth,
        @NotNull(message = "gender is mandatory") GenderEnum gender) {

    public User toUser() {
        return new User(name, birth, gender);
    }

}
