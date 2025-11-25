package com.ulco.dashboard.presentation;

import com.ulco.dashboard.business.GraphBusiness;
import com.ulco.dashboard.persistence.Espace;
import com.ulco.dashboard.persistence.TypeEspace;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class GraphController {

    private final GraphBusiness graphBusiness;

    public GraphController(GraphBusiness graphBusiness) {
        this.graphBusiness = graphBusiness;
    }

    @GetMapping("/api/getChamberStatus")
    @ResponseBody
    public Map<String, List<Espace.Status>> getChamberStatus() {
        List<Espace.Status> res = graphBusiness.getStatusAllRoom();
        return Map.of("value",res);
    }

    @GetMapping("/api/getChamberPrices")
    @ResponseBody
    public Map<String,List<Float>>  getChamberPrices() {
        List<Float> res = graphBusiness.getAllEspacesPrices();
        return Map.of("value",res);
    }

    @GetMapping("/api/getChamberPlaces")
    @ResponseBody
    public Map<String,List<Float>> getChamberPlaces(){
        List<Float> res = graphBusiness.getAllEspacesPlaces();
        return  Map.of("value",res);
    }

    @GetMapping("/api/getChamberTypes")
    @ResponseBody
    public Map<String,List<Object[]>> getChamberTypes(){
        List<Object[]> res = graphBusiness.getAllTypeEspacesAndCount();
        return Map.of("value",res);
    }

}
