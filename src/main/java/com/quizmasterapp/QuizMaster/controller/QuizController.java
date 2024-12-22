package com.quizmasterapp.QuizMaster.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.quizmasterapp.QuizMaster.Model.Question;
import com.quizmasterapp.QuizMaster.Service.Quizservice;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("username")
public class QuizController {

	@Autowired
	private Quizservice quizservice;
	Integer score = 0;

	@GetMapping("/startquiz")
	public String startQuiz(Model model, HttpSession session) {
		score = 0;
		Question question = quizservice.startQuiz().get(0);
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("question", question);
		return "Questions";
	}

	@GetMapping("/submitAnswer")
	public ResponseEntity<String> submitAnswer(@RequestParam("questionId") Long questionId,
			@RequestParam("question") String answer, HttpSession session, Model model) {
		Optional<Question> rightAnswers = quizservice.submitAnswer(questionId);
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		if (rightAnswers.isPresent() && rightAnswers.get().getCorrectAnswer().equals(answer)) {
			score++;
			return new ResponseEntity<>("true", HttpStatus.OK);
		}
		return new ResponseEntity<>("false", HttpStatus.OK);
	}

	@GetMapping("/nextquestion")
	public String nextQuestion(@RequestParam("questionId") Long questionId, Model model, HttpSession session) {
		Long nextQuestion = ++questionId;
		Optional<Question> question = quizservice.getQuestionById(nextQuestion);
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		if (question.isEmpty()) {
			return "redirect:/score";
		}
		model.addAttribute("question", question.get());
		return "Questions";
	}

	@GetMapping("/prevquestion")
	public String prevQuestion(@RequestParam("questionId") Long questionId, Model model, HttpSession session) {
		Long prevQuestion = --questionId;
		if (questionId < 1) {
			prevQuestion = (long) 1;
		}
		Optional<Question> question = quizservice.getQuestionById(prevQuestion);
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		if (question.isEmpty()) {
			return "redirect:/startquiz";
		}
		model.addAttribute("question", question.get());
		return "Questions";
	}

	@GetMapping("/score")
	public String calculateScore(Model model, HttpSession session) {
		List<Question> totalQuestions = quizservice.totalQuestions();
		model.addAttribute("totalQuestions", totalQuestions.size());
		String username = (String) session.getAttribute("username");
		String totalscore = score.toString();
		model.addAttribute("score", totalscore);
		model.addAttribute("username", username);
		return "End";
	}
}
