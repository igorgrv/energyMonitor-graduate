package graduate.energymonitor.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import graduate.energymonitor.entity.Location;
import graduate.energymonitor.entity.enums.BrazilStatesEnum;

@Repository
public class LocationRepository {

    Set<Location> locations = new HashSet<>();

    public LocationRepository() {
        locations.add(new Location("Rua 1", 1, "Vila Olímpia", "São Paulo", BrazilStatesEnum.SP));
        locations.add(new Location("Rua 1.1", 1, "Pirituba", "São Paulo", BrazilStatesEnum.SP));
        locations.add(new Location("Rua 2", 2, "Ipanema", "Rio de Janeiro", BrazilStatesEnum.RJ));
        locations.add(new Location("Rua 3", 3, "Camanducaia", "Belo Horizonte", BrazilStatesEnum.MG));
    }

    public Set<Location> findAll() {
        return this.locations;
    }

    public Set<Location> findByCity(String city) {
        return this.locations.stream()
                .filter(location -> location.getCity().toLowerCase().equalsIgnoreCase(city.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public Location addLocation(Location location) {
        locations.add(location);
        return location;
    }

    public void deleteLocation(Location location) {
        locations.remove(location);
    }

    public boolean exists(Location location) {
        return this.locations.contains(location);
    }

}
