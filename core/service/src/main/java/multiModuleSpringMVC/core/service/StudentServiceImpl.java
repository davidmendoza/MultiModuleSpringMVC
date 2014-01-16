package multiModuleSpringMVC.core.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import multiModuleSpringMVC.core.dao.StudentDao;
import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.model.Student;

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
	public List<StudentDTO> getStudentList(int page) {
		List<Student> students = studentDao.getStudentList(page);
		List<StudentDTO> studentDtos = new ArrayList<StudentDTO>();
		for (Student student:students) {
			StudentDTO studentDto = new StudentDTO();
			studentDto = transferStudentToDto(student, studentDto);
			studentDtos.add(studentDto);
		}
		return studentDtos;
	}
	
	@Transactional
	public StudentDTO getStudent(int id) {
		Student student = studentDao.getStudent(id);
		StudentDTO studentDto = new StudentDTO();
		return transferStudentToDto(student, studentDto);
	}

	@Transactional
	public int deleteStudent(int id) { 
		return studentDao.deleteStudent(id);
	}

	@Transactional
	public void updateStudent(StudentDTO studentDto) {
		Student updateStudent = studentDao.getStudent(studentDto.getId());
		updateStudent = transferDtoToStudent(updateStudent, studentDto);
		updateStudent.setFirstName(studentDto.getFirstName());
		studentDao.updateStudent(updateStudent);
	}
    
	@Transactional
	public List<StudentDTO> getPassingStudents() {
		Iterator<Object[]> iterator = studentDao.getPassingStudents();
		List<StudentDTO> passingStudentsList = new ArrayList<StudentDTO>();
		while (iterator.hasNext()){
			Object[] row = (Object[])iterator.next();
			StudentDTO passingStudent = new StudentDTO();
			passingStudent.setId((Integer)row[0]);
			passingStudent.setFirstName((String)row[1]);
			passingStudent.setLastName((String)row[2]);
			passingStudent.setLevel((Integer)row[3]);
			passingStudent.setAverage((int)Math.round((Double)row[4]));
			passingStudentsList.add(passingStudent);
		}
		return passingStudentsList;
	}
	
    private Student transferDtoToStudent(Student student, StudentDTO studentDto) {
    	student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setLevel(studentDto.getLevel());
		student.setStatus(studentDto.getStatus());
		student.setGender(studentDto.getGender());
		return student;
    }
    
    private StudentDTO transferStudentToDto(Student student, StudentDTO studentDto) {
        studentDto.setId(student.getId());
	    studentDto.setFirstName(student.getFirstName());
	    studentDto.setLastName(student.getLastName());
	    studentDto.setGender(student.getGender());
	    studentDto.setLevel(student.getLevel());
	    studentDto.setStatus(student.getStatus());
	    return studentDto;
    }


	
}
