package com.feuji.adminservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.feuji.adminservice.repo.ExamRepository;
import com.feuji.adminservice.repo.QuestionRepository;
import com.feuji.adminservice.repo.UserAnswersRepository;
import com.feuji.commonmodel.Exam;
import com.feuji.commonmodel.Question;
import com.feuji.commonmodel.User;
import com.feuji.commonmodel.UserAnswers;

@Service
public class UserAnswersService {

	@Autowired
	private UserAnswersRepository userAnswersRepository;
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private  QuestionRepository questionRepository;
	
private RestTemplate restTemplate;

	
	public UserAnswersService(@Value("${base.url}") String adressBaseUrl,RestTemplateBuilder builder)
	{
		System.out.println(adressBaseUrl);
		this.restTemplate=
				builder.
				rootUri(adressBaseUrl)
				.build();
		System.out.println(adressBaseUrl);	
	}
	
	public void saveAnswer(UserAnswers userAnswers,Long eid,Long qid,Long uid)
	{
		Exam exam=examRepository.findById(eid).get();
		Question question=questionRepository.findById(qid).get();
		User user=restTemplate.getForObject("/getUserById/{uid}",User.class,uid);
       
        UserAnswers answers=userAnswersRepository.findByUserAndExamAndQuestion(user, exam, question);
   
        if(answers!=null)
        {
        	 answers.setUserAnswer(userAnswers.getUserAnswer());
        	System.out.println(answers.getUserAnswer());
        	userAnswersRepository.saveAndFlush(answers);
        }
        else {
        	userAnswersRepository.save(userAnswers);
        }
		
	}
	public List<UserAnswers> getAnswers()
	{
		
		return userAnswersRepository.findAll();
	}
	
	public List<UserAnswers> getUserAnswers(Long uid)
	{
		
		return userAnswersRepository.findAllByUserId(uid);
	}
	
	public List<UserAnswers> getanswers(Long uid,Long eid)
	{
		
		return userAnswersRepository.findAllByUserIdAndExamId(uid,eid);
	}

}
