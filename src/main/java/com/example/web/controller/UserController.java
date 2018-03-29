package com.example.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.business.entity.User;
import com.example.business.service.UserService;
import com.example.security.LoginUserDetails;
import com.example.web.form.UserForm;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping("/login")
	public ModelAndView loginForm(ModelAndView mav, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		if(loginUserDetails != null) {
			mav.setViewName("redirect:/");
			
			return mav;
		}
		
		mav.setViewName("user/loginForm");
		return mav;
	}
	
	@GetMapping("/sign_up")
	public ModelAndView handler(ModelAndView mav, @AuthenticationPrincipal LoginUserDetails loginUserDetails, UserForm userForm) {
		if(loginUserDetails != null) {
			mav.setViewName("redirect:/");
			
			return mav;
		}
		
		mav.addObject("userForm", userForm);
		mav.setViewName("user/signupForm");
		return mav;
	}
	
	@PostMapping("/sign_up")
	public ModelAndView register(ModelAndView mav, 
								@Validated UserForm form,
								BindingResult result,
								@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		if(loginUserDetails != null) {
			mav.setViewName("redirect:/");
			return mav;
		}
		
		if( !form.getPassword().equals( form.getConfirmPassword() ) ) {
			result.rejectValue("password", "error.passwordCOnfirmation", "do not match");
		}
		
		if( result.hasErrors() ) {
			mav.setViewName("user/signupForm");
			
			return mav;
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userService.save(user);
		
		mav.setViewName("redirect:/");
		return mav;
	}
}
