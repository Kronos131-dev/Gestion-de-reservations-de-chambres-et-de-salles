package com.hotel.gestionTarifs.presentation.controller;

import com.hotel.gestionTarifs.business.dto.ServiceExtraDTO;
import com.hotel.gestionTarifs.business.service.ServiceExtraService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/service-extras")
public class ServiceExtraController {

    private final ServiceExtraService serviceExtraService;

    public ServiceExtraController(ServiceExtraService serviceExtraService) {
        this.serviceExtraService = serviceExtraService;
    }

    @GetMapping
    public String list(Model model) {
        List<ServiceExtraDTO> list = serviceExtraService.getAllServiceExtras();
        model.addAttribute("serviceExtras", list);
        return "serviceExtra/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("serviceExtra")) {
            model.addAttribute("serviceExtra", new ServiceExtraDTO());
        }
        model.addAttribute("title", "Créer un service extra");
        model.addAttribute("action", "/service-extras/save");
        return "serviceExtra/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("serviceExtra") ServiceExtraDTO dto,
                       BindingResult result,
                       RedirectAttributes ra,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Créer un service extra");
            model.addAttribute("action", "/service-extras/save");
            return "serviceExtra/form";
        }

        try {
            serviceExtraService.createServiceExtra(dto);
            ra.addFlashAttribute("success", "Service extra créé avec succès !");
            return "redirect:/service-extras";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Erreur lors de la création.");
            return "redirect:/service-extras/new";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            ServiceExtraDTO dto = serviceExtraService.getServiceExtraById(id);
            model.addAttribute("serviceExtra", dto);
            model.addAttribute("title", "Modifier le service extra");
            model.addAttribute("action", "/service-extras/" + id + "/update");
            return "serviceExtra/form";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Service extra introuvable.");
            return "redirect:/service-extras";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id,
                         @Valid @ModelAttribute("serviceExtra") ServiceExtraDTO dto,
                         BindingResult result,
                         RedirectAttributes ra,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Modifier le service extra");
            model.addAttribute("action", "/service-extras/" + id + "/update");
            return "serviceExtra/form";
        }

        try {
            serviceExtraService.updateServiceExtra(id, dto);
            ra.addFlashAttribute("success", "Service extra mis à jour !");
            return "redirect:/service-extras";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Erreur lors de la mise à jour.");
            return "redirect:/service-extras";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            serviceExtraService.deleteServiceExtra(id);
            ra.addFlashAttribute("success", "Service extra supprimé !");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Erreur lors de la suppression.");
        }
        return "redirect:/service-extras";
    }
}
