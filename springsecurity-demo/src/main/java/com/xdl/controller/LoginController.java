package com.xdl.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    //@Secured({"ROLE_abc"})
    //@PreAuthorize("hasRole('abc')") 允许角色以ROLE_开头，也可以不以ROLE_开头，但是配置类不允许以ROLE_开头
    @PreAuthorize("hasRole('ROLE_abc')")
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
