package multiModuleSpringMVC.web.controller;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;


@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    private IndexController indexController = new IndexController();

    @Test
    public void testMainPage() {
        ModelAndView mav = indexController.indexPage();
        assertEquals("not same page", "homepage", mav.getViewName());
    }

    @Test
    public void testIndexPage() throws Exception {
        ModelAndView mav = indexController.mainPage();
        assertEquals("not same page", "homepage", mav.getViewName());
    }

    @Test
    public void testRedirectToControllerExample() {
        ModelAndView mav = indexController.redirectToControllerExample();
        assertEquals("not same page", "redirect:/student/search", mav.getViewName());
    }
}
