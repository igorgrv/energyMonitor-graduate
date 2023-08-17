package graduate.energymonitor.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "APPLIANCES")
@Entity
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "watts", nullable = false)
    private Integer watts;

    public Appliance(String name, String model, String brand, Integer watts) {
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.watts = watts;
    }

}
