package lt.code.academy.repair_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceOptionRepository extends JpaRepository<ServiceOption, UUID> {
}
