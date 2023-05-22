package graduate.energymonitor.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graduate.energymonitor.controller.dto.LocationDto;
import graduate.energymonitor.entity.Location;
import graduate.energymonitor.exception.NotFoundException;
import graduate.energymonitor.repository.LocationRepository;
import jakarta.validation.Valid;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    public Set<Location> findAll() {
        return repository.findAll();
    }

    public Location findByCity(String city) {
        return repository.findByCity(city).orElseThrow(() -> new NotFoundException("Location not found"));
    }

    public Location addLocation(@Valid LocationDto request) {
        Location location = request.toLocation();
        return repository.addLocation(location);
    }

    public void deleteLocation(@Valid LocationDto request) {
        Location location = request.toLocation();
        repository.deleteLocation(location);
    }

}
