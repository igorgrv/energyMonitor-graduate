package graduate.energymonitor.domains.location.service;

import java.util.List;
import java.util.stream.Collectors;

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
    private static final String LOCATION_NOT_FOUND = "Location not found";

    @Transactional(readOnly = true)
    public List<LocationResidentResponse> findAll() {
        List<Location> locations = repository.findAll();
        return locations.stream().map(LocationResidentResponse::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LocationResidentResponse findById(Long id) {
        Location location = repository.findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));
        return LocationResidentResponse.fromEntity(location);
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
        Location location = repository.findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));
        repository.delete(location);
        return LocationResidentResponse.fromEntity(location);
    }

    @Transactional
    public LocationResidentResponse updateLocation(Long id, LocationResidentRequest updatedLocationDto) {

        Location location = repository.findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));
        Location updatedUser = updatedLocationDto.returnEntityUpdated(location);

        Location locationUpdated = repository.save(updatedUser);
        return LocationResidentResponse.fromEntity(locationUpdated);
    }

}
