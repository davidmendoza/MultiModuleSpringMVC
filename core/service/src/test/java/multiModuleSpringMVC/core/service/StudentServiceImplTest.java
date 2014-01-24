package multiModuleSpringMVC.core.service;

import junit.framework.TestCase;
import multiModuleSpringMVC.core.dao.StudentDao;
import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testAddStudent() {
        StudentDTO studentDTO = mock(StudentDTO.class);
        when(studentDTO.getFirstName()).thenReturn("David");
        studentService.addStudent(new StudentDTO()); // diff test to go inside if statement
        verify(studentDao, times(1)).addStudent(Mockito.<Student>any());
    }

    @Test
    public void testAddStudentWithNull() {
        studentService.addStudent(null);
        Mockito.verifyZeroInteractions(studentDao);
    }

    @Test
    public void testGetStudentList() {
        List<Student> list = new ArrayList<>();
        Student test = new Student();
        test.setLastName("perez");
        list.add(test);
        when(studentDao.getStudentList(0)).thenReturn(list);
        List<StudentDTO> studentList = studentService.getStudentList(anyInt());
        assertNotNull(studentList);
        assertEquals("Different last name", "perez", studentList.get(0).getLastName());
        verify(studentDao, times(1)).getStudentList(anyInt());
    }

    @Test
    public void testGetStudent() {
        Student student = new Student();
        student.setId(1);
        when(studentDao.getStudent(1)).thenReturn(student);
        StudentDTO studentDTO = studentService.getStudent(1);
        assertNotNull(studentDTO);
        assertEquals("Not same ID", 1, studentDTO.getId());
        verify(studentDao, times(1)).getStudent(1);
    }

    @Test
    public void testDeleteStudent() {
        when(studentDao.deleteStudent(1)).thenReturn(1);
        int id = studentService.deleteStudent(1);
        assertNotNull(id);
        assertEquals("not same id", 1, id);
        verify(studentDao, times(1)).deleteStudent(1);
    }

    @Test
    public void testUpdateStudent() {
        StudentDTO studentToUpdate = new StudentDTO();
        studentToUpdate.setFirstName("david");
        studentToUpdate.setId(1);
        Student studentFromDb = new Student();
        studentFromDb.setId(1);
        studentFromDb.setFirstName("pedro");
        when(studentDao.getStudent(1)).thenReturn(studentFromDb);
        studentService.updateStudent(studentToUpdate);
        assertEquals("Update student failed, not same name", studentFromDb.getFirstName(), studentToUpdate.getFirstName());
        verify(studentDao, times(1)).getStudent(1);
    }

    @Test
    public void testGetPassingStudents() {
        List<Object[]> testList = new ArrayList<>();
        Object[] sampleStudent = new Object[10];
        sampleStudent[0] = 1;
        sampleStudent[1] = "David";
        sampleStudent[2] = "Mendoza";
        sampleStudent[3] = 2;
        sampleStudent[4] = 89.5;
        testList.add(sampleStudent);
        Iterator<Object[]> fakeIter = testList.iterator();
        when(studentDao.getPassingStudents()).thenReturn(fakeIter);
        List<StudentDTO> passingStudents = studentService.getPassingStudents();
        assertEquals("Name is different", sampleStudent[1], passingStudents.get(0).getFirstName());
        assertEquals("Double to int value not satisfied", 90, passingStudents.get(0).getAverage());
        verify(studentDao, times(1)).getPassingStudents();
    }

    @Test
    public void testGetSearchResults() {
        Student david = new Student();
        david.setFirstName("david");
        List<Student> list = new ArrayList<>();
        list.add(david);
        when(studentDao.getSearchResults("david")).thenReturn(list);
        List<StudentDTO> results = studentService.getSearchResults("david");
        assertEquals("Not Same String", "david", results.get(0).getFirstName());
        assertNotEquals("Same String", "bar", results.get(0).getFirstName());
        verify(studentDao, times(1)).getSearchResults("david");
    }

}
