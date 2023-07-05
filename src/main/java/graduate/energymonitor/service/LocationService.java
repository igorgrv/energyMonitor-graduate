package graduate.energymonitor.service;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graduate.energymonitor.controller.dto.LocationDto;
import graduate.energymonitor.entity.Location;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    public Set<Location> findAll() {
        return repository.findAll();
    }

    public Optional<Location> findById(Integer idLocation) {
        return repository.findById(idLocation);
    }

    public Location addLocation(LocationDto request) {
        Location location = request.toLocation();

        if (repository.exists(location))
            throw new AlreadyExistsException
                (String.format("Location: address=%s, number=%s, neighborhood=%s, city=%s, state=%s,  already exists", location.getAddress()
                ,location.getNumber()
                ,location.getNeighborhood()
                ,location.getCity()
                ,location.getState()));

        location.setIdLocation(new Random().nextInt(Integer.MAX_VALUE));
        return repository.addLocation(location);
    }

    public void updateLocation(Location location, LocationDto request) {
        Location locationUpdated = request.toLocation();
        repository.updateLocation(location, locationUpdated);
    }

    public void deleteLocation(Location location) {
        repository.deleteLocation(location);
    }

}
