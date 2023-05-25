package graduate.energymonitor.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Location {

    private String address;
    private Integer number;
    private String neighborhood;

    @Setter
    private String city;
    private String state;

}
