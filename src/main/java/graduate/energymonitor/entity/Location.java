package graduate.energymonitor.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Location {

    private String address;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;

}
