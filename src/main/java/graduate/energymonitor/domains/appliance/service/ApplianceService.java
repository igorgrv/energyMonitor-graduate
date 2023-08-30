
package graduate.energymonitor.domains.appliance.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import graduate.energymonitor.domains.appliance.entity.Appliance;
import graduate.energymonitor.domains.appliance.entity.dto.ApplianceDto;
import graduate.energymonitor.domains.appliance.repository.ApplianceRepository;
import graduate.energymonitor.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplianceService {

    private final ApplianceRepository repository;
    private static final String APPLIANCE_NOT_FOUND = "Appliance not found";

    public List<Appliance> findAll() {
        return repository.findAll();
    }

    public Optional<Appliance> findById(UUID id) {
        return repository.findById(id);
    }

    public Appliance addAppliance(ApplianceDto request) {
        Appliance appliance = request.toAppliance();
        return repository.save(appliance);
    }

    @Transactional
    public Appliance deleteAppliance(UUID id) {
        Appliance appliance = findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        repository.delete(appliance);
        return appliance;
    }

    @Transactional
    public Appliance updateAppliance(UUID id, ApplianceDto updatedApplianceDto) {

        Appliance appliance = findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        Appliance updatedUser = updatedApplianceDto.returnEntityUpdated(appliance);

        return repository.save(updatedUser);
    }

}
