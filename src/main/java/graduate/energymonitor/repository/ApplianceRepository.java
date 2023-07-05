
package graduate.energymonitor.repository;

import java.util.*;
import java.util.stream.Collectors;

import graduate.energymonitor.entity.Location;
import org.springframework.stereotype.Repository;

import graduate.energymonitor.entity.Appliance;

@Repository
public class ApplianceRepository {

    Set<Appliance> appliances = new HashSet<>();

    public ApplianceRepository() {
        appliances.add(new Appliance(1,"TV", "Samsung QN85A", 100));
        appliances.add(new Appliance(2,"XBOX", "Series X", 100));
        appliances.add(new Appliance(3,"Playstation", "Verison 5", 100));
    }

    public Set<Appliance> findAll() {
        return this.appliances.stream()
            .sorted(Comparator.comparing(Appliance::getIdAppliance))
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Optional<Appliance> findById(Integer idAppliance) {
        return this.appliances.stream()
            .filter(appliance -> appliance.getIdAppliance().equals(idAppliance)).findFirst();
    }

    public boolean exists(Appliance appliance) {
        Optional<Appliance> applianceFound = this.appliances.stream().filter(applianceExist -> applianceExist.identifyBy(appliance.getName(),
            appliance.getModel(),
            appliance.getWatts())).findFirst();

        if (applianceFound.isEmpty()){
            return false;
        }
        return true;
    }

    public Appliance addAppliance(Appliance appliance) {
        appliances.add(appliance);
        return appliance;
    }

    public void deleteAppliance(Appliance appliance) {
        appliances.remove(appliance);
    }

    public void updateAppliance(Appliance appliance, Appliance applianceUpdated) {
        this.appliances.remove(appliance);
        applianceUpdated.setIdAppliance(appliance.getIdAppliance());
        this.addAppliance(applianceUpdated);
    }

}
