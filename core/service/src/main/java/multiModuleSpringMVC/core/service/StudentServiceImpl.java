package multiModuleSpringMVC.core.service;

import multiModuleSpringMVC.core.dao.StudentDao;
import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.model.Name;
import multiModuleSpringMVC.core.model.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    
	@Autowired
	private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Transactional
	public void addStudent(StudentDTO studentDto) {
        Student student = new Student();
        if (studentDto != null) {
            BeanUtils.copyProperties(studentDto, student);
            Name name = new Name(studentDto.getFirstName(), studentDto.getLastName());
            student.setName(name);
            studentDao.addStudent(student);
        }
	}
	
	@Transactional
	public List<StudentDTO> getStudentList(int page) {
		List<Student> students = studentDao.getStudentList(page);
		List<StudentDTO> studentDtos = new ArrayList<StudentDTO>();
		for (Student student:students) {
			StudentDTO studentDto = new StudentDTO();
			BeanUtils.copyProperties(student, studentDto);
            studentDto.setFirstName(student.getName().getFirstName());
            studentDto.setLastName(student.getName().getLastName());
            studentDtos.add(studentDto);
		}
		return studentDtos;
	}
	
	@Transactional
	public StudentDTO getStudent(int id) {
		Student student = studentDao.getStudent(id);
		StudentDTO studentDto = new StudentDTO();
        if (student != null) {
            BeanUtils.copyProperties(student, studentDto);
            studentDto.setFirstName(student.getName().getFirstName());
            studentDto.setLastName(student.getName().getLastName());
        }
		return studentDto; 
	}

	@Transactional
	public int deleteStudent(int id) { 
		return studentDao.deleteStudent(id);
	}

	@Transactional
	public void updateStudent(StudentDTO studentDto) {
		Student updateStudent = studentDao.getStudent(studentDto.getId());
        BeanUtils.copyProperties(studentDto,updateStudent);
        Name name = new Name(studentDto.getFirstName(), studentDto.getLastName());
        updateStudent.setName(name);
		studentDao.updateStudent(updateStudent);
	}
    
	@Transactional
	public List<StudentDTO> getPassingStudents() {
		Iterator<Object[]> iterator = studentDao.getPassingStudents();
		List<StudentDTO> passingStudentsList = new ArrayList<StudentDTO>();
		while (iterator.hasNext()){
			Object[] row = iterator.next();
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

    @Transactional
    public List<StudentDTO> getSearchResults(String name) {
        List<Student> students = studentDao.getSearchResults(name);
        List<StudentDTO> studentDtos = new ArrayList<StudentDTO>();
        for (Student student:students) {
            StudentDTO studentDto = new StudentDTO();
            BeanUtils.copyProperties(student, studentDto);
            studentDto.setFirstName(student.getName().getFirstName());
            studentDto.setLastName(student.getName().getLastName());
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    public String dwrHelloWorld() {
        return "Hello World";
    }


	
}
