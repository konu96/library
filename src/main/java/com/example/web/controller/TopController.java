package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopController {
	@GetMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("top/index");
		
		return mav;
	}
}