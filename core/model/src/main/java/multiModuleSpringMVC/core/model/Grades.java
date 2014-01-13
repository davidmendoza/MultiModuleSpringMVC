package multiModuleSpringMVC.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="GRADES")
public class Grades {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private int id;
	
	@Column(name="MATH")
	private int math;
	
	@Column(name="SCIENCE")
	private int science;
	
	@Column(name="ENGLISH")
	private int english;
	
	@OneToOne(mappedBy="grade")
	private Student student;
	
	public Grades() {
		
	}

	public Grades(int math, int science, int english) {
		super();
		this.math = math;
		this.science = science;
		this.english = english;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getScience() {
		return science;
	}

	public void setScience(int science) {
		this.science = science;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
