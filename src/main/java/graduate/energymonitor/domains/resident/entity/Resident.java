package graduate.energymonitor.domains.resident.entity;

import java.time.LocalDate;

import graduate.energymonitor.domains.resident.controller.dto.ResidentUserRequest;
import graduate.energymonitor.domains.resident.entity.enums.GenderEnum;
import graduate.energymonitor.domains.resident.entity.enums.RelativesEnum;
import graduate.energymonitor.domains.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RESIDENT")
@Entity
public class Resident {

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
    @JoinColumn(name = "users_id")
    private User user;

    public Resident(ResidentUserRequest dto) {
        this.cpf = dto.cpf();
        this.name = dto.name();
        this.gender = dto.gender();
        this.birth = dto.birth();
        this.relative = dto.relative();
    }

    public Resident(ResidentUserRequest dto, User user) {
        this.cpf = dto.cpf();
        this.name = dto.name();
        this.gender = dto.gender();
        this.birth = dto.birth();
        this.relative = dto.relative();
        this.user = user;
    }

}
