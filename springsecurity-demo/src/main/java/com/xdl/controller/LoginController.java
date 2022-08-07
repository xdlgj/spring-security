package com.xdl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "redirect:main.html";
    }
    @RequestMapping("/toMain")
    public String main() {
        return "redirect:main.html";
    }
    @RequestMapping("/toError")
    public String error() {
        return "redirect:error.html";
    }

    @GetMapping("/demo")
    @ResponseBody
    public String demo() {
        return "demo...";
    }
}
