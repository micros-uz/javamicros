package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
public class ExampleController {

   private static Logger log = LoggerFactory.getLogger(ExampleController.class);
   
   @RequestMapping(value="/hello/{hello}", method=RequestMethod.GET)
   public String test(@PathVariable String hello, Model model){
      model.addAttribute("message", hello);
      log.info("Saying hi with " + hello);
      return "hello";
   }
}
