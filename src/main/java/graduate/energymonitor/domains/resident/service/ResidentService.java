
package graduate.energymonitor.domains.resident.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.resident.entity.Resident;
import graduate.energymonitor.domains.resident.entity.dto.ResidentDto;
import graduate.energymonitor.domains.resident.repository.ResidentRepository;
import graduate.energymonitor.domains.user.entity.User;
import graduate.energymonitor.domains.user.service.UserService;
import graduate.energymonitor.exception.AlreadyExistsException;
import graduate.energymonitor.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResidentService {

    private final ResidentRepository repository;
    private final UserService userService;
    private static final String USER_NOT_FOUND = "Resident not found";

    @Transactional(readOnly = true)
    public List<Resident> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Resident> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Resident addResident(ResidentDto dto) {

        Resident resident = dto.toEntity();

        if (repository.existsByCpf(resident.getCpf()))
            throw new AlreadyExistsException(String.format("Resident: cpf=%s already exists", resident.getCpf()));

        User user = userService.findByUsername(dto.username());
        resident.setUser(user);
        return repository.save(resident);
    }

    @Transactional
    public Resident deleteResident(UUID id) {
        Resident resident = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        repository.delete(resident);
        return resident;
    }

    @Transactional
    public Resident updateResident(UUID id, ResidentDto updatedResidentDto) {

        Resident existingResident = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Resident updatedResident = updatedResidentDto.returnEntityUpdated(existingResident);

        return repository.save(updatedResident);
    }

}
