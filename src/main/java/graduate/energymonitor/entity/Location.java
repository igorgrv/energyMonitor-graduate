package graduate.energymonitor.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class Location {

    private String address;
    private Integer number;
    private String neighborhood;

    @Setter
    private String city;
    private String state;

    public Location(String address, Integer number, String neighborhood, String city, String state) {
        this.address = address;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

}
