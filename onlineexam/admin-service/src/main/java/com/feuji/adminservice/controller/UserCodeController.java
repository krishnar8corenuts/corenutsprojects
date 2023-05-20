package com.feuji.adminservice.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.adminservice.service.UserCodeService;
import com.feuji.commonmodel.UserCode;

@RestController
@CrossOrigin("*")
public class UserCodeController {

	@Autowired
	private UserCodeService userCodeService;
	
	@PostMapping("/savecode")
	public HttpStatus addPaper(@RequestBody UserCode userCode)
	{

		String codeValue=userCode.getUserInputCode();
		userCode.setCode(codeValue.getBytes());
		Long eid=userCode.getExam().getId();
		
		Long qid=userCode.getCodingQuestion().getId();
		Long uid=userCode.getUser().getId();
		userCodeService.saveCode(userCode,eid,qid,uid);
		return HttpStatus.OK;
	}
	
	@GetMapping("/getcode")
	public List<UserCode> getAnswers()
	{
		return userCodeService.getAnswers();
	}
	
//	@GetMapping("/getanswers/{uid}")
//	public List<UserAnswers> getUserAnswers(@PathVariable Long uid)
//	{
//		return userAnswersService.getUserAnswers(uid);
//	}
//	
//	@GetMapping("/getUserAnswers/{uid}/{eid}")
//	public List<UserAnswers> getanswers(@PathVariable Long uid,@PathVariable Long eid)
//	{
//		return userAnswersService.getanswers(uid,eid);
//	}
}
