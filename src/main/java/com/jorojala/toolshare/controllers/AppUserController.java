package com.jorojala.toolshare.controllers;

import com.jorojala.toolshare.location_api.ZipToLatLon;
import com.jorojala.toolshare.models.AppUser;
import com.jorojala.toolshare.models.Location;
import com.jorojala.toolshare.models.Tool;
import com.jorojala.toolshare.repositories.AppUserRepository;
import com.jorojala.toolshare.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;


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

    @GetMapping("/tool-listings")
    public String getToolListings() {
        return ("tool-listings-page.html");
    }

    @PostMapping("/add-listing")
    public RedirectView postListing(Principal p, String tools) {
        String username = p.getName();
        AppUser currentUser = (AppUser) appUserRepository.findByUsername(username);
        Tool newTool = new Tool();
        newTool.setName(tools);
        newTool.setToolListedByUser(currentUser);
        newTool.setAvailable(true);
        toolRepository.save(newTool);
        return new RedirectView("/tool-listings");
    }

    @GetMapping("/aboutus")
    public  String getAboutUsPage() {return ("aboutus.html");}

    @PostMapping("/signup")
    public RedirectView postSignup(String username, String password, String zipcode) throws IOException
    {
        // instantiate new user object
        AppUser newUser = new AppUser();
        newUser.setUsername(username);
        newUser.setZipcode(zipcode);
        // hash user password
        String hashedPassword = passwordEncoder.encode(password);
        // set user password to new hashed password
        newUser.setPassword(hashedPassword);
        //set user location via ZipToLatLon API call
//        ZipToLatLon zipToLatLon = new  ZipToLatLon();
//
//        Location location = zipToLatLon.getLocation(zipcode);
//        newUser.setLocation(location.getResults());

        // save newly instantiated user object in postgres
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
}

