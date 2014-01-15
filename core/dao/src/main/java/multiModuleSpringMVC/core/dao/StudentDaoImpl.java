package multiModuleSpringMVC.core.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import multiModuleSpringMVC.core.dto.PassingStudents;
import multiModuleSpringMVC.core.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	
    @Autowired	
	private SessionFactory sessionFactory;

	public void addStudent(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}
	
	public void updateStudent(Student student) {
		sessionFactory.getCurrentSession().update(student);
	}
	
	public List<Student> getStudentList() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Student");
		return (List<Student>)query.list();
	}

	public Student getStudent(int id) {
		return (Student) sessionFactory.getCurrentSession().get(Student.class, id);
	}

	public int deleteStudent(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createSQLQuery("delete from SUBJECT where STUDENT_ID = "+id);
		query1.executeUpdate(); 
		Query query = session.createQuery("delete from Student where id = :id");
		query.setInteger("id", id);
		return query.executeUpdate();
	}

	public Iterator<Object[]> getPassingStudents() {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = ("SELECT st.id, st.firstName, st.lastName, st.level, AVG(subj.grade) FROM Student st JOIN "
				+ "st.subjects subj WHERE st.status = 'Y' GROUP BY st.id HAVING AVG(subj.grade) >= 75"
				+ " ORDER BY st.level asc, AVG(subj.grade) desc");
		Iterator<Object[]> iterator = session.createQuery(hql).list().iterator();
		return iterator;
	}
	
}
