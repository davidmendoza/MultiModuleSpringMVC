package multiModuleSpringMVC.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import multiModuleSpringMVC.core.dto.StudentDTO;
import multiModuleSpringMVC.core.service.StudentService;

@Controller
@RequestMapping(value="/student")
public class StudentController {
    
	private static final int maxResult = 5;
	
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
		        mav.addObject("students", studentService.getStudentList(0));
			} else {
				studentService.updateStudent(student);
		        mav.addObject("message", "Updated Student Details");
		        mav.addObject("students", studentService.getStudentList(student.getPageNo()-1));
			}
		    mav.setViewName("viewStudents");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String viewStudents(@RequestParam(value="page", defaultValue="1")int page, Model model) {
		List<StudentDTO> studentList = studentService.getStudentList(page);
	    model.addAttribute("students", studentList);
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("nextPage", page);
		pageMap.put("prevPage", 0);
		
		if (studentList.size() > maxResult-1) {
		    pageMap.put("nextPage", page+1);
		} 
		if (page > 0) {
		    pageMap.put("prevPage", page-1);
		}
		model.addAttribute("pageMap", pageMap);
		return "viewStudents";
	}
	
	@RequestMapping(value="/edit/{page}-{studentId}")
	public String viewEditPage(@PathVariable("studentId") int id, @PathVariable("page") int page, Model model) {
		StudentDTO student = studentService.getStudent(id);
		if (student != null) {
			student.setPageNo(page);
		    model.addAttribute("student", student);
		} else {
			model.addAttribute("student", new StudentDTO());
		}
		return "addStudent";
	}
	
	@RequestMapping(value="/delete/{page}-{studentId}")
	public String deleteStudent(@PathVariable("studentId")int id, @PathVariable("page")int page, Model model) {
		int isStudentDeleted = studentService.deleteStudent(id);
		if (isStudentDeleted == 1) {
		    model.addAttribute("message", "Deleted Student");
		} else {
		    model.addAttribute("message", "Student does not exist");
		}
		return viewStudents(page, model);
	}
	
	@RequestMapping(value="/passed")
	public ModelAndView viewPassingStudents() {
		ModelAndView mav = new ModelAndView();
		List<StudentDTO> passers = studentService.getPassingStudents();
		mav.setViewName("viewPassers");
		mav.addObject("passers", passers);
		return mav;
	}
	
}
