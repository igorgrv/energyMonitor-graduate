package graduate.energymonitor.repository;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import graduate.energymonitor.entity.Location;
import graduate.energymonitor.entity.enums.BrazilStatesEnum;

@Repository
public class LocationRepository {

    Set<Location> locations = new HashSet<>();

    public LocationRepository() {
        locations.add(new Location(1, "Rua 1", 1, "Vila Olímpia", "São Paulo", BrazilStatesEnum.SP));
        locations.add(new Location(2, "Rua 1.1", 1, "Pirituba", "São Paulo", BrazilStatesEnum.SP));
        locations.add(new Location(3, "Rua 2", 2, "Ipanema", "Rio de Janeiro", BrazilStatesEnum.RJ));
        locations.add(new Location(4, "Rua 3", 3, "Camanducaia", "Belo Horizonte", BrazilStatesEnum.MG));
    }

    public Set<Location> findAll() {
        return this.locations.stream()
            .sorted(Comparator.comparing(Location::getIdLocation))
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Optional<Location> findById(Integer idLocation) {
        return this.locations.stream()
            .filter(location -> location.getIdLocation().equals(idLocation)).findFirst();
    }

    public Location addLocation(Location location) {
        locations.add(location);
        return location;
    }

    public void deleteLocation(Location location) {
        locations.remove(location);
    }

    public boolean exists(Location location) {
        Optional<Location> locationFound = this.locations.stream().filter(locationExist -> locationExist.identifyBy(location.getAddress(),
            location.getNumber(),
            location.getNeighborhood(),
            location.getCity(),
            location.getState())).findFirst();

        if (locationFound.isEmpty()){
            return false;
        }
        return true;
    }

    public void updateLocation(Location location, Location locationUpdated) {
        this.locations.remove(location);
        locationUpdated.setIdLocation(location.getIdLocation());
        this.addLocation(locationUpdated);
    }

}
