package lt.code.academy.repair_management_system.service;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lt.code.academy.repair_management_system.entity.Manufacturer;
import lt.code.academy.repair_management_system.exception.NotFoundException;
import lt.code.academy.repair_management_system.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;


    public List<Manufacturer> findAllManufacturers() {
        return manufacturerRepository.findAll();
    }
    public Manufacturer findManufacturerById(UUID id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Manufacturer not found  with ID %d", id)));
    }
    public void createManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }
    public void updateManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }
    public void deleteManufacturer(UUID id) {
        final Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Manufacturer not found  with ID %d", id)));

        manufacturerRepository.deleteById(manufacturer.getId());
    }
}
