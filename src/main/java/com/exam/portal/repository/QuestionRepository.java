package com.exam.portal.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.entites.exam.Question;
import com.exam.portal.entites.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz);
}
