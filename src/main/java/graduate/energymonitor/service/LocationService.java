package graduate.energymonitor.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import graduate.energymonitor.controller.dto.LocationDto;
import graduate.energymonitor.entity.Location;
import graduate.energymonitor.exception.NotFoundException;
import graduate.energymonitor.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private static final String LOCATION_NOT_FOUND = "Location not found";

    public List<Location> findAll() {
        return repository.findAll();
    }

    public Optional<Location> findById(UUID id) {
        return repository.findById(id);
    }

    public Location addLocation(LocationDto request) {
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
    public Location updateLocation(UUID id, LocationDto updatedLocationDto) {

        Location location = findById(id).orElseThrow(() -> new NotFoundException(LOCATION_NOT_FOUND));
        Location updatedUser = updatedLocationDto.returnEntityUpdated(location);

        return repository.save(updatedUser);
    }

}
