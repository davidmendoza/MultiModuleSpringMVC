package multiModuleSpringMVC.core.dao;

import junit.framework.TestCase;
import multiModuleSpringMVC.core.model.Student;
import org.junit.*;

import java.util.List;

public class TestStudentDaoImpl {


    @Test
    public void testAddStudent() {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        Assert.assertNull("Fail", List<Student>, studentDao.getStudentList(null));

    }

    /*@Test(timeout = 1000)
    public void infinity() {
        while (true);
    }*/

    @Test(expected = ArithmeticException.class)
    public void divide() {
        int i = 1/0;
    }



}
