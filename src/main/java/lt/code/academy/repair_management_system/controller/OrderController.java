package lt.code.academy.repair_management_system.controller;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lt.code.academy.repair_management_system.entity.Order;
import lt.code.academy.repair_management_system.service.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final ServiceOptionService serviceOptionService;
    private final DeviceTypeService deviceTypeService;
    private final ManufacturerService manufacturerService;
    private final StatusService statusService;
    @RequestMapping("/orders")
    public String findAllOrders(Model model) {
        final List<Order> orders = orderService.findAllOrders();

        model.addAttribute("orders", orders);
        return "/order/showOrders";
    }

    @RequestMapping("/searchOrder")
    public String searchOrder(@Param("keyword") String keyword, Model model) {
        final List<Order> orders = orderService.searchOrders(keyword);

        model.addAttribute("orders", orders);
        model.addAttribute("keyword", keyword);
        return "/order/showOrders";
    }

    @RequestMapping("/order/{id}")
    public String findOrderById(@PathVariable UUID id, Model model) {
        final Order order = orderService.findOrderById(id);

        model.addAttribute("order", order);
        return "/order/showOrder";
    }

    @GetMapping("/add")
    public String showCreateForm(Order order, Model model) {
        model.addAttribute("deviceTypes", deviceTypeService.findAllDeviceTypes());
        model.addAttribute("serviceOptions", serviceOptionService.findServiceOptions());
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        model.addAttribute("statuses", statusService.findAllStatuses());
        return "/order/addOrder";
    }

    @RequestMapping("/addOrder")
    public String createOrder(Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/order/addOrder";
        }

        orderService.createOrder(order);
        model.addAttribute("order", orderService.findAllOrders());
        return "redirect:/orders";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable UUID id, Model model) {
        final Order order = orderService.findOrderById(id);

        model.addAttribute("deviceTypes", deviceTypeService.findAllDeviceTypes());
        model.addAttribute("serviceOptions", serviceOptionService.findServiceOptions());
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        model.addAttribute("statuses", statusService.findAllStatuses());

        model.addAttribute("order", order);
        return "/order/updateOrder";
    }

    @RequestMapping("/updateOrder/{id}")
    public String updateOrder(@PathVariable UUID id, Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            order.setId(id);
            return "/order/updateOrder";
        }

        orderService.updateOrder(order);
        model.addAttribute("order", orderService.findAllOrders());
        return "redirect:/order/{id}";
    }

    @RequestMapping("/removeOrder/{id}")
    public String deleteOrder(@PathVariable UUID id, Model model) {
        orderService.deleteOrder(id);

        model.addAttribute("order", orderService.findAllOrders());
        return "redirect:/orders";
    }
}

