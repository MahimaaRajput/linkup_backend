package com.LinkUp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {
    @GetMapping("/one")
    public String homemethod()
    {
        return "hello this is home comtroller";
    }
    @GetMapping("/two")
    public String homemethod2()
    {
        return "hello this is home comtroller 2 ";
    }


}
