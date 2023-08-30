package graduate.energymonitor.domains.resident.controller.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.enums.GenderEnum;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(title = "UserDTO", description = "Object that represents a data transfer object for a user")
public record ResidentUserRequest(

    @NotBlank(message = "name is mandatory")
    @Size(min = 2, max = 150, message = "size must be between {min} and {max}")
    @Pattern(regexp = "[A-zÀ-ú\s]+", message = "must contain only letters")
    @Schema(description = "User Name", example = "Alex Igor")
    String name,

    @NotBlank(message = "cannot be null or empty")
    @Size(min = 11, max = 11, message = "must have {min} characters")
    @CPF
    @Schema(description = "CPF to identify an user", example = "00911719032")
    String cpf,

    @NotNull(message = "birth is mandatory")
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @Past
    @Schema(description = "When the resident was born", example = "1996-01-01")
    LocalDate birth,

    @NotNull(message = "gender is mandatory") GenderEnum gender,
    @NotNull(message = "relative is mandatory") RelativesEnum relative,
    
    @NotNull(message = "username is mandatory")
    @Schema(description = "User to access the app", example = "fiap_user1")
    String username) {

    public Resident toEntity() {
        return new Resident(this);
    }

    public Resident returnEntityUpdated(Resident resident) {
        resident.setCpf(cpf);
        resident.setName(name);
        resident.setBirth(birth);
        resident.setGender(gender);
        resident.setRelative(relative);

        return resident;
    }

}
