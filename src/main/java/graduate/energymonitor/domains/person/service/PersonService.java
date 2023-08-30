
package graduate.energymonitor.domains.person.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import graduate.energymonitor.domains.person.entity.Person;
import graduate.energymonitor.domains.person.entity.dto.PersonUserDto;
import graduate.energymonitor.domains.person.repository.PersonRepository;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private static final String USER_NOT_FOUND = "Person not found";

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Optional<Person> findById(UUID id) {
        return repository.findById(id);
    }

    public Person addPerson(PersonUserDto request) {
        Person person = request.toPerson();

        if (repository.existsByCpf(person.getCpf()))
            throw new AlreadyExistsException(
                    String.format("Person: cpf=%s, person=%s, birth=%s, gender=%s, relative=%s,  already exists",
                            person.getCpf(), person.getName(), person.getBirth(), person.getGender(),
                            person.getRelative()));

        return repository.save(person);
    }

    @Transactional
    public Person deletePerson(UUID id) {
        Person person = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        repository.delete(person);
        return person;
    }

    // @Transactional
    // public Person updatePerson(UUID id, PersonUserDto updatedPersonDto) {

    //     Person existingPerson = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    //     Person updatedPerson = updatedPersonDto.returnEntityUpdated(existingPerson);

    //     return repository.save(updatedPerson);
    // }

}
