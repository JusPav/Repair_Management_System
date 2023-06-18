package lt.code.academy.repair_management_system.controller;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lt.code.academy.repair_management_system.entity.DeviceType;
import lt.code.academy.repair_management_system.service.DeviceTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class DeviceTypeController {

    private final DeviceTypeService deviceTypeService;

    @RequestMapping("/deviceTypes")
    public String findAllDeviceTypes(Model model) {
        final List<DeviceType> deviceTypes = deviceTypeService.findAllDeviceTypes();

        model.addAttribute("deviceTypes", deviceTypes);
        return "deviceType/showDeviceTypes";
    }

    @RequestMapping("/category/{id}")
    public String findDeviceTypeById(@PathVariable UUID id, Model model) {
        final DeviceType deviceType = deviceTypeService.findDeviceTypeById(id);

        model.addAttribute("deviceType", deviceType);
        return "deviceType/showDeviceType";
    }

    @GetMapping("/addDeviceType")
    public String showCreateForm(DeviceType deviceType) {
        return "deviceType/addDeviceType";
    }

    @RequestMapping("/addDeviceType")
    public String createDeviceType(DeviceType deviceType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "deviceType/addDeviceType";
        }

        deviceTypeService.createDeviceType(deviceType);
        model.addAttribute("deviceType", deviceTypeService.findAllDeviceTypes());
        return "redirect:/deviceTypes";
    }

    @GetMapping("/updateDeviceType/{id}")
    public String showUpdateForm(@PathVariable UUID id, Model model) {
        final DeviceType deviceType = deviceTypeService.findDeviceTypeById(id);

        model.addAttribute("deviceType", deviceType);
        return "deviceType/updateDeviceType";
    }

    @RequestMapping("/updateDeviceType/{id}")
    public String updateDeviceType(@PathVariable UUID id, DeviceType deviceType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            deviceType.setId(id);
            return "deviceType/updateDeviceType";
        }

        deviceTypeService.updateDeviceType(deviceType);
        model.addAttribute("deviceType", deviceTypeService.findAllDeviceTypes());
        return "redirect:/deviceTypes";
    }

    @RequestMapping("/removeDeviceType/{id}")
    public String deleteDeviceType(@PathVariable UUID id, Model model) {
        deviceTypeService.deleteDeviceType(id);

        model.addAttribute("deviceType", deviceTypeService.findAllDeviceTypes());
        return "redirect:/deviceTypes";
    }
}
