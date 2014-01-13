package multiModuleSpringMVC.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import multiModuleSpringMVC.core.dao.StudentDao;
import multiModuleSpringMVC.core.model.Student;
import multiModuleSpringMVC.core.model.Grades;

@Service
public class StudentServiceImpl implements StudentService {
    
	@Autowired
	private StudentDao studentDao;
    
	@Transactional
	public void addStudent(Student student) {
		studentDao.addStudent(student);
	}

	@Transactional
	public List<Student> getStudentList() {
		return studentDao.getStudentList();
	}

	@Transactional
	public Student getStudent(int id) {
		return studentDao.getStudent(id);
	}

	@Transactional
	public boolean deleteStudent(int id) { 
		Student studentToDelete = studentDao.getStudent(id);
		if (studentToDelete != null) {
		    studentDao.deleteStudent(studentToDelete);
		    return true;
		}
		return false;
		//use only one return - good practice
	}

	@Transactional
	public void updateStudent(Student student) {
		Student updateStudent = studentDao.getStudent(student.getId());
		updateStudent.setFirstName(student.getFirstName());
		updateStudent.setLastName(student.getLastName());
		updateStudent.setLevel(student.getLevel());
		studentDao.updateStudent(updateStudent);
	}
	
    @Transactional
	public void saveGrades(Student student) {
    	Student updateStudent = studentDao.getStudent(student.getId());
    	Grades grade = updateStudent.getGrade();
    	if (grade == null) {
			Grades newGrade = new Grades();
			newGrade.setMath(student.getGrade().getMath());
			newGrade.setScience(student.getGrade().getScience());
			newGrade.setEnglish(student.getGrade().getEnglish());
			updateStudent.setGrade(newGrade);
		} else {
			grade.setMath(student.getGrade().getMath());
			grade.setEnglish(student.getGrade().getEnglish());
			grade.setScience(student.getGrade().getScience());
			updateStudent.setGrade(grade);
		}
    	
	    studentDao.updateStudent(updateStudent);
	}
	
	
}
