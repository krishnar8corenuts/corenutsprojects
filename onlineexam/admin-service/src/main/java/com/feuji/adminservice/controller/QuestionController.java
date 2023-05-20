package com.feuji.adminservice.controller;


import com.feuji.adminservice.service.QuestionService;
import com.feuji.commonmodel.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@CrossOrigin("*")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/addquestion/{sid}")
	public HttpStatus insertQuestion(@RequestBody Question question, @PathVariable Long sid) {
		questionService.addquestion(question,sid);
		return HttpStatus.OK;
	}
	
	@GetMapping("/getallquestions/{sid}")
	public Set<Question> getAllQuestions(@PathVariable Long sid)
	{
		
		return questionService.getAllQuestions(sid);
	}
	
	@PutMapping("/updatequestion")
	public Question updateQuestion(@RequestBody Question question){
		
		return questionService.updatequestions(question);
	}
	@GetMapping("/getquestionbyid/{id}")
	public Question getQuestionById(@PathVariable Long id) {
		return questionService.getQuestionById(id);
		
	}
	@DeleteMapping("/deleteQuestion/{id}")
	public HttpStatus deleteQuestion(@PathVariable Long id) {
		questionService.deleteQuestionById(id);
		return HttpStatus.OK;
	}
	
	

}
