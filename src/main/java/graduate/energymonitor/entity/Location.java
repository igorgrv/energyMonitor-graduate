package graduate.energymonitor.entity;

import graduate.energymonitor.entity.enums.BrazilStatesEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Location {

    @Getter
    private String address;

    @Getter
    private Integer number;

    @Getter
    private String neighborhood;

    @Getter
    private String city;

    private BrazilStatesEnum state;

    public String getState() {
        return state.getState();
    }

}
