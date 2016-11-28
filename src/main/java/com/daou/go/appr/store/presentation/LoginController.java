package com.daou.go.appr.store.presentation;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String login(Map<String, Object> model) {
        // TODO if user authenticated, go to main
        return "/main";
    }

    @RequestMapping(value = {"/login"})
    public String main(Map<String, Object> model) {
        return "/main";
    }

}
