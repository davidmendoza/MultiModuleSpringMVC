package multiModuleSpringMVC.core.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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
		List<String> props = getPropertyList();
		copyBeanProperties(studentDto, student, props);
		studentDao.addStudent(student);
	}
	
	@Transactional
	public List<StudentDTO> getStudentList(int page) {
		List<Student> students = studentDao.getStudentList(page);
		List<StudentDTO> studentDtos = new ArrayList<StudentDTO>();
		List<String> props = getPropertyList();
		for (Student student:students) {
			StudentDTO studentDto = new StudentDTO();
			copyBeanProperties(student, studentDto, props);
			studentDtos.add(studentDto);
		}
		return studentDtos;
	}
	
	@Transactional
	public StudentDTO getStudent(int id) {
		Student student = studentDao.getStudent(id);
		StudentDTO studentDto = new StudentDTO();
		List<String> props = getPropertyList();
		copyBeanProperties(student, studentDto, props);
		return studentDto; 
	}

	@Transactional
	public int deleteStudent(int id) { 
		return studentDao.deleteStudent(id);
	}

	@Transactional
	public void updateStudent(StudentDTO studentDto) {
		Student updateStudent = studentDao.getStudent(studentDto.getId());
		List<String> props = getPropertyList();
		copyBeanProperties(studentDto, updateStudent, props);
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
    
    public static void copyBeanProperties(
	    final Object source,
	    final Object target,
	    final Iterable<String> properties){

	    final BeanWrapper src = new BeanWrapperImpl(source);
	    final BeanWrapper trg = new BeanWrapperImpl(target);

	    for(final String propertyName : properties){
	        trg.setPropertyValue(
	            propertyName,
	            src.getPropertyValue(propertyName)
	        );
	    }
    }
    
    public static List<String> getPropertyList() {
    	List<String> props = new ArrayList<String>();
    	props.add("id");
		props.add("firstName");
		props.add("lastName");
		props.add("gender");
		props.add("level");
		props.add("status");
		return props;
    }


	
}
