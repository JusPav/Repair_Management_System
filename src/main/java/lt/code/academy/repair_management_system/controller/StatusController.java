package lt.code.academy.repair_management_system.controller;

import lt.code.academy.repair_management_system.entity.Status;
import lt.code.academy.repair_management_system.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Controller
public class StatusController {

    private final StatusService statusService;
    @RequestMapping("/statuses")
    public String findAllStatuses(Model model) {
        final List<Status> statuses = statusService.findAllStatuses();

        model.addAttribute("statuses", statuses);
        return "status/showStatuses";
    }

    @RequestMapping("/status/{id}")
    public String findStatusById(@PathVariable UUID id, Model model) {
        final Status status = statusService.findStatusById(id);

        model.addAttribute("status", status);
        return "status/showStatus";
    }

    @GetMapping("/addStatus")
    public String showCreateForm(Status Status) {
        return "status/addStatus";
    }

    @RequestMapping("/addStatus")
    public String createStatus(Status status, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "status/addStatus";
        }

        statusService.createStatus(status);
        model.addAttribute("status", statusService.findAllStatuses());
        return "redirect:/statuses";
    }

    @GetMapping("/updateStatus/{id}")
    public String showUpdateForm(@PathVariable UUID id, Model model) {
        final Status status = statusService.findStatusById(id);

        model.addAttribute("status", status);
        return "status/updateStatus";
    }

    @RequestMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable UUID id, Status status, BindingResult result, Model model) {
        if (result.hasErrors()) {
            status.setId(id);
            return "status/updateStatus";
        }

        statusService.updateStatus(status);
        model.addAttribute("status", statusService.findAllStatuses());
        return "redirect:/statuses";
    }

    @RequestMapping("/removeStatus/{id}")
    public String deleteStatus(@PathVariable UUID id, Model model) {
        statusService.deleteStatus(id);

        model.addAttribute("status", statusService.findAllStatuses());
        return "redirect:/statuses";
    }
}
