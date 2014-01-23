package multiModuleSpringMVC.core.service;

import multiModuleSpringMVC.core.dao.StudentDao;
import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class StudentServiceImplTest {


    private StudentDao studentDao;

    private StudentServiceImpl studentService;

    @Before
    public void setUp() throws Exception {
        studentDao = mock(StudentDao.class);
        studentService = new StudentServiceImpl(studentDao); //inject mock studentDao to studentService
    }

    @Test
    public void testAddStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentService.addStudent(studentDTO);
        Mockito.verify(studentDao).addStudent(Mockito.<Student>any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddStudentIfNull() throws Exception {
        StudentDTO studentDTO = null;
        studentService.addStudent(studentDTO);
        verify(studentDao, times(1)).addStudent(any(Student.class));
    }

    @Test
    public void testGetStudentList() throws Exception {
        List<StudentDTO> studentList = studentService.getStudentList(anyInt());
        Assert.assertNotNull(studentList);
        Assert.assertEquals(0, studentList.size());
        verify(studentDao, times(1)).getStudentList(anyInt());
    }

    @Test
    public void testGetStudent() throws Exception {
        StudentDTO studentDTO = studentService.getStudent(1);
        Assert.assertNotNull(studentDTO);
        verify(studentDao, times(1)).getStudent(1);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        int id = studentService.deleteStudent(anyInt());
        Assert.assertNotNull(id);
        verify(studentDao, times(1)).deleteStudent(anyInt());

    }

    @Test
    public void testUpdateStudent() throws Exception {

    }

    @Test
    public void testGetPassingStudents() throws Exception {

    }

    @Test
    public void testGetSearchResults() throws Exception {

    }

    @Test
    public void testGetPropertyList() throws Exception {

    }
}
