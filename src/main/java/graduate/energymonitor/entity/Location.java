package graduate.energymonitor.entity;

import java.util.UUID;

import graduate.energymonitor.entity.enums.BrazilStatesEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LOCATIONS")
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    public Location(String address, Integer number, String neighborhood, String city, BrazilStatesEnum state) {
        this.address = address;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

}
