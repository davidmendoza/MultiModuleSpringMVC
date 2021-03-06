package multiModuleSpringMVC.core.service;

import java.util.List;

import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.model.Student;

public interface StudentService {

    public void addStudent(StudentDTO student);
	
	public List<StudentDTO> getStudentList(int page);
	
	public StudentDTO getStudent(int id);
	
	public int deleteStudent(int id);
	
	public void updateStudent(StudentDTO student);
	
	public List<StudentDTO> getPassingStudents();

    public List<StudentDTO> getSearchResults(String name);
}
