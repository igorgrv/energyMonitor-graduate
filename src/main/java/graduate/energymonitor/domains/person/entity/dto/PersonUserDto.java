package graduate.energymonitor.domains.person.entity.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import graduate.energymonitor.domains.person.entity.Person;
import graduate.energymonitor.domains.person.entity.enums.GenderEnum;
import graduate.energymonitor.domains.person.entity.enums.RelativesEnum;
import graduate.energymonitor.domains.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(title = "UserDTO", description = "Object that represents a data transfer object for a user")
public record PersonUserDto(

        @NotBlank(message = "name is mandatory")
        @Size(min = 2, max = 150, message = "size must be between {min} and {max}")
        @Pattern(regexp = "[A-zÀ-ú\s]+", message = "must contain only letters")
        @Schema(description = "User Name", example = "Igor Romero")
        String name,

        @NotBlank(message = "cannot be null or empty")
        @Size(min = 11, max = 11, message = "must have {min} characters")
        @CPF
        @Schema(description = "CPF to identify an user", example = "00911719032")
        String cpf,

        @NotNull(message = "birth is mandatory")
        @DateTimeFormat(pattern = "YYYY-MM-dd")
        @Past
        @Schema(description = "When the person was born", example = "1996-01-01")
        LocalDate birth,

        @NotNull(message = "gender is mandatory") GenderEnum gender,
        @NotNull(message = "relative is mandatory") RelativesEnum relative,
        @NotNull(message = "id user is mandatory") User user)

{

    public Person toPerson() {
        return new Person(this);
    }

    // public Person returnEntityUpdated(User user) {
    //     user.setCpf(cpf);
    //     user.setName(name);
    //     user.setBirth(birth);
    //     user.setGender(gender);
    //     user.setRelative(relative);

    //     return user;
    // }

}
