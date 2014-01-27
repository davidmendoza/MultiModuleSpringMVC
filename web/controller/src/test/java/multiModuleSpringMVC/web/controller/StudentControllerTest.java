package multiModuleSpringMVC.web.controller;

import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController controller;

    @Mock
    StudentDTO mockStudent;

    @Mock
    BindingResult mockResult;

    @Mock
    Model mockModel;

    @Mock
    ObjectError mockObjectError;

    @Test
    public void testViewAddPage()  {
        String testView = controller.viewAddPage(mockModel);
        assertEquals("addStudent", testView);
    }

    @Test
    public void testAddOrEditStudentWithBindingResultError()  {
        BindingResult testResult = new BeanPropertyBindingResult(mockStudent, "mockStudent");
        testResult.addError(mockObjectError);
        String addStudent = controller.addOrEditStudent(mockStudent, testResult, mockModel);
        assertEquals("addStudent", addStudent);
    }

    @Test
    public void testAddOrEditStudentWithPositiveId() {
        StudentDTO testStudent = new StudentDTO();
        testStudent.setId(1);
        String viewStudents = controller.addOrEditStudent(testStudent, mockResult, mockModel);
        assertEquals("viewStudents", viewStudents);
        verify(studentService, times(1)).updateStudent(testStudent);
    }

    @Test
    public void testAddOrEditStudentWithMockId() {
        String homepage = controller.addOrEditStudent(mockStudent, mockResult, mockModel);
        assertEquals("homepage", homepage);
        verify(studentService, times(1)).addStudent(mockStudent);
    }

    @Test
    public void testViewStudents()  {
        Model testModel = new ExtendedModelMap();
        String viewStudents = controller.viewStudents(1, testModel);
        assertTrue(testModel.containsAttribute("pageMap"));
        assertTrue(testModel.containsAttribute("students"));
        assertEquals("viewStudents", viewStudents);
    }

    @Test
    public void testViewEditPage()  {
        //if branch
        StudentDTO testStudent = new StudentDTO();
        when(studentService.getStudent(1)).thenReturn(testStudent);
        String addStudent = controller.viewEditPage(1, 1, mockModel);
        assertEquals(1, testStudent.getPageNo());
        assertEquals("addStudent", addStudent);

        //else branch
        Model testModel = new ExtendedModelMap();
        when(studentService.getStudent(1)).thenReturn(null);
        controller.viewEditPage(0, 1, testModel);
        assertTrue(!testModel.asMap().isEmpty());

        verify(studentService, times(1)).getStudent(1);
    }

    @Test
    public void testDeleteStudent()  {
        Model testModel = new ExtendedModelMap();

        //if branch
        when(studentService.deleteStudent(1)).thenReturn(1);
        controller.deleteStudent(1, 1, testModel);
        assertEquals("Deleted Student", testModel.asMap().get("message"));

        //else branch
        when(studentService.deleteStudent(0)).thenReturn(0);
        controller.deleteStudent(0, 1, testModel);
        assertEquals("Student does not exist", testModel.asMap().get("message"));
    }

    @Test
    public void testViewPassingStudents()  {
        ModelAndView testMav = controller.viewPassingStudents();
        assertTrue(testMav.getModel().containsKey("passers"));
        assertEquals("viewPassers", testMav.getViewName());
    }

    @Test
    public void testOpenSearchPage()  {
        assertEquals("searchStudent", controller.openSearchPage().getViewName());
    }

    @Test
    public void testViewSearchResults()  {
        List<StudentDTO> testList = new ArrayList<>();

        //if branch
        when(studentService.getSearchResults("emptyList")).thenReturn(testList);
        assertEquals("No students found", controller.viewSearchResults("emptyList").getModel().get("message"));

        //else branch
        testList.add(new StudentDTO());
        when(studentService.getSearchResults("list")).thenReturn(testList);
        ModelAndView testMav = controller.viewSearchResults("list");
        assertEquals(testList, testMav.getModel().get("students"));
        assertEquals("searchStudent", testMav.getViewName());
    }
}
