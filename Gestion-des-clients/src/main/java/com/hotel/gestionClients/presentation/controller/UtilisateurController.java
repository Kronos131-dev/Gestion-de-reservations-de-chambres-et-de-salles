package com.hotel.gestionClients.presentation.controller;

import com.hotel.gestionClients.business.dto.UtilisateurDTO;
import com.hotel.gestionClients.business.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;


    @GetMapping
    public String listerClients(@RequestParam(name = "nom", required = false) String nom,
                                @RequestParam(name = "prenom", required = false) String prenom,
                                @RequestParam(name = "ville", required = false) String ville,
                                Model model) {

        List<UtilisateurDTO> clients = utilisateurService.getAllClients();

        if (nom != null && !nom.isBlank()) {
            String n = nom.toLowerCase();
            clients = clients.stream()
                    .filter(c -> c.getNom() != null && c.getNom().toLowerCase().contains(n))
                    .toList();
        }

        if (prenom != null && !prenom.isBlank()) {
            String p = prenom.toLowerCase();
            clients = clients.stream()
                    .filter(c -> c.getPrenom() != null && c.getPrenom().toLowerCase().contains(p))
                    .toList();
        }

        if (ville != null && !ville.isBlank()) {
            String v = ville.toLowerCase();
            clients = clients.stream()
                    .filter(c -> c.getAdresse() != null &&
                            c.getAdresse().getVille() != null &&
                            c.getAdresse().getVille().toLowerCase().contains(v))
                    .toList();
        }

        model.addAttribute("clients", clients);
        model.addAttribute("nom", nom);
        model.addAttribute("prenom", prenom);
        model.addAttribute("ville", ville);

        return "clients/liste";
    }

    @GetMapping("/nouveau")
    public String afficherFormulaireCreation(Model model) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        model.addAttribute("utilisateur", utilisateurDTO);
        return "clients/form";
    }

    @PostMapping("/nouveau")
    public String creerClient(@ModelAttribute("utilisateur") UtilisateurDTO utilisateurDTO,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            return "clients/form";
        }

        utilisateurService.createClient(utilisateurDTO);
        return "redirect:/clients";
    }

    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModification(@PathVariable("id") Long id, Model model) {
        UtilisateurDTO utilisateurDTO = utilisateurService.getClientById(id);
        if (utilisateurDTO == null) {
            return "redirect:/clients";
        }

        model.addAttribute("utilisateur", utilisateurDTO);
        return "clients/form";
    }


    @PostMapping("/modifier/{id}")
    public String modifierClient(@PathVariable("id") Long id,
                                 @ModelAttribute("utilisateur") UtilisateurDTO utilisateurDTO,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            return "clients/form";
        }

        utilisateurService.updateClient(id, utilisateurDTO);
        return "redirect:/clients";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerClient(@PathVariable("id") Long id) {
        utilisateurService.deleteClient(id);
        return "redirect:/clients";
    }
}
