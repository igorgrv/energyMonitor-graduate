
package graduate.energymonitor.domains.appliance.service;

import java.util.List;
import java.util.Optional;


import graduate.energymonitor.domains.appliance.controller.dto.ApplianceLocationDto;
import graduate.energymonitor.domains.location.controller.dto.LocationResidentResponse;
import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.service.LocationService;
import org.springframework.stereotype.Service;

import graduate.energymonitor.domains.appliance.controller.dto.ApplianceDto;
import graduate.energymonitor.domains.appliance.entity.Appliance;
import graduate.energymonitor.domains.appliance.repository.ApplianceRepository;
import graduate.energymonitor.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplianceService {

    private final ApplianceRepository repository;
    private final LocationService locationService;
    private static final String APPLIANCE_NOT_FOUND = "Appliance not found";

    @Transactional(readOnly = true)
    public List<Appliance> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Appliance> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Appliance addAppliance(ApplianceLocationDto request) {
        Appliance appliance = request.toEntity();

        LocationResidentResponse locationResidentResponse = locationService.findById(request.locationId());
        Location location = new Location(
             request.locationId()
            , locationResidentResponse.address()
            , locationResidentResponse.number()
            , locationResidentResponse.neighborhood()
            , locationResidentResponse.city()
            , locationResidentResponse.state());
        appliance.setLocation(location);

        return repository.save(appliance);
    }

    @Transactional
    public Appliance deleteAppliance(Long id) {
        Appliance appliance = findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        repository.delete(appliance);
        return appliance;
    }

    @Transactional
    public Appliance updateAppliance(Long id, ApplianceDto updatedApplianceDto) {

        Appliance appliance = findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        Appliance updatedUser = updatedApplianceDto.returnEntityUpdated(appliance);

        return repository.save(updatedUser);
    }

}
