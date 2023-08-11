package graduate.energymonitor.entity;

import java.time.LocalDate;

import graduate.energymonitor.entity.enums.GenderEnum;
import graduate.energymonitor.entity.enums.RelativesEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class User {

    @Setter
    private Integer idUser;
    private String name;
    private LocalDate birth;
    private GenderEnum gender;
    private RelativesEnum relative;

    public User(String name, LocalDate birth, GenderEnum gender, RelativesEnum relative) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.relative = relative;
    }

    public boolean identifyBy(String name, LocalDate birth, GenderEnum gender, RelativesEnum relative) {
        return this.name.equals(name)
                && this.birth.equals(birth)
                && this.gender.equals(gender)
                && this.relative.equals(relative);
    }
}
