package graduate.energymonitor.domains.location.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import graduate.energymonitor.domains.location.controller.dto.LocationResidentApplianceResponse;
import graduate.energymonitor.domains.location.controller.dto.LocationResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.location.controller.dto.LocationResidentRequest;
import graduate.energymonitor.domains.location.controller.dto.LocationResidentResponse;
import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.repository.LocationRepository;
import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.service.ResidentService;
import graduate.energymonitor.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private final ResidentService residentService;
    private static final String LOCATION_NOT_FOUND = "Location not found - ID: ";

    @Transactional(readOnly = true)
    public List<LocationResponse> findAll() {
        List<Location> locations = repository.findAll();
        return locations.stream().map(LocationResponse::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LocationResidentApplianceResponse findById(Long id) {
        Location location = repository.findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND + id));
        return LocationResidentApplianceResponse.fromEntity(location);
    }

    @Transactional(readOnly = true)
    public Location findByIdLocation(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND + id));
    }

    @Transactional
    public LocationResidentResponse addLocation(LocationResidentRequest request) {
        Location location = request.toLocation();

        List<Resident> residents = request.residentIds().stream().map(residentService::findById)
                .collect(Collectors.toList());
        location.setResidents(residents);

        Location locationCreated = repository.save(location);
        return LocationResidentResponse.fromEntity(locationCreated);
    }

    @Transactional
    public LocationResidentResponse deleteLocation(Long id) {
        Location location = repository.findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND + id));
        repository.delete(location);
        return LocationResidentResponse.fromEntity(location);
    }

    @Transactional
    public LocationResidentResponse updateLocation(Long id, LocationResidentRequest request) {

        Location location = repository.findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND + id));
        Location updatedUser = request.returnEntityUpdated(location);

        Location locationUpdated = repository.save(updatedUser);
        return LocationResidentResponse.fromEntity(locationUpdated);
    }

}
