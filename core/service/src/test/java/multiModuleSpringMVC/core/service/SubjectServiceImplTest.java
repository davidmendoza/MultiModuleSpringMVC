package multiModuleSpringMVC.core.service;

import multiModuleSpringMVC.core.dao.SubjectDao;
import multiModuleSpringMVC.core.model.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceImplTest {

    @Mock
    private SubjectDao subjectDao;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @Test
    public void testAddSubject() {
        Subject mockSubject = mock(Subject.class);
        subjectService.addSubject(1, mockSubject);
        when(mockSubject.getName()).thenReturn("Math");
        verify(subjectDao, times(1)).addSubject(1, mockSubject);
    }

    @Test
    public void testGetSubjectList() throws Exception {
        subjectService.getSubjectList(anyInt());
        verify(subjectDao, times(1)).getSubjectList(anyInt());
    }

    @Test
    public void testGetGPA() throws Exception {
        subjectService.getGPA(anyInt());
        verify(subjectDao, times(1)).getGPA(anyInt());
    }

    @Test
    public void testDeleteSubject() throws Exception {
        subjectService.deleteSubject(1);
        verify(subjectDao, times(1)).deleteSubject(1);
    }
}
