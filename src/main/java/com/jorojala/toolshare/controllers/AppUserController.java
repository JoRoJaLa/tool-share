package com.jorojala.toolshare.controllers;

import com.jorojala.toolshare.location_api.ZipToLatLon;
import com.jorojala.toolshare.models.AppUser;
import com.jorojala.toolshare.models.Location;
import com.jorojala.toolshare.models.Tool;
import com.jorojala.toolshare.models.Results;
import com.jorojala.toolshare.repositories.AppUserRepository;
import com.jorojala.toolshare.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class AppUserController {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ToolRepository toolRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/")
    public String getSplash(){
        return ("splash.html");
    }

    @GetMapping("/home")
    public String getHome(Model m, Principal p){
        String username =  p.getName();
        m.addAttribute("username", username);
        return ("index.html");
    }
    @GetMapping("/profile")
    public String getUserProfile(Principal p, Model m){
        //TODO Add Return Buttons for the Tools
        String username = p.getName();
        AppUser currentUser = (AppUser) appUserRepository.findByUsername(username);
        m.addAttribute("username", username);
        m.addAttribute("zipcode", currentUser.getZipcode());
        m.addAttribute("listOfTools", currentUser.getToolsListed());
        m.addAttribute("listOfBorrowedTools", currentUser.getToolsBorrowed());
        return ("profile.html");

    }

    @GetMapping("/login")
    public String getLoginPage()
    {
        return ("login-page.html");
    }

    @GetMapping("/signup")
    public String getSignupPage()
    {
        return ("signup-page.html");
    }

    @GetMapping("/createlisting")
    public String getCreateListingsPage() { return ("tool-form.html");}

    @PostMapping("/add-listing")
    public RedirectView postListing(Principal p, String tools) {
        String username = p.getName();
        AppUser currentUser = (AppUser) appUserRepository.findByUsername(username);
        Tool newTool = new Tool();
        if (tools.equals("drill")){
            newTool.setImage("images/drill.png");
        }
        else if (tools.equals("crowbar")){
            newTool.setImage("images/crowbar.png");
        }
        else if (tools.equals("sledgehammer")){
            newTool.setImage("images/sledgehammer.png");
        }
        else if (tools.equals("circular saw")){
            newTool.setImage("images/circularsaw.png");
        }
        newTool.setName(tools);
        newTool.setToolListedByUser(currentUser);
        newTool.setAvailable(true);
        toolRepository.save(newTool);
        return new RedirectView("/tool-listings");
    }


    @GetMapping("/tool-listings")
    public String getToolListings(Model m, Principal p) {
        AppUser currentUser = (AppUser) appUserRepository.findByUsername(p.getName());
        List<Tool> originalListOfTools = toolRepository.findAll();

        List<Tool> listOfTools = originalListOfTools.stream()
                .filter(Tool::getAvailable)
                .filter(tool -> !tool.getToolListedByUser().equals(currentUser))
                .collect(Collectors.toList());
        List<String> toolOwners = listOfTools.stream().map(tool -> tool.getToolListedByUser().getUsername()).toList();

        m.addAttribute("toolOwners", toolOwners);
        m.addAttribute("listOfTools", listOfTools);
        return ("tool-listings-page.html");
    }





    @PostMapping("/borrow-tool")
    public RedirectView borrowTool(Principal p, Long toolId) throws IOException {
        String username = p.getName();
        AppUser currentUser = (AppUser) appUserRepository.findByUsername(username);
        Tool toolToBorrow = toolRepository.getById(toolId);

        if (toolToBorrow != null && toolToBorrow.getAvailable() && !toolToBorrow.getToolListedByUser().equals(currentUser)) {
            toolToBorrow.setToolBorrowedByUser(currentUser);
            toolToBorrow.setAvailable(false);
            toolRepository.save(toolToBorrow);
        } else {
            throw new IOException("Tool not available to borrow");
        }

        return new RedirectView("/profile");
    }

    @PutMapping("/return-tool")
    public RedirectView returnTool(Principal p, Long toolId){
        String username = p.getName();
        AppUser currentUser = (AppUser) appUserRepository.findByUsername(username);
        Tool toolToReturn = toolRepository.getById(toolId);
        toolToReturn.setAvailable(true);
        toolToReturn.setToolBorrowedByUser(null);
        List<Tool> toolsBorrowed = currentUser.getToolsBorrowed();
        List<Tool> updatedBorrowedTools = toolsBorrowed.stream().filter(tool -> !tool.equals(toolToReturn)).toList();
        currentUser.setToolsBorrowed(updatedBorrowedTools);
        toolRepository.save(toolToReturn);
        appUserRepository.save(currentUser);

        return new RedirectView("/profile");
    }

    @GetMapping("/aboutus")
    public  String getAboutUsPage() {return ("aboutus.html");}

    @PostMapping("/signup")
    public RedirectView postSignup(String username, String password, String zipcode) throws IOException
    {

        if(appUserRepository.existsByUsername(username)){
            return new RedirectView("/signup");
        }
        AppUser newUser = new AppUser();
        newUser.setUsername(username);
        newUser.setZipcode(zipcode);

        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPassword(hashedPassword);

        Location location = ZipToLatLon.getLocation(zipcode);
        Results[] results = location.getResults();
        newUser.setZipcode(results[0].getPostcode());
        newUser.setResults(results[0]);

        appUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServletRequest(String username, String password)
    {
        try {
            request.login(username, password);
        } catch(ServletException SE) {
            System.out.println("Error: Servlet Exception");
            SE.printStackTrace();
        }
    }

    @PostMapping("/logout")
    public RedirectView logOutUserAndGetLogin(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();

        return new RedirectView("/login");
    }
}

