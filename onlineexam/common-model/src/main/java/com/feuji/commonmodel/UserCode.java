package com.feuji.commonmodel;

import java.io.Serializable;
import java.sql.Blob;

import org.springframework.core.serializer.Serializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "user_code")
public class UserCode implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String language;
	
	// @JsonDeserialize(using = BlobDeserializer.class)
	//private Blob code;
	@Lob
	private byte[] code;
	
	@Transient
	private String userInputCode;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "exam_id")
	private Exam exam;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "question_id")
	private CodingQuestion codingQuestion;

	public UserCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCode(int id, String language, byte[] code, User user, Exam exam, CodingQuestion codingQuestion) {
		super();
		this.id = id;
		this.language = language;
		this.code = code;
		this.user = user;
		this.exam = exam;
		this.codingQuestion = codingQuestion;
	}

	
	public String getUserInputCode() {
		return userInputCode;
	}

	public void setUserInputCode(String userInputCode) {
		this.userInputCode = userInputCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public CodingQuestion getCodingQuestion() {
		return codingQuestion;
	}

	public void setCodingQuestion(CodingQuestion codingQuestion) {
		this.codingQuestion = codingQuestion;
	}

	public byte[] getCode() {
		return code;
	}

	public void setCode(byte[] code) {
		this.code = code;
	}
	
	
}
