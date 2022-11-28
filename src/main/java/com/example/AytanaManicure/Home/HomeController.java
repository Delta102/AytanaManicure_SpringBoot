package com.example.AytanaManicure.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class HomeController {
    String carpeta = "Home/";
    @GetMapping("")
    public String Index() {
        return carpeta + "Index";
    }

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }
}