package io.controller;

import io.controller.dto.NameAge;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/practice")
public class PracticeController {

    @ResponseBody
    @GetMapping("/name/{name}/age/{age}")
    public void pathVariable(@PathVariable String name, @PathVariable int age) {
        System.out.println("name : " + name + ", age : " + age);
    }

    @ResponseBody
    @GetMapping("/name/{name}")
    public void pathVariable2(String name) {
        /*
        GET localhost:8080/practice/name/woo
        @PathVariable 생략 불가능 name -> null
         */

        System.out.println("name : " + name + ". ");
    }

    @ResponseBody
    @GetMapping("/query-string")
    public void  requestParamGet(@RequestParam String name, int age) {
        System.out.println("name : " + name + ", age : " + age);
    }

    @ResponseBody
    @PostMapping("/body")
    public void  requestParamGet2(@RequestParam String name, int age) {
        /*
        POST localhost:8080/practice/body
        Content-Type: application/x-www-form-urlencoded

        name=woo&age=5

         @RequestParam 생략가능
         */
        System.out.println("name : " + name + ", age : " + age);
    }

    @ResponseBody
    @PostMapping("/body/query-string")
    public void modelAttribute(@ModelAttribute NameAge nameAge) {
        System.out.println("name : " + nameAge.getName() + ", age : " + nameAge.getAge());
    }

    @ResponseBody
    @PostMapping("/body/query-string2")
    public void modelAttribute2(NameAge nameAge) { // 생략 가능
        System.out.println("name : " + nameAge.getName() + ", age : " + nameAge.getAge());
    }

    @ResponseBody
    @PostMapping("/request-body")
    public void requestBody(@RequestBody NameAge nameAge) {
        System.out.println("name : " + nameAge.getName() + ", age : " + nameAge.getAge());
    }

    @ResponseBody
    @PostMapping("/request-body2")
    public void requestBody2(NameAge nameAge) {
        /*
        POST localhost:8080/practice/request-body2
        Content-Type: application/json

        {
          "name" :  "woo",
          "age" :  5
        }

        -> name : null, age : null
         */
        System.out.println("name : " + nameAge.getName() + ", age : " + nameAge.getAge());
    }
}


