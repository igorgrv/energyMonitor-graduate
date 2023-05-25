
package graduate.energymonitor.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import graduate.energymonitor.entity.Appliance;

@Repository
public class ApplianceRepository {

    Set<Appliance> appliances = new HashSet<>();

    public ApplianceRepository() {
        appliances.add(new Appliance("TV", "Samsung QN85A", 100));
        appliances.add(new Appliance("XBOX", "Series X", 100));
        appliances.add(new Appliance("Playstation", "Verison 5", 100));
    }

    public Set<Appliance> findAll() {
        return this.appliances;
    }

    public Optional<Appliance> findByName(String name) {
        return this.appliances.stream()
                .filter(appliance -> appliance.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase()))
                .findFirst();
    }

    public Appliance add(Appliance appliance) {
        appliances.add(appliance);
        return appliance;
    }

    public void delete(Appliance appliance) {
        appliances.remove(appliance);
    }

}
