package com.feng.mp4ba.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model){
		model.addAttribute("userName", "方中信");
		
		return "index";
	} 
}
