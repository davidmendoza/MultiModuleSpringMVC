package multiModuleSpringMVC.web.controller;

import java.util.List;

import javax.validation.Valid;

import multiModuleSpringMVC.core.model.Subject;
import multiModuleSpringMVC.core.service.StudentService;
import multiModuleSpringMVC.core.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/subject")
public class SubjectController {
    
    @Autowired
    SubjectService subjService;
    
    @Autowired
    StudentService studentService;
    
    @RequestMapping(value="manage/{studentId}")
    public ModelAndView openSubjectPage(@PathVariable("studentId")int id, ModelAndView mav) {
    	List<Subject> subjList = subjService.getSubjectList(id);
    	mav.addObject("subjectId", id);
    	mav.addObject("subjects", subjList);
    	mav.addObject("gpa", subjService.getGPA(id));
    	mav.addObject("subject", new Subject());
    	mav.setViewName("manageSubjects");
    	return mav;
    }
    
    @RequestMapping(value="delete/{studentId}-{subjectId}")
    public ModelAndView deleteSubject(@PathVariable("studentId")int studentId, @PathVariable("subjectId")int subjId) {
    	ModelAndView mav = new ModelAndView();
    	subjService.deleteSubject(subjId);
    	mav.addObject("message", "Deleted Subject");
    	return openSubjectPage(studentId, mav);
    }

    @RequestMapping(value="process/{studentId}", method=RequestMethod.POST)
    public ModelAndView addSubjectAndGrade(@PathVariable("studentId")int id, @Valid @ModelAttribute("subject") Subject subject,
    	   BindingResult result) {
    	ModelAndView mav = new ModelAndView();
    	if (result.hasErrors()) {
    		mav.addObject("message", "Grade should be between 65-99 and Fields should not be blank");
    	} else {
    	    subjService.addSubject(id, subject);
    	    mav.addObject("message", "Recorded new subject");
    	}
    	return openSubjectPage(id, mav);
    }
    
    
}
