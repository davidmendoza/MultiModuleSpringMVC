package multiModuleSpringMVC.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import multiModuleSpringMVC.core.dto.PassingStudents;
import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.model.Student;
import multiModuleSpringMVC.core.service.StudentService;

@Controller
@RequestMapping(value="/student")
public class StudentController {
    
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/add")
	public String viewAddPage(Model model) {
		model.addAttribute("student", new StudentDTO());
		return "addStudent";
	}
	
	@RequestMapping(value="/process", method=RequestMethod.POST)
	public ModelAndView addOrEditStudent(@Valid @ModelAttribute("student") StudentDTO student, BindingResult result) {
	    ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
	        mav.addObject("message", "Errors encountered. Please fill up the form again.");
	        mav.setViewName("addStudent");
	        mav.addObject("student", student);
		} else {
		    if (student.getId() < 1) {
				studentService.addStudent(student);
		        mav.addObject("message", "New Student Added");
			} else {
				studentService.updateStudent(student);
		        mav.addObject("message", "Updated Student Details");
			}
		    mav.setViewName("viewStudents");
			mav.addObject("students", studentService.getStudentList());
		}
		
		return mav;
	}
	
	@RequestMapping(value="/view")
	public String viewStudents(Model model) {
		model.addAttribute("students", studentService.getStudentList());
		return "viewStudents";
	}
	
	@RequestMapping(value="/edit/{studentId}")
	public String viewEditPage(@PathVariable("studentId")int id, Model model) {
		StudentDTO student = studentService.getStudent(id);
		if (student != null) {
		    model.addAttribute("student", student);
		} else {
			model.addAttribute("student", new StudentDTO());
		}
		return "addStudent";
	}
	
	@RequestMapping(value="/delete/{studentId}")
	public String deleteStudent(@PathVariable("studentId")int id, Model model) {
		int isStudentDeleted = studentService.deleteStudent(id);
		if (isStudentDeleted == 1) {
		    model.addAttribute("message", "Deleted Student");
		} else {
		    model.addAttribute("message", "Student does not exist");
		}
		return viewStudents(model);
	}
	
	@RequestMapping(value="/passed")
	public ModelAndView viewPassingStudents() {
		ModelAndView mav = new ModelAndView();
		List<PassingStudents> passers = studentService.getPassingStudents();
		mav.setViewName("viewPassers");
		mav.addObject("passers", passers);
		return mav;
	}
	
}
