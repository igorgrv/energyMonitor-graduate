package graduate.energymonitor.domains.resident.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.enums.GenderEnum;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;
import graduate.energymonitor.domains.user.entity.User;

public record ResidentResponse(
        Long id,
        String cpf,
        String name,
        LocalDate birth,
        GenderEnum gender,
        RelativesEnum relative,
        User user) {

    public static List<ResidentResponse> fromEntity(List<Resident> residents) {
        return residents.stream()
                .map(resident -> new ResidentResponse(resident.getId(), resident.getCpf(), resident.getName(),
                        resident.getBirth(),
                        resident.getGender(), resident.getRelative(), resident.getUser()))
                .collect(Collectors.toList());
    }

    public static ResidentResponse fromEntity(Resident resident) {
        return new ResidentResponse(resident.getId(), resident.getCpf(), resident.getName(), resident.getBirth(),
                resident.getGender(), resident.getRelative(), resident.getUser());
    }

}
