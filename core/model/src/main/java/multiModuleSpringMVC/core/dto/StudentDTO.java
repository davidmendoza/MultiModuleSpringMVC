package multiModuleSpringMVC.core.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import multiModuleSpringMVC.core.model.Gender;
import multiModuleSpringMVC.core.model.Grades;


public class StudentDTO {

    private int id;
    
	@Size(min=2, max=30, message="First name must be between 2-30 characters only")
	@NotNull
    private String firstName;
	
	@Size(min=2, max=30, message="First name must be between 2-30 characters only")
	@NotNull
    private String lastName;
	
	@NotNull @Min(value = 1, message="Please choose year level")
    private int level;
	
	private Grades grade;
	
	@NotNull(message="Please choose the Student's status")
	private Boolean status;
	
	@NotNull(message="Please enter Student's gender")
	private Gender gender;
	
	public StudentDTO() {
		
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

}
