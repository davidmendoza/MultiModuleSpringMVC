package multiModuleSpringMVC.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import multiModuleSpringMVC.core.dao.SubjectDao;
import multiModuleSpringMVC.core.model.Subject;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	SubjectDao subjectDao;
	
	public void addSubject(int id, Subject subject) {
		subjectDao.addSubject(id, subject);
	}
	
	public List<Subject> getSubjectList(int id) {
		return subjectDao.getSubjectList(id);
	}
	
	public Map<String, Object> getGPA(int id) {
		return subjectDao.getGPA(id);
	}
	
	public void deleteSubject(int id) {
		subjectDao.deleteSubject(id);
	}

}
