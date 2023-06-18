package lt.code.academy.repair_management_system.repository;

import java.util.List;
import java.util.UUID;

import lt.code.academy.repair_management_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT b FROM Order b WHERE b.orderNumber = ?1" +
            " OR b.serialNumber = ?1" +
            " OR b.surname = ?1")
    public List<Order> search(String keyword);
}
