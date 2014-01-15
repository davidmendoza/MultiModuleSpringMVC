package multiModuleSpringMVC.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import multiModuleSpringMVC.core.dao.SubjectDao;
import multiModuleSpringMVC.core.model.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	SubjectDao subjectDao;
	
	@Transactional
	public void addSubject(int id, Subject subject) {
		subjectDao.addSubject(id, subject);

	}
	
	@Transactional
	public List<Subject> getSubjectList(int id) {
		return subjectDao.getSubjectList(id);
	}

	public void updateSubject(Subject subject) {
		// TODO Auto-generated method stub

	}
	
	@Transactional
	public Map<String, Object> getGPA(int id) {
		return subjectDao.getGPA(id);
	}

}
