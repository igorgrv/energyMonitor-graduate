
package graduate.energymonitor.domains.appliance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.appliance.controller.dto.ApplianceDto;
import graduate.energymonitor.domains.appliance.controller.dto.ApplianceLocationResidentRequest;
import graduate.energymonitor.domains.appliance.controller.dto.ApplianceLocationResidentResponse;
import graduate.energymonitor.domains.appliance.entity.Appliance;
import graduate.energymonitor.domains.appliance.repository.ApplianceRepository;
import graduate.energymonitor.domains.location.controller.dto.LocationResidentResponse;
import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.service.LocationService;
import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.service.ResidentService;
import graduate.energymonitor.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplianceService {

    private final ApplianceRepository repository;
    private final LocationService locationService;
    private final ResidentService residentService;
    private static final String APPLIANCE_NOT_FOUND = "Appliance not found";

    @Transactional(readOnly = true)
    public List<ApplianceLocationResidentResponse> findAll() {
        List<Appliance> appliances = repository.findAll();
        return appliances.stream().map(ApplianceLocationResidentResponse::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ApplianceLocationResidentResponse findById(Long id) {
        Appliance appliance = repository.findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        return ApplianceLocationResidentResponse.fromEntity(appliance);
    }

    @Transactional
    public ApplianceLocationResidentResponse addAppliance(ApplianceLocationResidentRequest request) {
        Appliance appliance = request.toEntity();

        Location location = locationService.findByIdLocation(request.locationId());
        appliance.setLocation(location);

        List<Resident> residents = request.residentIds()
                .stream()
                .map(residentService::findById)
                .collect(Collectors.toList());
        appliance.setResidents(residents);
        Appliance applianceSaved = repository.save(appliance);

        return ApplianceLocationResidentResponse.fromEntity(applianceSaved);
    }

    @Transactional
    public Appliance deleteAppliance(Long id) {
        Appliance appliance = repository.findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        repository.delete(appliance);
        return appliance;
    }

    @Transactional
    public Appliance updateAppliance(Long id, ApplianceDto updatedApplianceDto) {

        Appliance appliance = repository.findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        Appliance updatedUser = updatedApplianceDto.returnEntityUpdated(appliance);

        return repository.save(updatedUser);
    }

}
