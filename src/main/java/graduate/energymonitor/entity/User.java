package graduate.energymonitor.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class User {

    private String name;
    private LocalDate birth;
    private GenderEnum gender;
    // private String grauParentesco; ????
}
