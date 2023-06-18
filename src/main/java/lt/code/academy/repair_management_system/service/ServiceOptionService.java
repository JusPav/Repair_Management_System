package lt.code.academy.repair_management_system.service;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lt.code.academy.repair_management_system.entity.ServiceOption;
import lt.code.academy.repair_management_system.exception.NotFoundException;
import lt.code.academy.repair_management_system.repository.ServiceOptionRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ServiceOptionService {

    private final ServiceOptionRepository serviceOptionRepository;

    public List<ServiceOption> findServiceOptions() {
        return serviceOptionRepository.findAll();
    }
    public ServiceOption findServiceOptionById(UUID id) {
        return serviceOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("ServiceOption not found with ID %d", id)));
    }
    public void createServiceOption(ServiceOption serviceOption) {
        serviceOptionRepository.save(serviceOption);
    }
    public void updateServiceOption(ServiceOption serviceOption) {
        serviceOptionRepository.save(serviceOption);
    }
    public void deleteServiceOption(UUID id) {
        final ServiceOption serviceOption = serviceOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("ServiceOption not found with ID %d", id)));

        serviceOptionRepository.deleteById(serviceOption.getId());
    }
}
