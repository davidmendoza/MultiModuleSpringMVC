package multiModuleSpringMVC.core.service;

import java.util.List;
import java.util.Map;

import multiModuleSpringMVC.core.model.Subject;

public interface SubjectService {

    public void addSubject(int id, Subject subject);
    
    public List<Subject> getSubjectList(int id);
    
    public void updateSubject(Subject subject);

    public Map<String, Object>getGPA(int id);
	
}
