package multiModuleSpringMVC.core.dao;

import java.util.List;

import multiModuleSpringMVC.core.model.Student;

public interface StudentDao {

	public void addStudent(Student student);
	
	public List<Student> getStudentList();
	
	public Student getStudent(int id);
	
	public void deleteStudent(Student student);
	
	public void updateStudent(Student student);
	
}
