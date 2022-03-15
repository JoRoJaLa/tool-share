package com.jorojala.toolshare.controllers;

import com.jorojala.toolshare.models.Tool;
import com.jorojala.toolshare.repositories.AppUserRepository;
import com.jorojala.toolshare.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ToolController {


    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ToolRepository toolRepository;

    @GetMapping("/filter-tools")
    public String getToolListings(Model m, String tools) {
        List<Tool> originalListOfTools = toolRepository.findAll();

        List<Tool> listOfTools = originalListOfTools.stream()
                .filter(tool -> tool.getName().equals(tools))
                .filter(Tool::getAvailable)
                .collect(Collectors.toList());

        m.addAttribute("listOfTools", listOfTools);
        return ("tool-listings-page.html");
    }



}
