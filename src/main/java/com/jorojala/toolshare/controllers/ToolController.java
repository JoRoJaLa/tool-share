package com.jorojala.toolshare.controllers;

import com.jorojala.toolshare.location_api.ZipToLatLon;
import com.jorojala.toolshare.models.AppUser;
import com.jorojala.toolshare.models.Tool;
import com.jorojala.toolshare.repositories.AppUserRepository;
import com.jorojala.toolshare.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ToolController {


    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ToolRepository toolRepository;


    @GetMapping("/tool-listings")
    public String getToolListings(Principal p, Model m) {
        String username =  p.getName();
        AppUser currentUser = (AppUser) appUserRepository.findByUsername(username);
        m.addAttribute("username", currentUser.getUsername());
        List<Tool> originalListOfTools = toolRepository.findAll();

        List<Tool> listOfTools = originalListOfTools.stream()
                .filter(Tool::getAvailable)
                .collect(Collectors.toList());

        m.addAttribute("listOfTools", listOfTools);
        return ("tool-listings-page.html");
    }

    @GetMapping("/filter-tools")
    public String getToolListings(Model m, String tools) {
        if (tools.equals("all")){
            List<Tool> listOfTools = toolRepository.findAll();
            m.addAttribute("listOfTools", listOfTools);
            return ("tool-listings-page.html");
        } else {
            List<Tool> originalListOfTools = toolRepository.findAll();

            List<Tool> listOfTools = originalListOfTools.stream()
                    .filter(tool -> tool.getName().equals(tools))
                    .filter(Tool::getAvailable)
                    .collect(Collectors.toList());
            m.addAttribute("listOfTools", listOfTools);
            return ("tool-listings-page.html");
        }
    }



    @GetMapping("/filter-state")
    public String getToolListingsByState(Model m, String state){
        List<Tool> originalListOfTools = toolRepository.findAll();

        List<Tool> listOfTools = originalListOfTools.stream()
                .filter(tool -> tool.getToolListedByUser().getResults().getState().equals(state))
                .filter(Tool::getAvailable)
                .collect(Collectors.toList());

        m.addAttribute("listOfTools", listOfTools);
        return ("tool-listings-page.html");
    }


    @GetMapping("/filter-city")
    public String getToolListingsByCity(Model m, String city){
        List<Tool> originalListOfTools = toolRepository.findAll();


        List<Tool> listOfTools = originalListOfTools.stream()
                .filter(tool -> tool.getToolListedByUser().getResults().getCity().equals(city))
                .filter(Tool::getAvailable)
                .collect(Collectors.toList());

        m.addAttribute("listOfTools", listOfTools);
        return ("tool-listings-page.html");
    }


    @GetMapping("/borrow-tool-get-distance")
    public String getDistanceFromUser(Principal p, Model m, long toolId) {
        String username = null;
        if (p != null) {
            username = p.getName();
        }

        AppUser currentUser = (AppUser) appUserRepository.findByUsername(username);
        double currentUserLat = currentUser.getResults().getLat();
        double currentUserLon = currentUser.getResults().getLon();

        Tool toolOfOwner = toolRepository.getById(toolId);
        double userToolOfOwnerLat = toolOfOwner.getToolListedByUser().getResults().getLat();
        double userToolOfOwnerLon = toolOfOwner.getToolListedByUser().getResults().getLon();

        double distanceBetweenUsers = ZipToLatLon.latLongDist(currentUserLat,currentUserLon,userToolOfOwnerLat,userToolOfOwnerLon);
        m.addAttribute("distanceBetweenUsers", distanceBetweenUsers);

        return ("tool-listings-page.html");
    }





    @GetMapping("/filter-distance-miles")
    public String getFilterDistanceMiles(Principal p, Model m) {
        String username = null;
        if (p != null) {
            username = p.getName();
        }

        AppUser currentUser = (AppUser) appUserRepository.findByUsername(username);
        double currentUserLat = currentUser.getResults().getLat();
        double currentUserLon = currentUser.getResults().getLon();

        List<Tool> originalListOfTools = toolRepository.findAll();


        for (Tool tool : originalListOfTools) {
            Tool toolOfOwner = toolRepository.getById(tool.getId());
            double userToolOfOwnerLat = toolOfOwner.getToolListedByUser().getResults().getLat();
            double userToolOfOwnerLon = toolOfOwner.getToolListedByUser().getResults().getLon();

            double distanceBetweenUsers = ZipToLatLon.latLongDist(currentUserLat,currentUserLon,userToolOfOwnerLat,userToolOfOwnerLon);
            tool.setDistanceFromUser(distanceBetweenUsers);
        }

        Comparator<Tool> toolComparator = Comparator.comparing(Tool::getDistanceFromUser);

        List<Tool> listOfTools = originalListOfTools.stream()
                .sorted(toolComparator)
                .filter(Tool::getAvailable)
                .collect(Collectors.toList());


        m.addAttribute("listOfTools", listOfTools);

        return ("tool-listings-page.html");
    }


}
