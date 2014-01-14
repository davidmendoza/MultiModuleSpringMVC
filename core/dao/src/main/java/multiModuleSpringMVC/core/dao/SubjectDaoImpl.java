package multiModuleSpringMVC.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import multiModuleSpringMVC.core.model.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {

	@Autowired	
	private SessionFactory sessionFactory;
	
	public void addSubject(int id, Subject subject) {
		String sql = "INSERT INTO SUBJECT(GRADE, NAME, STUDENT_ID) VALUES(:grade, :name, :id)";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id", id);
		query.setString("name", subject.getName());
		query.setInteger("grade", subject.getGrade());
		query.executeUpdate();
	}

	public List<Subject> getSubjectList(int id) {
		String hql = "SELECT sbj from Student st JOIN st.subjects sbj WHERE st.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger("id", id);
		return (List<Subject>)query.list();
	}

	public void updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		
	}

    
}
