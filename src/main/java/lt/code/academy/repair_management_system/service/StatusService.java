package lt.code.academy.repair_management_system.service;

import lombok.AllArgsConstructor;
import lt.code.academy.repair_management_system.entity.Status;
import lt.code.academy.repair_management_system.exception.NotFoundException;
import lt.code.academy.repair_management_system.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class StatusService {

    private final StatusRepository statusRepository;


    public List<Status> findAllStatuses() {
        return statusRepository.findAll();
    }
    public Status findStatusById(UUID id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Status not found  with ID %d", id)));
    }
    public void createStatus(Status status) {
        statusRepository.save(status);
    }
    public void updateStatus(Status status) {
        statusRepository.save(status);
    }
    public void deleteStatus(UUID id) {
        final Status status = statusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Status not found  with ID %d", id)));
        statusRepository.deleteById(status.getId());
    }
}
