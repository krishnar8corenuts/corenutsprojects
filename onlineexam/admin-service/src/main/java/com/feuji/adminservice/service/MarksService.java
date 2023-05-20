package com.feuji.adminservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.adminservice.repo.MarksRepository;
import com.feuji.commonmodel.Marks;

@Service
public class MarksService 
{
	@Autowired
	private MarksRepository marksRepository;
	
	public void addMarks(Marks marks)
	{
		marksRepository.save(marks);
	}

	public List<Marks> getMarks()
	{
		return marksRepository.findAll();
	}

}
