package graduate.energymonitor.domains.user.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.user.controller.dto.UserRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
    private List<Resident> residents = new ArrayList<>();

    public User() {
    }

    public User(UserRequest dto) {
        this.username = dto.username();
        this.password = dto.password();
    }

    public User(String username, String password, List<Resident> residents) {
        this.username = username;
        this.password = password;
        this.residents = residents;
    }

    public User(Long id, String username, String password, List<Resident> residents) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.residents = residents;
    }

}
