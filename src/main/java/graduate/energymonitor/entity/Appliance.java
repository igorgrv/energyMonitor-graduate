package graduate.energymonitor.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Appliance {

    private String name;
    private String model;
    private Integer watts;
}
