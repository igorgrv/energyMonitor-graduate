
package graduate.energymonitor.domains.resident.service;

import java.util.List;
import java.util.stream.Collectors;

import graduate.energymonitor.domains.resident.controller.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private static final String RESIDENT_NOT_FOUND = "Resident not found - ID: ";

    @Transactional(readOnly = true)
    public List<ResidentResponse> findAll() {
        List<Resident> residents = repository.findAll();
        return residents.stream().map(ResidentResponse::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResidentUserLocationApplianceResponse findByIdResponse(Long id) {
        Resident resident = repository.findById(id).orElseThrow(() -> new NotFoundException(RESIDENT_NOT_FOUND + id));
        return ResidentUserLocationApplianceResponse.fromEntity(resident);
    }

    @Transactional(readOnly = true)
    public Resident findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(RESIDENT_NOT_FOUND + id));
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
    public ResidentResponse deleteResident(Long id) {
        Resident resident = repository.findById(id).orElseThrow(() -> new NotFoundException(RESIDENT_NOT_FOUND + id));
        repository.delete(resident);
        return ResidentResponse.fromEntity(resident);
    }

    @Transactional
    public ResidentResponse updateResident(Long id, ResidentUserRequest updatedResidentDto) {

        Resident existingResident = repository.findById(id).orElseThrow(() -> new NotFoundException(RESIDENT_NOT_FOUND + id));
        Resident updatedResident = updatedResidentDto.returnEntityUpdated(existingResident);
        Resident residentUpdated = repository.save(updatedResident);
        return ResidentResponse.fromEntity(residentUpdated);
    }

}
