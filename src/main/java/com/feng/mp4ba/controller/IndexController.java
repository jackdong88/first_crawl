package com.feng.mp4ba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/a1")
	public String index(){
		return "index";
	}
}
