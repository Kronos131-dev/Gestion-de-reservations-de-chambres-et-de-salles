package com.ulco.dashboard.presentation;

import com.ulco.dashboard.business.GraphBusiness;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GraphController {

    private final GraphBusiness graphBusiness;

    public GraphController(GraphBusiness graphBusiness) {
        this.graphBusiness = graphBusiness;
    }

    @GetMapping("/api/getChamberStatus")
    @ResponseBody
    public String getChamberStatus() {
        return graphBusiness.getStatusAllRoom();
    }

}
