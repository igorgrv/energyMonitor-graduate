package graduate.energymonitor.domains.consumption.entity;

import graduate.energymonitor.domains.appliance.entity.Appliance;
import graduate.energymonitor.domains.consumption.dto.ConsumptionApplianceRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONSUMPTION")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startOfOperation", nullable = false)
    private Instant startOfOperation;

    @Column(name = "endOfOperation", nullable = false)
    private Instant endOfOperation;

    @Column(name = "consumption", nullable = false)
    private Double consumption;

    @ManyToOne
    @JoinColumn(name = "appliance_id", nullable = false)
    private Appliance appliance;

    public Consumption(Instant startOfOperation, Instant endOfOperation, Double consumption, Appliance appliance) {
        this.startOfOperation = startOfOperation;
        this.endOfOperation = endOfOperation;
        this.consumption = consumption;
        this.appliance = appliance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Consumption consumption))
            return false;

        return getId().equals(consumption.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
