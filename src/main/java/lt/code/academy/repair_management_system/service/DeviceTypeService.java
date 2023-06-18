package lt.code.academy.repair_management_system.service;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lt.code.academy.repair_management_system.entity.DeviceType;
import lt.code.academy.repair_management_system.exception.NotFoundException;
import lt.code.academy.repair_management_system.repository.DeviceTypeRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeviceTypeService {

    private final DeviceTypeRepository deviceTypeRepository;

    public List<DeviceType> findAllDeviceTypes() {
        return deviceTypeRepository.findAll();
    }
    public DeviceType findDeviceTypeById(UUID id) {
        return deviceTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("DeviceType not found  with ID %d", id)));
    }
    public void createDeviceType(DeviceType deviceType) {
        deviceTypeRepository.save(deviceType);
    }
    public void updateDeviceType(DeviceType deviceType) {
        deviceTypeRepository.save(deviceType);
    }
    public void deleteDeviceType(UUID id) {
        final DeviceType deviceType = deviceTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("DeviceType not found  with ID %d", id)));

        deviceTypeRepository.deleteById(deviceType.getId());
    }
}
