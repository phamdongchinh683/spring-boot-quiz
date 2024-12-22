package com.quizmasterapp.QuizMaster.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmasterapp.QuizMaster.Model.Question;
import com.quizmasterapp.QuizMaster.Repository.QuestionRepository;

@Service
public class QuestionService {

 @Autowired
 private QuestionRepository questionRepository;

 // Add new question
 public void addQuestion(Question question) {
  questionRepository.save(question);
 }

 // Update existing question
 public void updateQuestion(Question question) {
  questionRepository.save(question);
 }

 // Get all questions
 public List<Question> getAllQuestions() {
  return questionRepository.findAll();
 }

 // Get question by ID
 public Question getQuestionById(Long id) {
  return questionRepository.findById(id).orElse(null);
 }

 // Remove question
 public void removeQuestion(Long id) {
  questionRepository.deleteById(id);
 }
}
