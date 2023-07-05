package graduate.energymonitor.entity;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Appliance {

    @Setter
    private Integer idAppliance;
    private String name;
    private String model;
    private Integer watts;

    public Appliance(String name, String model,Integer watts ){
        this.name = name;
        this.model = model;
        this.watts = watts;
    }

    public boolean identifyBy(String name, String model,Integer watts ){
        return this.name.equals(name)
            && this.model.equals(model)
            && this.watts.equals(watts);
    }

}
