package graduate.energymonitor.domains.consumption.dto;

import java.time.Instant;

import graduate.energymonitor.domains.appliance.controller.dto.ApplianceResponse;
import graduate.energymonitor.domains.consumption.entity.Consumption;

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
            , consumption.getConsumptionTaken()
            , applianceResponse);
    }

}
