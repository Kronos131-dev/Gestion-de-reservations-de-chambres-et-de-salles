package com.hotel.gestionTarifs.presentation.controller;

import com.hotel.gestionTarifs.business.dto.PrestationDTO;
import com.hotel.gestionTarifs.business.service.IPrestationService;
import com.hotel.gestionTarifs.business.service.ServiceExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/prestations")
public class PrestationController {
    @Autowired
    private IPrestationService prestationService;
    @Autowired
    private ServiceExtraService serviceExtraService;

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
        model.addAttribute("servicesAvailable", serviceExtraService.getAllServiceExtras());
        return "prestations/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("prestation") PrestationDTO dto, @RequestParam(required = false) Integer idServiceSelected,RedirectAttributes ra) {
        try {
            if (idServiceSelected != null) {
                prestationService.createPrestationWithService(dto, idServiceSelected);
                ra.addFlashAttribute("success", "Service facturé avec succès !");
            } else {
                prestationService.create(dto);
                ra.addFlashAttribute("success", "Prestation créée avec succès !");}
            return "redirect:/prestations";}
        catch (Exception e) {
            ra.addFlashAttribute("error", "Erreur : " + e.getMessage());
            return "redirect:/prestations/create";}
    }

    @GetMapping("/sejour/create")
    public String showSejourForm() {
        return "prestations/formSejour";
    }

    @PostMapping("/sejour/create")
    public String createSejour(@RequestParam Integer idReservation, RedirectAttributes ra) {
        try {
            prestationService.createPrestationSejour(idReservation);
            ra.addFlashAttribute("success", "Séjour facturé avec succès !");}
        catch (Exception e) {
            ra.addFlashAttribute("error", "Erreur lors de la facturation du séjour : " + e.getMessage());}
        return "redirect:/prestations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        PrestationDTO prestation = prestationService.getById(id);
        model.addAttribute("prestation",prestation);
        model.addAttribute("servicesAvailable",serviceExtraService.getAllServiceExtras());
        return "prestations/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute("prestation") PrestationDTO dto) {
        prestationService.update(id, dto);
        return "redirect:/prestations";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            prestationService.delete(id);
            ra.addFlashAttribute("success", "Prestation supprimée.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Impossible de supprimer (peut-être liée à un paiement ?).");
        }
        return "redirect:/prestations";
    }
}