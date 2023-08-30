package graduate.energymonitor.domains.resident.controller.dto;

import java.time.LocalDate;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.enums.GenderEnum;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;

public record ResidentBasicResponse(
        Long id,
        String cpf,
        String name,
        LocalDate birth,
        GenderEnum gender,
        RelativesEnum relative) {

    public ResidentBasicResponse(Resident resident) {
        this(resident.getId(), resident.getCpf(), resident.getName(), resident.getBirth(), resident.getGender(),
                resident.getRelative());
    }

}
