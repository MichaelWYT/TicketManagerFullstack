package com.barclays.ticketmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class IndexController {

    // Use this to test if the backend api is working
    @RequestMapping(value="/isReady")
    public @ResponseBody String isReadyAPI() {
        return "Is Ready :)";
    }
    
}
