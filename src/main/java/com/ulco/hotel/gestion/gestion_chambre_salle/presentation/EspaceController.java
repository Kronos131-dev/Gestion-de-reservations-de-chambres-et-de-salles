package com.ulco.hotel.gestion.gestion_chambre_salle.presentation;

import com.ulco.hotel.gestion.gestion_chambre_salle.business.EspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.business.TypeEspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/espaces")
public class EspaceController {

    @Autowired
    private EspaceService espaceService;

    @Autowired
    private TypeEspaceService typeEspaceService;

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/";
    }


    @GetMapping
    public String getAllEspaces(Model model) {
        List<Espace> espaces = espaceService.findAll();
        model.addAttribute("espaces", espaces);
        model.addAttribute("espace", new Espace());
        return "espaces";
    }


    @PostMapping
    public String createEspace(@ModelAttribute("espace") Espace espace, @RequestParam("typeId") Long typeId) {
        try {
            TypeEspace type = typeEspaceService.findById(typeId);
            if (type == null) {
                return "redirect:/espaces/new";
            }
            espace.setTypeEspace(type);
            espaceService.save(espace);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/espaces/new";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteEspace(@PathVariable Long id) {
        try {
            espaceService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @GetMapping("/new")
    public String createEspaceForm(Model model) {
        model.addAttribute("espace", new Espace());
        List<TypeEspace> types = typeEspaceService.findAll();
        model.addAttribute("types", types);
        return "espaces/form";
    }




    @GetMapping("/edit/{id}")
    public String editEspaceForm(@PathVariable Long id, Model model) {
        Espace espace = espaceService.findById(id);
        if (espace == null) {
            return "redirect:/";
        }
        List<TypeEspace> types = typeEspaceService.findAll();
        model.addAttribute("espace", espace);
        model.addAttribute("types", types);
        return "espaces/edit-form";
    }


    @PostMapping("/{id}")
    public String updateEspace(@PathVariable Long id,
                               @ModelAttribute("espace") Espace espace,
                               @RequestParam("typeId") Long typeId) {
        try {
            Espace existingEspace = espaceService.findById(id);
            if (existingEspace == null) {
                return "redirect:/";
            }

            TypeEspace type = typeEspaceService.findById(typeId);
            if (type == null) {
                return "redirect:/espaces/edit/" + id + "?error=Type+d'espace+introuvable";
            }

            existingEspace.setDescription(espace.getDescription());
            existingEspace.setNb_place(espace.getNb_place());
            existingEspace.setPrix_base(espace.getPrix_base());
            existingEspace.setStatus(espace.getStatus());
            existingEspace.setTypeEspace(type);

            espaceService.save(existingEspace);
            return "redirect:/espaces/edit/" + id + "?success=true";
        } catch (Exception e) {
            return "redirect:/espaces/edit/" + id + "?error=" + e.getMessage();
        }
    }
}