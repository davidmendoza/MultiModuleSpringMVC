package multiModuleSpringMVC.web.controller;

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

import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.model.Student;
import multiModuleSpringMVC.core.service.StudentService;

@Controller
public class AppController {
    
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/")
	public ModelAndView mainPage() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/index")
	public ModelAndView indexPage() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/student/add")
	public String viewAddPage(Model model) {
		model.addAttribute("student", new StudentDTO());
		return "addStudent";
	}
	
	@RequestMapping(value="/student/process", method=RequestMethod.POST)
	public ModelAndView addOrEditStudent(@Valid @ModelAttribute("student") StudentDTO student, BindingResult result) {
	    ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
	        mav.addObject("message", "Errors encountered. Please fill up the form again.");
		} else {
		    if (student.getId() < 1) {
				studentService.addStudent(student);
		        mav.addObject("message", "New Student Added");
		        mav.addObject("student",new StudentDTO());
			} else {
				studentService.updateStudent(student);
		        mav.addObject("message", "Updated Student Details");
		        mav.addObject("student", student);
			}
		}
		mav.setViewName("addStudent");
		return mav;
	}
	
	@RequestMapping(value="/student/view")
	public String viewStudents(Model model) {
		model.addAttribute("students", studentService.getStudentList());
		return "viewStudents";
	}
	
	@RequestMapping(value="/student/edit/{studentId}")
	public String viewEditPage(@PathVariable("studentId")int id, Model model) {
		StudentDTO student = studentService.getStudent(id);
		if (student != null) {
		    model.addAttribute("student", student);
		} else {
			model.addAttribute("student", new StudentDTO());
		}
		return "addStudent";
	}
	
	@RequestMapping(value="/student/delete/{studentId}")
	public String deleteStudent(@PathVariable("studentId")int id, Model model) {
		int isStudentDeleted = studentService.deleteStudent(id);
		if (isStudentDeleted == 1) {
		    model.addAttribute("message", "Deleted Student");
		} else {
		    model.addAttribute("message", "Student does not exist");
		}
		return viewStudents(model);
	}
	
	@RequestMapping(value="/student/grades")
	public String viewGrades(Model model) {
	    model.addAttribute("students", studentService.getStudentList());
	    model.addAttribute("student", new Student());
	    return "viewGrades";
	}
	
	@RequestMapping(value="/student/grades/process", method=RequestMethod.POST)
	public String saveGrades(Student student, BindingResult result, Model model) {
		
		if (result.hasErrors()){
			model.addAttribute("students", studentService.getStudentList());
		    model.addAttribute("student", new Student());
			model.addAttribute("message","Please enter a valid grade");
			return "viewGrades";
		} 
		
		studentService.saveGrades(student);
		model.addAttribute("message", "Saved Grades!");
		return "index";
		//use ModelAndView object as return type
	}
	
	
}
