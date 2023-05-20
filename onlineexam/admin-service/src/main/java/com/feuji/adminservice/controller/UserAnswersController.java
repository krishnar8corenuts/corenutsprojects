package com.feuji.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.adminservice.service.UserAnswersService;
import com.feuji.commonmodel.UserAnswers;

@RestController
@CrossOrigin("*")
public class UserAnswersController {
	
	@Autowired
	private UserAnswersService userAnswersService;
	
	@PostMapping("/saveanswer")
	public HttpStatus addPaper(@RequestBody UserAnswers userAnswers)
	{
		Long eid=userAnswers.getExam().getId();
		Long qid=userAnswers.getQuestion().getId();
		Long uid=userAnswers.getUser().getId();
		userAnswersService.saveAnswer(userAnswers,eid,qid,uid);
		return HttpStatus.OK;
	}
	
	@GetMapping("/getanswers")
	public List<UserAnswers> getAnswers()
	{
		return userAnswersService.getAnswers();
	}
	
	@GetMapping("/getanswers/{uid}")
	public List<UserAnswers> getUserAnswers(@PathVariable Long uid)
	{
		return userAnswersService.getUserAnswers(uid);
	}
	
	@GetMapping("/getUserAnswers/{uid}/{eid}")
	public List<UserAnswers> getanswers(@PathVariable Long uid,@PathVariable Long eid)
	{
		return userAnswersService.getanswers(uid,eid);
	}
	

}
