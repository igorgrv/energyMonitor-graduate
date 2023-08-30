package graduate.energymonitor.domains.user.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import graduate.energymonitor.domains.person.entity.Person;
import graduate.energymonitor.domains.person.entity.enums.GenderEnum;
import graduate.energymonitor.domains.person.entity.enums.RelativesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
//@NoArgsConstructor
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

    @OneToMany(mappedBy = "usuario")
    private List<Person> person;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
