package com.morozov.warrantywebsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "redirect:claims";
    }

    //    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getUsers() {
        log.info("users");
        return "users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dealers")
    public String getDealers() {
        log.info("dealers");
        return "dealers";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dealer")
    public String getDealer() {
        log.info("dealer profile");
        return "dealer";
    }
/*    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }*/

    @GetMapping("/claims")
    public String getClaims() {
        log.info("claims");
        return "claims";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find-parts")
    public String getParts() {
        log.info("find parts");
        return "findParts";
    }

    @GetMapping("/parts")
    public String getAllParts() {
        log.info("get all parts");
        return "allParts";
    }

    @GetMapping("/profile")
    public String getProfile() {
        log.info("profile");
        return "profile";
    }
}
