package com.mobilestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/mobiles")
    public String mobiles() {
        return "mobiles";
    }

    @GetMapping("/product")
    public String product() {
        return "product";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
