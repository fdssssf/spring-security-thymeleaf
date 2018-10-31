package com.trungthuc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.trungthuc.entity.User;
import com.trungthuc.service.SecurityService;
import com.trungthuc.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService ;
	@Autowired
	SecurityService securityService ;
	
	
	

	@GetMapping(value = "/login")
	public String login(Model model, String error, String logout, HttpServletRequest request) {
		if (logout != null) {
			model.addAttribute("logout", "You have been logged out successfully.");
		}
		return "login";
	}

	@GetMapping(value = "/loginError")
	public String loginError(Model model, String username) {
		model.addAttribute("error", "Your username and password is invalid.");
		model.addAttribute("username", username);
		return "login";
	}

	@GetMapping(value = "/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@PostMapping(value = "/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, String[] roles,
			Model model) {
		String password = userForm.getPassword();
		userService.save(userForm, roles);
		securityService.autologin(userForm.getUsername(), password);
		return "registration";
	}
	
	@GetMapping(value = "/admin")
	public String admin() {
	
		return "admin/admin";
	}
	
	@GetMapping(value = "/user")
	public String user() {
	
		return "user/user";
	}
	@GetMapping(value="/403")
	  public String access(){
	    return "access";
	  }
	@GetMapping(value="/main")
	  public String main(){
	    return "main";
	  }


}
