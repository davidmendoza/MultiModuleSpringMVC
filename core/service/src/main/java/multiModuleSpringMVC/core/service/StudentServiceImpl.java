package multiModuleSpringMVC.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import multiModuleSpringMVC.core.dao.StudentDao;
import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.model.Student;
import multiModuleSpringMVC.core.model.Grades;

@Service
public class StudentServiceImpl implements StudentService {
    
	@Autowired
	private StudentDao studentDao;
    
	@Transactional
	public void addStudent(StudentDTO studentDto) {
		Student student = new Student();
		student = transferDtoToStudent(student, studentDto);
		studentDao.addStudent(student);
	}
	
	@Transactional
	public List<Student> getStudentList() {
		return studentDao.getStudentList();
	}
	
	@Transactional
	public StudentDTO getStudent(int id) {
		Student student = studentDao.getStudent(id);
		StudentDTO studentDto = new StudentDTO();
		studentDto.setId(student.getId());
		studentDto.setFirstName(student.getFirstName());
		studentDto.setLastName(student.getLastName());
		studentDto.setGender(student.getGender());
		studentDto.setLevel(student.getLevel());
		studentDto.setStatus(student.getStatus());
		return studentDto;
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
	public void updateStudent(StudentDTO studentDto) {
		Student updateStudent = studentDao.getStudent(studentDto.getId());
		updateStudent = transferDtoToStudent(updateStudent, studentDto);
		updateStudent.setFirstName(studentDto.getFirstName());
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
    
    private static Student transferDtoToStudent(Student student, StudentDTO studentDto) {
    	student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setLevel(studentDto.getLevel());
		student.setStatus(studentDto.getStatus());
		student.setGender(studentDto.getGender());
		return student;
    }
	
	
}
