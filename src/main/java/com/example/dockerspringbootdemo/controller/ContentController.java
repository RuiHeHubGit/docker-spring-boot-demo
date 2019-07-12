package com.example.dockerspringbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class ContentController {
    @RequestMapping("content")
    public String content(Model model) {
        Date now = new Date();
        model.addAttribute("pageContent", String.format("Now:%tF %tT", now, now));
        return "content";
    }
}
