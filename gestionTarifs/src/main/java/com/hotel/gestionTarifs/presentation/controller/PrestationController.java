package com.hotel.gestionTarifs.presentation.controller;

import com.hotel.gestionTarifs.business.dto.PrestationDTO;
import com.hotel.gestionTarifs.business.service.IPrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prestations")
public class PrestationController {

    @Autowired
    private IPrestationService prestationService;

    @GetMapping
    public String listPrestations(Model model) {
        List<PrestationDTO> prestations = prestationService.getAll();
        model.addAttribute("prestations", prestations);
        return "prestations/liste";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        PrestationDTO prestation = prestationService.getById(id);
        model.addAttribute("prestation", prestation);
        return "prestations/detail";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("prestation", new PrestationDTO());
        return "prestations/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("prestation") PrestationDTO dto) {
        prestationService.create(dto);
        return "redirect:/prestations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        PrestationDTO prestation = prestationService.getById(id);
        model.addAttribute("prestation", prestation);
        return "prestations/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute("prestation") PrestationDTO dto) {
        prestationService.update(id, dto);
        return "redirect:/prestations";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        prestationService.delete(id);
        return "redirect:/prestations";
    }
}
