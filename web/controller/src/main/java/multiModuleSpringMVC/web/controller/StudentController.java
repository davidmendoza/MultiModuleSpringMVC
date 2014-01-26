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

import multiModuleSpringMVC.core.dao.StudentDaoImpl;
import multiModuleSpringMVC.core.dto.StudentDTO;
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
	public String addOrEditStudent(@Valid @ModelAttribute("student") StudentDTO student, BindingResult result, Model model) {
		String view = null;
		if (result.hasErrors()) {
			model.addAttribute("message", "Errors encountered. Please fill up the form again.Please make sure birthday is in the correct format (dd/MM/YYYY)");
			model.addAttribute("student", student);
			view = "addStudent";
		} else {
		    if (student.getId() < 1) {
				studentService.addStudent(student);
		        model.addAttribute("message", "Added New Student");
		        view ="homepage";
			} else {
				studentService.updateStudent(student);
				model.addAttribute("message", "Updated Student Details");
				view = viewStudents(student.getPageNo(), model);
			}
		}
		return view;
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String viewStudents(@RequestParam(value="page")int page, Model model) {
		List<StudentDTO> studentList = studentService.getStudentList(page);
	    model.addAttribute("students", studentList);
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("nextPage", page+1);
		pageMap.put("prevPage", page-1);
		pageMap.put("currPage", page);
		pageMap.put("totalResult", studentList.size());
		pageMap.put("maxResults", StudentDaoImpl.MAX_RESULTS);
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

    @RequestMapping(value="/search")
    public ModelAndView openSearchPage() {
       return new ModelAndView("searchStudent");
    }

    @RequestMapping(value="/search/process")
    public ModelAndView viewSearchResults(@RequestParam("name") String name) {
        ModelAndView mav = new ModelAndView("searchStudent");
        List<StudentDTO> searchList = studentService.getSearchResults(name);
        if (searchList.isEmpty()) {
            mav.addObject("message", "No students found");
        } else {
            mav.addObject("students", searchList);
            mav.addObject("message", "Success!");
        }
        return mav;
    }


	
}
