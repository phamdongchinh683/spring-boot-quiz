package com.quizmasterapp.QuizMaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizmasterapp.QuizMaster.Model.Question;

@Repository
public interface Quizrepository extends JpaRepository<Question, Long> {

}
