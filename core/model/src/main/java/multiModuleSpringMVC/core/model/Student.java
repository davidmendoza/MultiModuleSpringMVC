package multiModuleSpringMVC.core.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;


@Entity
@Table(name="STUDENT")
public class Student {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
    private int id;
	//validations should be in DTO 
	@Size(min=2, max=30, message="First name must be between 2-30 characters only")
	@NotNull
	@Column(name="FIRST_NAME")
    private String firstName;
	
	@Size(min=2, max=30, message="First name must be between 2-30 characters only")
	@NotNull
	@Column(name="LAST_NAME")
    private String lastName;
	
	@NotNull @Min(value = 1, message="Please choose year level")
	@Column(name="YEAR_LEVEL")
    private int level;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="GRADES_ID")
	private Grades grade;
	
	@OneToMany(mappedBy="student")
	private Set<Subject> subjects;
	
	@Column(name="STATUS")
	@Type(type="yes_no")
	private Boolean status;
	
	@Column(name="GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	public Student() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Grades getGrade() {
		return grade;
	}

	public void setGrade(Grades grade) {
		this.grade = grade;
	}

	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	
	

}
