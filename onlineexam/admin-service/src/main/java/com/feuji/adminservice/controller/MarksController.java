package com.feuji.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.adminservice.service.MarksService;
import com.feuji.commonmodel.Marks;

@RestController
@CrossOrigin("*")
public class MarksController {

	@Autowired
	private MarksService marksService;
	
	@PostMapping("/savemarks")
	public HttpStatus addexam(@RequestBody Marks marks)
	{
		marksService.addMarks(marks);
		return HttpStatus.OK;
	}
	
	@GetMapping("/getmarks")
	public List<Marks> getMarks()
	{
		return marksService.getMarks();
	}
}
