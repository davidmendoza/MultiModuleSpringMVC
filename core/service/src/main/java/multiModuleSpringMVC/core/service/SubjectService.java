package multiModuleSpringMVC.core.service;

import java.util.List;
import java.util.Map;

import multiModuleSpringMVC.core.model.Subject;

public interface SubjectService {

    public void addSubject(int id, Subject subject);
    
    public List<Subject> getSubjectList(int id);

    public Map<String, Object>getGPA(int id);
    
    public void deleteSubject(int id);
	
}
