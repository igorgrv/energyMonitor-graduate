package graduate.energymonitor.domains.location.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graduate.energymonitor.domains.appliance.entity.Appliance;
import graduate.energymonitor.domains.location.entity.enums.BrazilStatesEnum;
import graduate.energymonitor.domains.resident.entity.Resident;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LOCATIONS")
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false, length = 100)
    private String neighborhood;

    @Column(nullable = false, length = 100)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 2)
    private BrazilStatesEnum state;

    @ManyToMany(mappedBy = "locations")
    private List<Resident> residents = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Appliance> appliances = new HashSet<>();

    public Location(String address, Integer number, String neighborhood, String city, BrazilStatesEnum state) {
        this.address = address;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public Location(Long id, String address, Integer number, String neighborhood, String city, BrazilStatesEnum state) {
        this.id = id;
        this.address = address;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Location location))
            return false;

        return getId().equals(location.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
