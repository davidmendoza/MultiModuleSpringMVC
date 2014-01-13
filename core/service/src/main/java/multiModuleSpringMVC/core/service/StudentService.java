package multiModuleSpringMVC.core.service;

import java.util.List;

import multiModuleSpringMVC.core.model.Student;

public interface StudentService {

    public void addStudent(Student student);
	
	public List<Student> getStudentList();
	
	public Student getStudent(int id);
	
	public boolean deleteStudent(int id);
	
	public void updateStudent(Student student);
	
	public void saveGrades(Student student);
}
