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
	public String addOrEditStudent(@Valid @ModelAttribute("student") StudentDTO student, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("message", "Errors encountered. Please fill up the form again.");
		} else {
		    if (student.getId() < 1) {
				studentService.addStudent(student);
				model.addAttribute("student", new StudentDTO());
		        model.addAttribute("message", "New Student Added");
			} else {
				System.out.println("Error is BELOW!!!!!!!!");
				studentService.updateStudent(student);
		        model.addAttribute("message", "Updated Student Details");
			}
		}
		return "addStudent";
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
		Boolean isStudentDeleted = studentService.deleteStudent(id);
		if (isStudentDeleted == true) {
		    model.addAttribute("message", "Deleted Student");
		    model.addAttribute("students", studentService.getStudentList());
		} else {
		    model.addAttribute("message", "Student does not exist");
		    model.addAttribute("students", studentService.getStudentList());
		}
		return "viewStudents";
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
