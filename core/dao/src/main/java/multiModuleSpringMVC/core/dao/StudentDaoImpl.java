package multiModuleSpringMVC.core.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import multiModuleSpringMVC.core.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	
	public static final int MAX_RESULTS = 5;
	private static final int PASSING_GRADE = 75;
	
    @Autowired	
	private SessionFactory sessionFactory;

	public void addStudent(Student student) {
		System.out.println("INSIDE ADD STUDENT...");
        student.setCreateTime(new Date());
		sessionFactory.getCurrentSession().save(student);
		SubjectDaoImpl.printStatistics(sessionFactory);
	}
	
	public void updateStudent(Student student) {
		System.out.println("INSIDE UPDATE STUDENT...");
        student.setUpdateTime(new Date());
		sessionFactory.getCurrentSession().update(student);
		SubjectDaoImpl.printStatistics(sessionFactory);
	}
	
	public List<Student> getStudentList(int page) {
		System.out.println("INSIDE GET STUDENT LIST...");
		Query query = sessionFactory.getCurrentSession().createQuery("from Student")
		    //.setCacheable(true)
		    .setFirstResult(page * MAX_RESULTS)
		    .setMaxResults(MAX_RESULTS);
		List<Student> students = query.list();
		return students;
	}

	public Student getStudent(int id) {
		System.out.println("INSIDE GET STUDENT BY ID...");
		Student st = (Student)sessionFactory.getCurrentSession().get(Student.class, id);
		//SubjectDaoImpl.printStatistics(sessionFactory);
		return st;
	}

	public int deleteStudent(int id) {
		System.out.println("INSIDE DELETE STUDENT BY ID...");
		Session session = sessionFactory.getCurrentSession();
		int delete = 0;
		Student st = (Student) session.get(Student.class, id);
		if (st != null) {
			session.delete(st);
			delete = 1;
		}
		//SubjectDaoImpl.printStatistics(sessionFactory);
		return delete;
	}

	public Iterator<Object[]> getPassingStudents() {
		System.out.println("INSIDE GET PASSING STUDENTS...");
		Session session = sessionFactory.getCurrentSession();
		String hql = ("SELECT st.id, st.name.firstName, st.name.lastName, st.level, AVG(subj.grade) FROM Student st JOIN "
				+ "st.subjects subj WHERE st.status = 'Y' GROUP BY st.id HAVING AVG(subj.grade) >= "+PASSING_GRADE
				+ " ORDER BY st.level asc, AVG(subj.grade) desc");
		Iterator<Object[]> iterator = session.createQuery(hql).list().iterator();
		return iterator;
	}

    public List<Student> getSearchResults(String name) {
        System.out.println("INSIDE SEARCH STUDENTS...");
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Student.class)
                .add(Restrictions.or(
                    Restrictions.ilike("name.firstName", name+"%"),
                    Restrictions.ilike("name.lastName", name+"%")
                ));
        return (List<Student>)crit.list();
    }




}
