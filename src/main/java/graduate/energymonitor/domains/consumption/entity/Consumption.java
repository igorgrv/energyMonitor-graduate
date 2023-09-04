package graduate.energymonitor.domains.consumption.entity;

import java.time.Instant;

import graduate.energymonitor.domains.appliance.entity.Appliance;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "consumptionTaken", nullable = false)
    private Double consumptionTaken;

    @ManyToOne
    @JoinColumn(name = "appliance_id", nullable = false)
    private Appliance appliance;

    public Consumption(Instant startOfOperation, Instant endOfOperation, Double consumptionTaken, Appliance appliance) {
        this.startOfOperation = startOfOperation;
        this.endOfOperation = endOfOperation;
        this.consumptionTaken = consumptionTaken;
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
