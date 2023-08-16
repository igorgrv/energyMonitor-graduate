package graduate.energymonitor.entity;

import java.time.LocalDate;
import java.util.UUID;

import graduate.energymonitor.entity.enums.GenderEnum;
import graduate.energymonitor.entity.enums.RelativesEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @Column(name = "name")
    private String name;

    @Column(nullable = false, columnDefinition = "date")
    private LocalDate birth;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Enumerated(EnumType.STRING)
    private RelativesEnum relative;

    public User(String cpf, String name, LocalDate birth, GenderEnum gender, RelativesEnum relative) {
        this.cpf = cpf;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.relative = relative;
    }

}
