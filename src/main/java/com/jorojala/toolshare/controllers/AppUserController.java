package com.jorojala.toolshare.controllers;

import com.jorojala.toolshare.models.AppUser;
import com.jorojala.toolshare.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Controller
public class AppUserController {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;


    @GetMapping("/")
    public String getSplashPage()
    {
        return("login-page.html");
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

    @PostMapping("/signup")
    public RedirectView postSignup(String username, String password, String zipcode)
    {
        // instantiate new user object
        AppUser newUser = new AppUser(username, password, zipcode);
        // hash user password
        String hashedPassword = passwordEncoder.encode(password);
        // set user password to new hashed password
        newUser.setPassword(hashedPassword);
        // save newly instantiated user object in postgres
        appUserRepository.save(newUser);
        authWithHttpServletRequest(username, hashedPassword);
        return new RedirectView("/test");
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

