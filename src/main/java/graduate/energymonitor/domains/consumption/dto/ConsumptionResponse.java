package graduate.energymonitor.domains.consumption.dto;

import java.time.Instant;

import graduate.energymonitor.domains.consumption.entity.Consumption;

public record ConsumptionResponse(Long id,
                                  Instant startOfOperation,
                                  Instant endOfOperation,
                                  Double consumption) {

    public static ConsumptionResponse fromEntity(Consumption consumption) {
        return new ConsumptionResponse(consumption.getId()
            , consumption.getStartOfOperation()
            , consumption.getEndOfOperation()
            , consumption.getConsumptionTaken());
    }

}
