
package graduate.energymonitor.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graduate.energymonitor.controller.dto.ApplianceDto;
import graduate.energymonitor.entity.Appliance;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.exception.NotFoundException;
import graduate.energymonitor.repository.ApplianceRepository;
import jakarta.validation.Valid;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepository repository;

    public Set<Appliance> findAll() {
        return repository.findAll();
    }

    public Appliance findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("Appliance not found"));
    }

    public Appliance add(@Valid ApplianceDto request) {
        Appliance appliance = request.toAppliance();
        if (repository.exists(appliance))
            throw new AlreadyExistsException(String.format("Appliance %s already exists", appliance));
        return repository.add(appliance);
    }

    public void delete(@Valid ApplianceDto request) {
        Appliance appliance = request.toAppliance();
        repository.delete(appliance);
    }

    public void deleteByName(String name) {
        Appliance appliance = findByName(name);
        repository.delete(appliance);
    }

}
