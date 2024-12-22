package com.quizmasterapp.QuizMaster.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmasterapp.QuizMaster.Model.Question;
import com.quizmasterapp.QuizMaster.Repository.Quizrepository;

@Service
public class Quizservice {
	@Autowired
	private Quizrepository quizrepository;

	public List<Question> startQuiz() {
		return quizrepository.findAll();
	}

	public List<Question> totalQuestions() {
		return quizrepository.findAll();
	}

	public Optional<Question> submitAnswer(Long qid) {

		return quizrepository.findById(qid);
	}

	public Optional<Question> getQuestionById(Long questionId) {
		return quizrepository.findById(questionId);
	}

}
