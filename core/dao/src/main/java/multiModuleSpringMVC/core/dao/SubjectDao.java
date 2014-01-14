package multiModuleSpringMVC.core.dao;

import java.util.List;

import multiModuleSpringMVC.core.model.Subject;

public interface SubjectDao {

    public void addSubject(int id, Subject subject);
    
    public List<Subject> getSubjectList(int id);
    
    public void updateSubject(Subject subject);
}
