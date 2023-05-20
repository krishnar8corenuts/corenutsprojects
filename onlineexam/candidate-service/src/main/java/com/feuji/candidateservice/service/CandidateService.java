package com.feuji.candidateservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.feuji.candidateservice.repo.CandidateRepository;
import com.feuji.commonmodel.User;
import com.feuji.commonmodel.UserAnswers;

@Service
public class CandidateService {

	@Autowired
	private   CandidateRepository candidateRepository;

	public String correctAnswer;

	private RestTemplate restTemplate;
	
	public CandidateService(@Value("${admin.url}") String adminUrl ,RestTemplateBuilder builder) 
	{
		this.restTemplate = builder.rootUri(adminUrl).build();
	}
	
	
	//Product product = restTemplate.getForObject(sellerserviceUrl+"/findproductbyid?productId="+productId, Product.class);

	
	public Long getuserScore(Long uid,Long eid)
	{
		Long score=(long) 0;
		UserAnswers[] userAnswers =restTemplate.getForObject("/getUserAnswers/{uid}/{eid}",UserAnswers[].class,uid,eid);
		
		for(UserAnswers answers:userAnswers) 
		{
			//answers.getExam().getCreatePaper().getQuestions().stream().forEach((q)->{correctAnswer=q.getAnswer();});
			
			if(answers.getUserAnswer().equals(answers.getQuestion().getAnswer()))
			{
				score++;
			}
		}
		
		return score;
	}
}

