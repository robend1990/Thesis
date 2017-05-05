package com.pgs.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	@RequestMapping(value="/greeting")
	public String sayHello(Model model){
		model.addAttribute("greeting", "Hello World !");
		return "hello";
	}
	
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public String index(Locale locale){
		return "/resources/home.html";
	}
	
	
}
