
package graduate.energymonitor.service;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graduate.energymonitor.controller.dto.ApplianceDto;
import graduate.energymonitor.entity.Appliance;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.repository.ApplianceRepository;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepository repository;

    public Set<Appliance> findAll() {
        return repository.findAll();
    }

    public Optional<Appliance> findById(Integer idAppliance) {
        return repository.findById(idAppliance);
    }

    public Appliance addAppliance(ApplianceDto request) {
        Appliance appliance = request.toAppliance();

        if (repository.exists(appliance))
            throw new AlreadyExistsException
                (String.format("Appliance: name=%s, model=%s, watts=%s,  already exists", appliance.getName()
                    ,appliance.getModel()
                    ,appliance.getWatts()));

        appliance.setIdAppliance(new Random().nextInt(Integer.MAX_VALUE));
        return repository.addAppliance(appliance);
    }

    public void updateAppliance(Appliance appliance, ApplianceDto request) {
        Appliance applianceUpdated = request.toAppliance();
        repository.updateAppliance(appliance, applianceUpdated);
    }

    public void deleteAppliance(Appliance appliance) {
        repository.deleteAppliance(appliance);
    }
}
