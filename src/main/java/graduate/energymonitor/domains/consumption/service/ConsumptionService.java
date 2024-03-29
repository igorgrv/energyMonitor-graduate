
package graduate.energymonitor.domains.consumption.service;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.appliance.entity.Appliance;
import graduate.energymonitor.domains.appliance.repository.ApplianceRepository;
import graduate.energymonitor.domains.consumption.dto.ConsumptionApplianceRequest;
import graduate.energymonitor.domains.consumption.dto.ConsumptionApplianceResponse;
import graduate.energymonitor.domains.consumption.entity.Consumption;
import graduate.energymonitor.domains.consumption.repository.ConsumptionRepository;
import graduate.energymonitor.exception.NotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ConsumptionService {

    private final ApplianceRepository applianceRepository;
    private final ConsumptionRepository consumptionRepository;
    private static final String APPLIANCE_NOT_FOUND = "Appliance not found";

    @Transactional(readOnly = true)
    public ConsumptionApplianceResponse saveConsumption(ConsumptionApplianceRequest consumptionApplianceRequest) {
        Appliance appliance = applianceRepository.findById(consumptionApplianceRequest.applianceId()).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        Double totalConsumption = getTotalConsumption(consumptionApplianceRequest, appliance);

        Consumption consumption = new Consumption(consumptionApplianceRequest.startOfOperation()
            , consumptionApplianceRequest.endOfOperation()
            , totalConsumption
            , appliance);

        Consumption consumptionCreated = consumptionRepository.save(consumption);
        return ConsumptionApplianceResponse.fromEntity(consumptionCreated);

    }

    private static Double getTotalConsumption(ConsumptionApplianceRequest consumptionApplianceRequest, Appliance appliance) {
        Duration hour = Duration.between(consumptionApplianceRequest.startOfOperation(), consumptionApplianceRequest.endOfOperation());
        long hourSeconds = hour.getSeconds();
        return Double.valueOf(hourSeconds * ((double) appliance.getWatts() / 3600));
    }


}
