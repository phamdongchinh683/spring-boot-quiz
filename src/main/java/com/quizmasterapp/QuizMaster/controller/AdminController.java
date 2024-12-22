package com.quizmasterapp.QuizMaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quizmasterapp.QuizMaster.Model.Question;
import com.quizmasterapp.QuizMaster.Model.Users;
import com.quizmasterapp.QuizMaster.Service.QuestionService;
import com.quizmasterapp.QuizMaster.Service.Userservice;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private Userservice userservice;

	@Autowired
	private QuestionService questionService;

	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		if (username != null) {
			model.addAttribute("username", username);
			return "Home";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("/register")
	public String registerUser() {

		return "Register";
	}

	@GetMapping("/login")
	public String loginPage() {

		return "Login";
	}

	@PostMapping("/validate-login")
	public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password,
			HttpSession session, Model model) {
		Users foundUser = userservice.loginValidate(username);
		if (foundUser != null && foundUser.getPassword().equals(password)) {
			session.setAttribute("username", username);
			return ResponseEntity.status(HttpStatus.FOUND)
					.header(HttpHeaders.LOCATION, "/")
					.body("Login successful");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	@GetMapping("/create-user-form")
	public String showCreateUserForm(Model model) {
		model.addAttribute("user", new Users());
		return "CreateUser";
	}

	@PostMapping("/create-user-form")
	public String createUser(@ModelAttribute Users user, Model model) {
		userservice.registerUser(user);
		model.addAttribute("users", userservice.listUsers());
		return "ListUser";
	}

	@GetMapping("/list-users")
	public String listUsers(Model model) {
		model.addAttribute("users", userservice.listUsers());
		return "ListUser";
	}

	@GetMapping("/add-question")
	public String showAddQuestionForm(Model model) {
		model.addAttribute("question", new Question());
		return "CreateQuestion";
	}

	@PostMapping("/add-question")
	public String addQuestion(@ModelAttribute Question question) {
		questionService.addQuestion(question);
		return "redirect:/list-question";
	}

	@GetMapping("/update-question/{id}")
	public String showUpdateQuestionForm(@PathVariable("id") Long questionId, Model model) {
		Question question = questionService.getQuestionById(questionId);
		model.addAttribute("question", question);
		return "UpdateQuestion";
	}

	@PostMapping("/update-question")
	public String updateQuestion(@ModelAttribute Question question) {
		questionService.updateQuestion(question);
		return "redirect:/list-question";
	}

	@GetMapping("/list-question")
	public String listQuestions(Model model) {
		model.addAttribute("questions", questionService.getAllQuestions());
		return "ListQuestion";
	}

	@PostMapping("/remove-question")
	public String removeQuestion(@RequestParam Long questionId) {
		questionService.removeQuestion(questionId);
		return "redirect:/list-question";
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.status(HttpStatus.FOUND)
				.header(HttpHeaders.LOCATION, "/")
				.body("Login out");
	}

}
