package multiModuleSpringMVC.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public ModelAndView mainPage() {
		return new ModelAndView("homepage");
	}
	
	@RequestMapping(value="/home")
	public ModelAndView indexPage() {
		return new ModelAndView("homepage");
	}

    @RequestMapping(value="/redirectExample")
    public ModelAndView redirectToControllerExample() {
        return new ModelAndView("redirect:/student/search");
    }
}
