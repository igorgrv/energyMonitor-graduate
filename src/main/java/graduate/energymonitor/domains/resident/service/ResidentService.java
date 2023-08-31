
package graduate.energymonitor.domains.resident.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graduate.energymonitor.domains.resident.controller.dto.ResidentUserRequest;
import graduate.energymonitor.domains.resident.controller.dto.ResidentUserResponse;
import graduate.energymonitor.domains.resident.controller.dto.ResidentUserLocationResponse;
import graduate.energymonitor.domains.resident.entity.Resident;
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
    private static final String RESIDENT_NOT_FOUND = "Resident not found";

    @Transactional(readOnly = true)
    public List<ResidentUserLocationResponse> findAll() {
        List<Resident> residents = repository.findAll();
        return residents.stream().map(ResidentUserLocationResponse::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResidentUserLocationResponse findByIdResponse(Long id) {
        Resident resident = repository.findById(id).orElseThrow(() -> new NotFoundException(RESIDENT_NOT_FOUND));
        return ResidentUserLocationResponse.fromEntity(resident);
    }

    @Transactional(readOnly = true)
    public Resident findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(RESIDENT_NOT_FOUND));
    }

    @Transactional
    public ResidentUserResponse addResident(ResidentUserRequest dto) {

        Resident resident = dto.toEntity();
        String cpf = resident.getCpf();
        if (repository.existsByCpf(cpf))
            throw new AlreadyExistsException(String.format("Resident: cpf=%s already exists", cpf));

        User user = userService.findByUsername(dto.username());
        resident.setUser(user);
        Resident residentCreated = repository.save(resident);
        return ResidentUserResponse.fromEntity(residentCreated);
    }

    @Transactional
    public ResidentUserLocationResponse deleteResident(Long id) {
        Resident resident = repository.findById(id).orElseThrow(() -> new NotFoundException(RESIDENT_NOT_FOUND));
        repository.delete(resident);
        return ResidentUserLocationResponse.fromEntity(resident);
    }

    @Transactional
    public ResidentUserLocationResponse updateResident(Long id, ResidentUserRequest updatedResidentDto) {

        Resident existingResident = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESIDENT_NOT_FOUND));
        Resident updatedResident = updatedResidentDto.returnEntityUpdated(existingResident);

        Resident residentUpdated = repository.save(updatedResident);
        return ResidentUserLocationResponse.fromEntity(residentUpdated);
    }

}
