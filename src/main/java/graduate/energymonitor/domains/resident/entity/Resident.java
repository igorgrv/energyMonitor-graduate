package graduate.energymonitor.domains.resident.entity;

import java.time.LocalDate;
import java.util.List;

import graduate.energymonitor.domains.location.entity.Location;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RESIDENT")
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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Resident(ResidentUserRequest dto) {
        this.cpf = dto.cpf();
        this.name = dto.name();
        this.gender = dto.gender();
        this.birth = dto.birth();
        this.relative = dto.relative();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resident resident)) return false;

        return getId().equals(resident.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
