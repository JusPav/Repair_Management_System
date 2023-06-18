package lt.code.academy.repair_management_system.repository;

import lt.code.academy.repair_management_system.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
}
