package graduate.energymonitor.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{city}")
    public ResponseEntity<Set<Location>> getLocationByAddress(@PathVariable String city) {
        Set<Location> locations = locationService.findByCity(city);
        return ResponseEntity.ok().body(locations);
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@Valid @RequestBody LocationDto request) {
        Location location = locationService.addLocation(request);
        return ResponseEntity.ok().body(location);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLocation(@Valid @RequestBody LocationDto request) {
        locationService.deleteLocation(request);
        return ResponseEntity.ok().body("Location was deleted");
    }
}
