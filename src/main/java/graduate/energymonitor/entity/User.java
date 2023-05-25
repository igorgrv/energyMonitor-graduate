package graduate.energymonitor.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class User {

    private String name;
    private LocalDate birth;
    private GenderEnum gender;
    // private String grauParentesco; ????
}
