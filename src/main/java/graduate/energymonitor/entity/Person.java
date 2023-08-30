package graduate.energymonitor.entity;

import graduate.energymonitor.controller.dto.PersonUserDto;
import graduate.energymonitor.entity.enums.GenderEnum;
import graduate.energymonitor.entity.enums.RelativesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSON")
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    public Person(PersonUserDto dto) {
        this.cpf = dto.cpf();
        this.name = dto.name();
        this.gender = dto.gender();
        this.birth = dto.birth();
        this.relative = dto.relative();
        this.user = dto.user();
    }

}
