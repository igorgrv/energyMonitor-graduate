package graduate.energymonitor.domains.appliance.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import graduate.energymonitor.domains.appliance.entity.Appliance;
import graduate.energymonitor.domains.consumption.dto.ConsumptionApplianceResponse;
import graduate.energymonitor.domains.consumption.dto.ConsumptionResponse;
import graduate.energymonitor.domains.consumption.entity.Consumption;
import graduate.energymonitor.domains.location.controller.dto.LocationResponse;
import graduate.energymonitor.domains.resident.controller.dto.ResidentResponse;

public record ApplianceLocationResidentResponse(
        Long id,
        String name,
        String model,
        String brand,
        Integer watts,
        Long locationId,
        Double totalConsumption,
        List<ResidentResponse> residents) {

    public static ApplianceLocationResidentResponse fromEntity(Appliance appliance) {
        appliance.getResidents()
                .forEach(resident -> resident.getAppliances().add(appliance));

        LocationResponse location = LocationResponse.fromEntity(appliance.getLocation());
        List<ResidentResponse> residents = appliance.getResidents()
                .stream()
                .map(ResidentResponse::fromEntity)
                .collect(Collectors.toList());

        List<ConsumptionResponse> consumptions = appliance.getConsumptions()
            .stream()
            .map(ConsumptionResponse::fromEntity)
            .collect(Collectors.toList());

        Double totalConsumption = 0.0;
        for (ConsumptionResponse consumption : consumptions) {
            totalConsumption += consumption.consumption();
        }

        return new ApplianceLocationResidentResponse(appliance.getId(), appliance.getName(), appliance.getModel(), appliance.getBrand(),
                appliance.getWatts(), location.id(), totalConsumption, residents);
    }

}
