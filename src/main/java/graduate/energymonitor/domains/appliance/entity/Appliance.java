package graduate.energymonitor.domains.appliance.entity;

import graduate.energymonitor.domains.appliance.controller.dto.ApplianceLocationDto;
import graduate.energymonitor.domains.location.entity.Location;
import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "APPLIANCES")
@Entity
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "watts", nullable = false)
    private Integer watts;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public Appliance(String name, String model, String brand, Integer watts) {
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.watts = watts;
    }

    public Appliance(ApplianceLocationDto applianceLocationDto) {
        this.name = applianceLocationDto.name();
        this.model = applianceLocationDto.model();
        this.brand = applianceLocationDto.brand();
        this.watts = applianceLocationDto.watts();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Appliance appliance))
            return false;

        return getId().equals(appliance.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
