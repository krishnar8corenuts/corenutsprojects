package com.feuji.adminservice.controller;

import com.feuji.adminservice.service.SubjectService;
import com.feuji.commonmodel.Subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	
	@PostMapping("/addsubject")
	public HttpStatus addSubject(@RequestBody Subject subject)
	{
		subjectService.addSubject(subject);
		
		return HttpStatus.OK;
	}

	@PutMapping("/updatesubject/{id}")
	public HttpStatus updateSubject( @PathVariable Long id, @RequestBody Subject subject)
	{
		subjectService.updateSubject(subject, id);
		System.out.println(subject.getName()+"  "+subject.getDescription());
		return HttpStatus.OK;
	}
	
	@GetMapping("/getAllSubjects")
	public List<Subject> getAll()
	{
		List<Subject> list=subjectService.getAll();
		
		return list;
	}
	@GetMapping("/getSubjectById/{id}")
	public Subject getSubjectById(@PathVariable Long id) {
		return subjectService.getSubjectById(id);
	}
	@DeleteMapping("/deleteSubject/{id}")
	public HttpStatus deleteSubject(@PathVariable Long id) {
		 subjectService.deleteSubject(id);;
		return HttpStatus.OK;
	}

	

}
