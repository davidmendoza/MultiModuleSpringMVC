package multiModuleSpringMVC.web.controller;

import multiModuleSpringMVC.core.model.Subject;
import multiModuleSpringMVC.core.service.StudentService;
import multiModuleSpringMVC.core.service.SubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SubjectControllerTest {

    @Mock
    StudentService studentService;

    @Mock
    SubjectService subjectService;

    @InjectMocks
    SubjectController subjectController;

    @Test
    public void testOpenSubjectPage() {
        ModelAndView testMav = subjectController.openSubjectPage(anyInt(), new ModelAndView());
        assertTrue("no key subjectId", testMav.getModel().containsKey("subjectId"));
        assertTrue("no key subjects", testMav.getModel().containsKey("subjects"));
        assertTrue("no key gpa", testMav.getModel().containsKey("gpa"));
        assertTrue("no key subject", testMav.getModel().containsKey("subject"));
        assertEquals("not same view name", "manageSubjects", testMav.getViewName());
    }

    @Test
    public void testDeleteSubject() {
        ModelAndView testMav = subjectController.deleteSubject(1, 1);
        verify(subjectService, times(1)).deleteSubject(1);
        assertTrue(testMav.getModel().containsKey("message"));
        assertTrue(testMav.getModel().containsKey("subject"));
    }

    @Test
    public void testAddSubjectAndGrade() {
        BindingResult mockResult = mock(BindingResult.class);
        Subject mockSubj = mock(Subject.class);

        //test else path
        ModelAndView testMav = subjectController.addSubjectAndGrade(1, mockSubj, mockResult);
        verify(subjectService, times(1)).addSubject(1, mockSubj);
        assertEquals("Recorded new subject", testMav.getModel().get("message"));

        //test if path
        BindingResult testResult = new BeanPropertyBindingResult(mockSubj, "mockSubj");
        testResult.addError(mock(ObjectError.class));
        ModelAndView newTestMav = subjectController.addSubjectAndGrade(1, mockSubj, testResult);
        assertEquals("Grade should be between 65-99 and Fields should not be blank", newTestMav.getModel().get("message"));
    }
}
