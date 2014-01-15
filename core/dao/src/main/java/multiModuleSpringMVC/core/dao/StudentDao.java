package multiModuleSpringMVC.core.dao;

import java.util.Iterator;
import java.util.List;

import multiModuleSpringMVC.core.dto.PassingStudents;
import multiModuleSpringMVC.core.model.Student;

public interface StudentDao {

	public void addStudent(Student student);
	
	public List<Student> getStudentList();
	
	public Student getStudent(int id);
	
	public int deleteStudent(int id);
	
	public void updateStudent(Student student);
	
	public Iterator<Object[]> getPassingStudents();
	
}
