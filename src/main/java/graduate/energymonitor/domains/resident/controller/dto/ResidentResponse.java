package graduate.energymonitor.domains.resident.controller.dto;

import java.time.LocalDate;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.enums.GenderEnum;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "ResidentUserResponse", description = "Object that represents a Resident response")
public record ResidentResponse(
        Long id,
        String cpf,
        String name,
        LocalDate birth,
        GenderEnum gender,
        RelativesEnum relative) {

    public static ResidentResponse fromEntity(Resident resident) {
        return new ResidentResponse(
                resident.getId(), resident.getCpf(), resident.getName(), resident.getBirth(), resident.getGender(),
                resident.getRelative()

        );
    }

}
