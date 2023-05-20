package com.feuji.adminservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.feuji.adminservice.repo.CodingQuestionRepository;
import com.feuji.adminservice.repo.ExamRepository;
import com.feuji.adminservice.repo.QuestionRepository;
import com.feuji.adminservice.repo.UserCodeRepository;
import com.feuji.commonmodel.CodingQuestion;
import com.feuji.commonmodel.Exam;
import com.feuji.commonmodel.Question;
import com.feuji.commonmodel.User;
import com.feuji.commonmodel.UserCode;

@Service
public class UserCodeService 
{
	
	@Autowired
	private UserCodeRepository userCodeRepository;
	
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private  CodingQuestionRepository codingQuestionRepository;
	
	private RestTemplate restTemplate;

	
	public UserCodeService(@Value("${base.url}") String adressBaseUrl,RestTemplateBuilder builder)
	{
		System.out.println(adressBaseUrl);
		this.restTemplate=
				builder.
				rootUri(adressBaseUrl)
				.build();
		System.out.println(adressBaseUrl);	
	}
	
	public void saveCode(UserCode userCode,Long eid,Long qid,Long uid)
	{
		Exam exam=examRepository.findById(eid).get();
		
		CodingQuestion codingQuestion=codingQuestionRepository.findById(qid).get();
		
		User user=restTemplate.getForObject("/getUserById/{uid}",User.class,uid);
		
        UserCode code=userCodeRepository.findByUserAndExamAndCodingQuestion(user, exam, codingQuestion);
        System.out.println("================");
        if(code!=null)
        {
        	 code.setCode(userCode.getCode());
        	//System.out.println(code.getUserAnswer());
        	userCodeRepository.saveAndFlush(code);
        }
        else {
        	userCodeRepository.save(userCode);
        }
		
	}
	public List<UserCode> getAnswers()
	{
		List<UserCode> list=userCodeRepository.findAll();
		list.stream().forEach((userCode)->{
			userCode.setUserInputCode(new String(userCode.getCode()));
		});
		return list;
	}
	
//	public List<UserAnswers> getUserAnswers(Long uid)
//	{
//		
//		return userAnswersRepository.findAllByUserId(uid);
//	}
//	
//	public List<UserAnswers> getanswers(Long uid,Long eid)
//	{
//		
//		return userAnswersRepository.findAllByUserIdAndExamId(uid,eid);
//	}



}
