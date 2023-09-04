package graduate.energymonitor.domains.consumption.dto;

import graduate.energymonitor.domains.appliance.controller.dto.ApplianceResponse;
import graduate.energymonitor.domains.consumption.entity.Consumption;

import java.time.Instant;

public record ConsumptionResponse(Long id,
                                  Instant startOfOperation,
                                  Instant endOfOperation,
                                  Double consumption) {

    public static ConsumptionResponse fromEntity(Consumption consumption) {
        return new ConsumptionResponse(consumption.getId()
            , consumption.getStartOfOperation()
            , consumption.getEndOfOperation()
            , consumption.getConsumption());
    }

}
