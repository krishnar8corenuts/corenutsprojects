package com.feuji.adminservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feuji.commonmodel.Exam;
import com.feuji.commonmodel.Question;
import com.feuji.commonmodel.User;
import com.feuji.commonmodel.UserAnswers;

public interface UserAnswersRepository extends JpaRepository<UserAnswers, Long>{

public UserAnswers findByUserAndExamAndQuestion(User user,Exam exam,Question question);
	
List<UserAnswers> findAllByUserId(Long userId);

List<UserAnswers> findAllByUserIdAndExamId(Long uid,Long eid);


}
