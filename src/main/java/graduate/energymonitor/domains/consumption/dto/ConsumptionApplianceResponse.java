package graduate.energymonitor.domains.consumption.dto;

import graduate.energymonitor.domains.appliance.controller.dto.ApplianceResponse;
import graduate.energymonitor.domains.consumption.entity.Consumption;
import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;
import graduate.energymonitor.domains.user.controller.dto.UserResponse;

import java.time.Instant;

public record ConsumptionApplianceResponse(Long id,
                                           Instant startOfOperation,
                                           Instant endOfOperation,
                                           Double consumption,
                                           ApplianceResponse appliance) {

    public static ConsumptionApplianceResponse fromEntity(Consumption consumption) {
        ApplianceResponse applianceResponse = new ApplianceResponse(consumption.getAppliance().getId()
            ,consumption.getAppliance().getName()
            ,consumption.getAppliance().getModel()
            ,consumption.getAppliance().getBrand()
            ,consumption.getAppliance().getWatts());

        return new ConsumptionApplianceResponse(consumption.getId()
            , consumption.getStartOfOperation()
            , consumption.getEndOfOperation()
            , consumption.getConsumption()
            , applianceResponse);
    }

}
