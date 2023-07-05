package graduate.energymonitor.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import graduate.energymonitor.controller.dto.LocationDto;
import graduate.energymonitor.entity.Location;
import graduate.energymonitor.service.LocationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<Set<Location>> getAllLocations() {
        Set<Location> locations = locationService.findAll();
        return ResponseEntity.ok().body(locations);
    }

    @GetMapping("/{id_location}")
    public ResponseEntity getLocationById(@PathVariable("id_location") Integer idLocation) {
        Optional<Location> location = locationService.findById(idLocation);

        if (location.isEmpty()) {
            return ResponseEntity.badRequest().body("Location not found");
        }

        return ResponseEntity.ok().body(location);
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@Valid @RequestBody LocationDto request) {
        Location location = locationService.addLocation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(location);
    }

    @PutMapping("/{id_location}")
    public ResponseEntity updateLocation(@PathVariable("id_location") Integer idLocation, @Valid @RequestBody LocationDto request) {

        Optional<Location> location = locationService.findById(idLocation);

        if (location.isEmpty()) {
            return ResponseEntity.badRequest().body("Location not found");
        }

        locationService.updateLocation(location.get(), request);

        return ResponseEntity.ok().body(request);

    }

    @DeleteMapping("/{id_location}")
    public ResponseEntity<String> deleteLocation(@PathVariable("id_location") Integer idLocation) {
        Optional<Location> location = locationService.findById(idLocation);

        if (location.isEmpty()) {
            return ResponseEntity.badRequest().body("Location not found");
        }
        locationService.deleteLocation(location.get());
        return ResponseEntity.ok().body("Location was deleted");
    }
}
