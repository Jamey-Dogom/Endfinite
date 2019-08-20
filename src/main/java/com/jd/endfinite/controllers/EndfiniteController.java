package com.jd.endfinite.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jd.endfinite.models.Path;
import com.jd.endfinite.models.Stage;
import com.jd.endfinite.models.Story;
import com.jd.endfinite.models.User;
import com.jd.endfinite.services.EndfiniteService;
import com.jd.endfinite.validator.UserValidator;

@Controller
public class EndfiniteController {
	@Autowired
	private EndfiniteService endfiniteService;
	@Autowired
	private UserValidator userValidator;

	// Display the registration - login page
	@RequestMapping("/")
	public String registerForm(@ModelAttribute("user") User user) {
		return "login.jsp";
	}

	// Once a user clicks on register - if validations are met - they are sent to
	// the homepage
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		// if result has errors, return the registration page
		// validator does check on form as well
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "login.jsp";
		} else {
			// else, save the user in the database, save the user id in session, and
			// redirect them to the /home route
			endfiniteService.registerUser(user);
			session.setAttribute("user", user);
			return "redirect:/home";
		}
	}

	// If user is already created - they can login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("user") User user, @RequestParam("email") String email,
			@RequestParam("password") String password, Model model, HttpSession session) {
		// if the user is authenticated, save their user id in session
		if (endfiniteService.authenticateUser(email, password)) {
			session.setAttribute("user", endfiniteService.findByEmail(email));
			return "redirect:/home";
		}
		// else, add error messages and return the login page
		else {
			model.addAttribute("error", "Invalid Credentials. Please try again.");
			return "login.jsp";
		}
	}

	@RequestMapping("/home")
	// Check to make sure a user is stored in session
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

	// Form shown to create a solo story
	@RequestMapping("/create")
	public String initStory(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");

		return "createStory.jsp";
	}

	// display the newly created story and give options to continue or publish
	@RequestMapping("create/{id}")
	public String contCreate(@PathVariable("id") Long id, HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("story", endfiniteService.findByStoryId(id));
		model.addAttribute("user", user);
		return "creation.jsp";
	}

	// User initializes creation of story
	@PostMapping("/createStory")
	public String createStory(@RequestParam("title") String title, @RequestParam("firstStage") String firstStage,
			@RequestParam(value = "collab", required = false) String collab, @RequestParam("pathOne") String pathOne,
			@RequestParam("pathTwo") String pathTwo, @RequestParam("pathThree") String pathThree,
			@RequestParam("pathOneTitle") String pathOneTitle, @RequestParam("pathTwoTitle") String pathTwoTitle,
			@RequestParam("pathThreeTitle") String pathThreeTitle, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		System.out.println("here");

		User user = (User) session.getAttribute("user");
		// create a new story object
		Story newStory = new Story();
		endfiniteService.createStory(newStory);
		// set the user in session as the author
		newStory.setAuthor(user);
		// set title of new story
		newStory.setTitle(title);
		// if user selects to not have collaborators set att to null
		if (collab != null) {
			newStory.setCollab(true);
		}

		// create a new stage object
		Stage initStage = new Stage();
		endfiniteService.createStage(initStage);

		// add stage to list of stages to add to paths
		List<Stage> stages = new ArrayList<>();
		stages.add(initStage);

		// create 3 new paths and assign them to the stage
		Path path1 = new Path();
		path1.setTitle(pathOneTitle);
		path1.setChoice(pathOne);
		path1.setStages(stages);
		endfiniteService.createPath(path1);

		Path path2 = new Path();
		path2.setTitle(pathTwoTitle);
		path2.setChoice(pathTwo);
		path2.setStages(stages);
		endfiniteService.createPath(path2);

		Path path3 = new Path();
		path3.setTitle(pathThreeTitle);
		path3.setChoice(pathThree);
		path3.setStages(stages);
		endfiniteService.createPath(path3);

		// 0 index represents previous stage - 1 represents next stage

		// set description of stage to user info from form
		initStage.setDescription(firstStage);
		endfiniteService.createStage(initStage);

		// set the path to the stage
//		List<Path> paths = new ArrayList<>();
//		paths.add(path1);
//		paths.add(path2);
//		paths.add(path3);
//		initStage.setPaths(paths);
		endfiniteService.createStage(initStage);
		initStage.setStory(newStory);
		endfiniteService.createStage(initStage);

		// create a new list of stages to set for Story
		List<Stage> stages2 = new ArrayList<>();
		stages2.add(initStage);

		// add stage to Story
		newStory.setStages(stages2);
		endfiniteService.createStory(newStory);

		// set the story to the user
		List<Story> stories = new ArrayList<>();
		stories.add(newStory);
		user.setStories(stories);

		List<Story> authors = new ArrayList<>();
		authors.add(newStory);
		user.setAuthors(authors);
		endfiniteService.saveUser(user);

		return "redirect:/create/" + newStory.getId();
	}
	
	
	// View Users Own Stories
	@RequestMapping("/stories/{id}")
	public String userStories(@PathVariable("id") Long id, HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("userStories", endfiniteService.findUserStories(user));
		return "userStory.jsp";
	}
	
	@RequestMapping("/path/{id}")
	public String continuePath(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		Path path = endfiniteService.findPathById(id);
		model.addAttribute("path", path);
		return "createPath.jsp";
		
	}
	
	@PostMapping("/createNextPath/{id}")
	public String createNextPath(@PathVariable("id") Long id, @RequestParam("stage") String stage, @RequestParam("pathOne") String pathOne,
			@RequestParam("pathTwo") String pathTwo, @RequestParam("pathThree") String pathThree,
			@RequestParam("pathOneTitle") String pathOneTitle, @RequestParam("pathTwoTitle") String pathTwoTitle,
			@RequestParam("pathThreeTitle") String pathThreeTitle, HttpSession session) {
		
		Path path = endfiniteService.findPathById(id);
		Stage prevstage = path.getStages().get(0);
		Story story = prevstage.getStory();
		
		List<Stage> stages = path.getStages();
		Stage newStage = new Stage();
		newStage.setStory(story);
		newStage.setDescription(stage);
		endfiniteService.createStage(newStage);
		stages.add(newStage);
		path.setStages(stages);
		endfiniteService.createPath(path);
		
		List<Stage> stages2 = new ArrayList<>();
		stages2.add(newStage);
		
		Path path1 = new Path();
		path1.setTitle(pathOneTitle);
		path1.setChoice(pathOne);
		path1.setStages(stages2);
		endfiniteService.createPath(path1);

		Path path2 = new Path();
		path2.setTitle(pathTwoTitle);
		path2.setChoice(pathTwo);
		path2.setStages(stages2);
		endfiniteService.createPath(path2);

		Path path3 = new Path();
		path3.setTitle(pathThreeTitle);
		path3.setChoice(pathThree);
		path3.setStages(stages2);
		endfiniteService.createPath(path3);
		
		return "redirect:/create/" + story.getId();
	}
	
	@RequestMapping("/publish/{id}")
	public String publishStory(@PathVariable("id") Long id) {
		Story story = endfiniteService.findByStoryId(id);
		if(story.getPublish()) {
			story.setPublish(false);
		}
		else {
			story.setPublish(true);
		}
		endfiniteService.createStory(story);
		return "redirect:/create/" + story.getId();
	}
	
	@RequestMapping("/read")
	public String readStories(Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}

		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("publishedStories", endfiniteService.findPublishedStories());
		return "readHome.jsp";
	}
	
	@RequestMapping("/collab")
	public String collabStories(Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("collabStories", endfiniteService.findCollabStory());
		return "collabStory.jsp";
	}
	
	
	

}
