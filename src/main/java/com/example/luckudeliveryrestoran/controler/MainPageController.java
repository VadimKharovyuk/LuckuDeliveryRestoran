package com.example.luckudeliveryrestoran.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String homePage(){
        return "homePage";
    }
}
