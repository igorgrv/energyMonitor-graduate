package graduate.energymonitor.entity;

import graduate.energymonitor.entity.enums.BrazilStatesEnum;
import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Location {

    @Setter
    private Integer idLocation;
    private String address;
    private Integer number;
    private String neighborhood;
    private String city;
    private BrazilStatesEnum state;

    public Location(String address,Integer number, String neighborhood,String city, BrazilStatesEnum state ){
            this.address = address;
            this.number = number;
            this.neighborhood = neighborhood;
            this.city = city;
            this.state = state;
    }

    public boolean identifyBy(String address,Integer number, String neighborhood,String city, BrazilStatesEnum state){
        return this.address.equals(address)
            && this.number.equals(number)
            && this.neighborhood.equals(neighborhood)
            && this.city.equals(city)
            && this.state.equals(state);

    }
}
