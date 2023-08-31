package graduate.energymonitor.domains.location.entity;

import java.util.ArrayList;
import java.util.List;

import graduate.energymonitor.domains.location.entity.enums.BrazilStatesEnum;
import graduate.energymonitor.domains.resident.entity.Resident;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

    public Location(String address, Integer number, String neighborhood, String city, BrazilStatesEnum state) {
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
