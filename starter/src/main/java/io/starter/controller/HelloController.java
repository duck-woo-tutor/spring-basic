package io.starter.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false, defaultValue = "응애!") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello String " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // HTTP 프로토콜의 Body에 데이터를 직접 넣어주겠다.
    public Hello helloApi(@RequestParam("name") String name) {
        return new Hello("woo!!");
    }
}

@Getter
@Setter
class Hello {
    private String name;

    public Hello(String name) {
        this.name = name;
    }
}