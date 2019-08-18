package com.jd.endfinite.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jd.endfinite.models.User;
import com.jd.endfinite.services.EndfiniteService;
import com.jd.endfinite.validator.UserValidator;

@Controller
public class EndfiniteController {
	@Autowired
	private EndfiniteService endfiniteService;
	@Autowired
	private UserValidator userValidator;

	@RequestMapping("/")
	public String registerForm(@ModelAttribute("user") User user) {
		return "login.jsp";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		// if result has errors, return the registration page (don't worry about
		// validations just now)

		// validator does check on form as well
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "login.jsp";
		} else {
			// else, save the user in the database, save the user id in session, and
			// redirect them to the /home route
			endfiniteService.registerUser(user);
			session.setAttribute("user", user);
			return "redirect:/events";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("user") User user, @RequestParam("email") String email,
			@RequestParam("password") String password, Model model, HttpSession session) {
		// if the user is authenticated, save their user id in session
		if (endfiniteService.authenticateUser(email, password)) {
			session.setAttribute("user", endfiniteService.findByEmail(email));
			return "redirect:/events";
		}
		// else, add error messages and return the login page
		else {
			model.addAttribute("error", "Invalid Credentials. Please try again.");
			return "login.jsp";
		}
	}

	@RequestMapping("/events")
	public String home(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");

		return "homePage.jsp";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// invalidate session
		session.invalidate();
		// redirect to login page
		return "redirect:/";
	}
}
