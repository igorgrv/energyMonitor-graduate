package graduate.energymonitor.domains.resident.controller.dto;

import java.time.LocalDate;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.enums.GenderEnum;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;
import graduate.energymonitor.domains.user.controller.dto.UserResponse;

public record ResidentUserResponse(Long id,
        String cpf,
        String name,
        LocalDate birth,
        GenderEnum gender,
        RelativesEnum relative,
        UserResponse user) {

    public static ResidentUserResponse fromEntity(Resident resident) {
        UserResponse userResponse = new UserResponse(resident.getUser().getId(), resident.getUser().getUsername());

        return new ResidentUserResponse(resident.getId(), resident.getCpf(), resident.getName(),
                resident.getBirth(),
                resident.getGender(), resident.getRelative(), userResponse);
    }

}
