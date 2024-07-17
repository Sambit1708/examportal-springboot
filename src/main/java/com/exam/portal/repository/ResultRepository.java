package com.exam.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.portal.entites.User;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.entites.exam.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {


	public List<Result> findByUser(User user);
	
	@Query("FROM Result res ORDER BY res.createDate DESC LIMIT 5")
	public List<Result> findRecentResult();
	
	public Optional<Result> findByUserAndQuiz(User user, Quiz quiz);
	
	public List<Result> findByQuiz(Quiz quiz);
	
	@Query(value = "SELECT * FROM quiz_result WHERE quiz_id IN "
            + "(SELECT id FROM quiz WHERE title LIKE CONCAT('%', :quiz, '%')) "
            + "OR user_id IN "
            + "(SELECT id FROM users WHERE first_name LIKE CONCAT('%', :firstName, '%') "
            + "OR last_name LIKE CONCAT('%', :lastName, '%'))", nativeQuery = true)
	public List<Result> searchResults(	@Param("quiz") String quiz, 
										@Param("firstName") String firstName, 
										@Param("lastName") String lastName); 
}
