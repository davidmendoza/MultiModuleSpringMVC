package multiModuleSpringMVC.core.dao;

import java.util.List;
import java.util.Map;

import multiModuleSpringMVC.core.model.Subject;

public interface SubjectDao {

    public void addSubject(int id, Subject subject);
    
    public List<Subject> getSubjectList(int id);
    
    public Map<String, Object>getGPA(int id);
    
    public void deleteSubject(int id);
}
