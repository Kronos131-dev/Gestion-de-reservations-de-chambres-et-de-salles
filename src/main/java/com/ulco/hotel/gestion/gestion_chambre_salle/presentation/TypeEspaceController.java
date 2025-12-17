package com.ulco.hotel.gestion.gestion_chambre_salle.presentation;

import com.ulco.hotel.gestion.gestion_chambre_salle.business.TypeEspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/types")
public class TypeEspaceController {

    @Autowired
    private TypeEspaceService typeEspaceService;


    @GetMapping
    public String getAllTypeEspacesPage(Model model) {
        List<TypeEspace> types = typeEspaceService.findAll();
        model.addAttribute("types", types);
        return "types";
    }

    @GetMapping("/{id}/espaces")
    @ResponseBody
    public ResponseEntity<List<Espace>> getEspacesAssocies(@PathVariable Long id) {
        try {
            List<Espace> espaces = typeEspaceService.getEspacesAssocies(id);
            return ResponseEntity.ok(espaces);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/new")
    public String createTypeForm(Model model) {
        model.addAttribute("type", new TypeEspace());
        List<TypeEspace> allTypes = typeEspaceService.findAll();
        model.addAttribute("allTypes", allTypes);
        return "types/form";
    }

    @PostMapping("/create")
    public String createType(@ModelAttribute TypeEspace type, Model model) {
        try {
            typeEspaceService.save(type);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "types/form";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteType(@PathVariable Long id) {
        try {
            typeEspaceService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}