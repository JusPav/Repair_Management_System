package lt.code.academy.repair_management_system.controller;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lt.code.academy.repair_management_system.entity.Manufacturer;
import lt.code.academy.repair_management_system.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @RequestMapping("/manufacturers")
    public String findAllManufacturers(Model model) {
        final List<Manufacturer> manufacturers = manufacturerService.findAllManufacturers();

        model.addAttribute("manufacturers", manufacturers);
        return "manufacturer/showManufacturers";
    }

    @RequestMapping("/manufacturer/{id}")
    public String findManufacturerById(@PathVariable UUID id, Model model) {
        final Manufacturer manufacturer = manufacturerService.findManufacturerById(id);

        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer/showManufacturer";
    }

    @GetMapping("/addManufacturer")
    public String showCreateForm(Manufacturer manufacturer) {
        return "manufacturer/addManufacturer";
    }

    @RequestMapping("/addManufacturer")
    public String createManufacturer(Manufacturer manufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "manufacturer/addManufacturer";
        }

        manufacturerService.createManufacturer(manufacturer);
        model.addAttribute("manufacturer", manufacturerService.findAllManufacturers());
        return "redirect:/manufacturers";
    }

    @GetMapping("/updateManufacturer/{id}")
    public String showUpdateForm(@PathVariable UUID id, Model model) {
        final Manufacturer manufacturer = manufacturerService.findManufacturerById(id);

        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer/updateManufacturer";
    }

    @RequestMapping("/updateManufacturer/{id}")
    public String updateManufacturer(@PathVariable UUID id, Manufacturer manufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            manufacturer.setId(id);
            return "manufacturer/updateManufacturer";
        }

        manufacturerService.updateManufacturer(manufacturer);
        model.addAttribute("manufacturer", manufacturerService.findAllManufacturers());
        return "redirect:/manufacturers";
    }

    @RequestMapping("/removeManufacturer/{id}")
    public String deleteManufacturer(@PathVariable UUID id, Model model) {
        manufacturerService.deleteManufacturer(id);

        model.addAttribute("manufacturer", manufacturerService.findAllManufacturers());
        return "redirect:/manufacturers";
    }
}
