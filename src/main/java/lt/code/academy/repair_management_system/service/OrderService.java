package lt.code.academy.repair_management_system.service;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lt.code.academy.repair_management_system.entity.Order;
import lt.code.academy.repair_management_system.exception.NotFoundException;
import lt.code.academy.repair_management_system.repository.OrderRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
    public List<Order> searchOrders(String keyword) {
        if (keyword != null) {
            return orderRepository.search(keyword);
        }
        return orderRepository.findAll();
    }
    public Order findOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order not found with ID %d", id)));
    }
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
    public void deleteOrder(UUID id) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order not found with ID %d", id)));

        orderRepository.deleteById(order.getId());
    }
}
