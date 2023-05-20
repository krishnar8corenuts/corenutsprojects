package com.feuji.adminservice.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.feuji.adminservice.repo.CreatePaperRepository;
import com.feuji.adminservice.repo.ExamRepository;
import com.feuji.adminservice.repo.QuestionRepository;
import com.feuji.commonmodel.CreatePaper;
import com.feuji.commonmodel.Exam;
import com.feuji.commonmodel.Question;
import com.feuji.commonmodel.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExamService {
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private CreatePaperRepository createPaperRepository;
	
	public void addExam(Exam exam,Long id)
	{
		CreatePaper createPaper= createPaperRepository.findById(id).get();
		exam.setCreatePaper(createPaper);
		exam.setStatus("active");
		
		examRepository.save(exam);
	}
	
	public Exam getbySid(long id)
	{
		return examRepository.findById(id).get();
	}
	public List<Exam> getAll()
	{
		return  examRepository.findByStatus("active");
	}
	public void updateExam(Exam exam,Long id) {
		Exam exam1 = examRepository.findById(id).get();
//		exam1.setCode(exam.getCode());
//		exam1.setDate(exam.getDate());
//		exam1.setMarks(exam.getMarks());
//		exam1.setName(exam.getName());
//		exam1.setQues_marks(exam.getQues_marks());
//		exam1.setTime(exam.getTime());
//		exam1.setTotal_marks(exam.getTotal_marks());
		examRepository.saveAndFlush(exam1);

	}
	public Set<Subject> getSubjectsByCode(String code){
		Exam exam=examRepository.findByCode(code);
		
		Set<Subject> set=new HashSet<>();
		set.addAll(exam.getCreatePaper().getQuestions().stream().map((s)->s.getSubject()).collect(Collectors.toSet())); 
		set.addAll(exam.getCreatePaper().getCodingQuestions().stream().map((s)->s.getSubject()).collect(Collectors.toSet()));
		return set;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set getQuestionsBySubjectId(Long sid,String code){
		Exam exam=examRepository.findByCode(code);
		
		Set set=new HashSet();
		set.addAll(exam.getCreatePaper().getQuestions().stream().filter((q)->q.getSubject().getId()==sid ).collect(Collectors.toSet()));
		set.addAll(exam.getCreatePaper().getCodingQuestions().stream().filter((q)->q.getSubject().getId()==sid).collect(Collectors.toSet()));
		
		return 	set;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set getAllQuestionsByCode(String code){
		Exam exam=examRepository.findByCode(code);
		
		Set set=new HashSet();
		set.addAll(exam.getCreatePaper().getQuestions());
		set.addAll(exam.getCreatePaper().getCodingQuestions());
		return 	set;
	}

	public Exam getExamById(Long id) 
	{
		
		return examRepository.findById(id).get();
	}
	
	public void deletebyid(Long id)
	{
		Exam exam=examRepository.findById(id).get();
		exam.setStatus("inactive");
		examRepository.saveAndFlush(exam);
	}
//	public void deleteexamwithpaper(Long eid,Long pid)
//	{
//		Exam exam=examRepository.findById(eid).get();
//		exam.setCreatePaper(null);
//		examRepository.deleteById(eid);
//		createPaperRepository.deleteById(pid);
//	}

	public Exam getExamByCode(String code) {
		
		return examRepository.findByCode(code);
	}


}
