package com.hlf.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * spring mvc demo
 * @author hlf
 * @since 2019/06/26 15:11
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/Hello")
    public String HelloWorld( Model model ) {
        model.addAttribute( "message", "Hello world!!!!" );
        return "HelloWorld";
    }
}
