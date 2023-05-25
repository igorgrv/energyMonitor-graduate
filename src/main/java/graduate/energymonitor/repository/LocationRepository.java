package graduate.energymonitor.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import graduate.energymonitor.entity.Location;

@Repository
public class LocationRepository {

    Set<Location> locations = new HashSet<>();

    public LocationRepository() {
        locations.add(new Location("Rua 1", 1, "Vila Olímpia", "São Paulo", "São Paulo"));
        locations.add(new Location("Rua 2", 2, "Santo Amaro", "Rio de Janeiro", "Rio de Janeiro"));
        locations.add(new Location("Rua 3", 3, "Barra Funda", "Belo Horizonte", "Minas Gerais"));
    }

    public Set<Location> findAll() {
        return this.locations;
    }

    public Optional<Location> findByCity(String city) {
        return this.locations.stream()
                .filter(location -> location.getCity().toLowerCase().equalsIgnoreCase(city.toLowerCase())).findFirst();
    }

    public Location addLocation(Location location) {
        locations.add(location);
        return location;
    }

    public void deleteLocation(Location location) {
        locations.remove(location);
    }

}
