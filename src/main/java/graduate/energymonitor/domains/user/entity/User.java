package graduate.energymonitor.domains.user.entity;

import java.util.HashSet;
import java.util.Set;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.user.controller.dto.UserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Table(name = "USERS")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Resident> residents = new HashSet<>();

    public User() {
    }

    public User(UserRequest dto) {
        this.username = dto.username();
        this.password = dto.password();
    }

    public User(String username, String password, Set<Resident> residents) {
        this.username = username;
        this.password = password;
        this.residents = residents;
    }

    public User(Long id, String username, String password, Set<Resident> residents) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.residents = residents;
    }

}
