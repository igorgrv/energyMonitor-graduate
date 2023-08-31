package graduate.energymonitor.domains.location.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.location.controller.dto.LocationRequest;
import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.location.repository.LocationRepository;
import graduate.energymonitor.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private static final String LOCATION_NOT_FOUND = "Location not found";

    @Transactional(readOnly = true)
    public List<Location> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Location> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Location addLocation(LocationRequest request) {
        Location location = request.toLocation();
        return repository.save(location);
    }

    @Transactional
    public Location deleteLocation(UUID id) {
        Location location = findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));
        repository.delete(location);
        return location;
    }

    @Transactional
    public Location updateLocation(UUID id, LocationRequest updatedLocationDto) {

        Location location = findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));
        Location updatedUser = updatedLocationDto.returnEntityUpdated(location);

        return repository.save(updatedUser);
    }

}
