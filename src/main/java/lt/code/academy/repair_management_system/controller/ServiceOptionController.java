package lt.code.academy.repair_management_system.controller;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lt.code.academy.repair_management_system.entity.ServiceOption;
import lt.code.academy.repair_management_system.service.ServiceOptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class ServiceOptionController {

    private final ServiceOptionService serviceOptionService;
    @RequestMapping("/serviceOptions")
    public String findAllServiceOptions(Model model) {
        final List<ServiceOption> serviceOptions = serviceOptionService.findServiceOptions();

        model.addAttribute("serviceOptions", serviceOptions);
        return "serviceOption/showServiceOptions";
    }

    @RequestMapping("/serviceOption/{id}")
    public String findServiceOptionById(@PathVariable UUID id, Model model) {
        final ServiceOption serviceOption = serviceOptionService.findServiceOptionById(id);

        model.addAttribute("serviceOption", serviceOption);
        return "serviceOption/showServiceOption";
    }

    @GetMapping("/addServiceOption")
    public String showCreateForm(ServiceOption serviceOption) {
        return "serviceOption/addServiceOption";
    }

    @RequestMapping("/addServiceOption")
    public String createServiceOption(ServiceOption serviceOption, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "serviceOption/addServiceOption";
        }

        serviceOptionService.createServiceOption(serviceOption);
        model.addAttribute("serviceOption", serviceOptionService.findServiceOptions());
        return "redirect:/serviceOptions";
    }

    @GetMapping("/updateServiceOption/{id}")
    public String showUpdateForm(@PathVariable UUID id, Model model) {
        final ServiceOption serviceOption = serviceOptionService.findServiceOptionById(id);

        model.addAttribute("serviceOption", serviceOption);
        return "serviceOption/updateServiceOption";
    }

    @RequestMapping("/updateServiceOption/{id}")
    public String updateServiceOption(@PathVariable UUID id, ServiceOption serviceOption, BindingResult result, Model model) {
        if (result.hasErrors()) {
            serviceOption.setId(id);
            return "serviceOption/updateServiceOption";
        }

        serviceOptionService.updateServiceOption(serviceOption);
        model.addAttribute("serviceOption", serviceOptionService.findServiceOptions());
        return "redirect:/serviceOptions";
    }

    @RequestMapping("/removeServiceOption/{id}")
    public String deleteServiceOption(@PathVariable UUID id, Model model) {
        serviceOptionService.deleteServiceOption(id);

        model.addAttribute("serviceOption", serviceOptionService.findServiceOptions());
        return "redirect:/serviceOptions";
    }
}
