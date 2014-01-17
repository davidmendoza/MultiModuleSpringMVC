package multiModuleSpringMVC.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import multiModuleSpringMVC.core.model.Student;
import multiModuleSpringMVC.core.model.Subject;
import multiModuleSpringMVC.core.model.TransTable;

@Repository
public class SubjectDaoImpl implements SubjectDao {

	@Autowired	
	private SessionFactory sessionFactory;
	
	public void addSubject(int id, Subject subject) {
		System.out.println("INSIDE ADD SUBJECT...");
		Session session = sessionFactory.getCurrentSession();
		subject.setStudent((Student)session.load(Student.class, id));
		session.save(subject);
	}

	public List<Subject> getSubjectList(int id) {
		System.out.println("INSIDE GET SUBJECT LIST...");
		String hql = "SELECT sbj from Student st JOIN st.subjects sbj WHERE st.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger("id", id);
		return (List<Subject>)query.list();
	}

	public void updateSubject(Subject subject) {
		
	}

	public Map<String, Object> getGPA(int id) {
		System.out.println("INSIDE GET GPA...");
		Map<String, Object> gpaMap = new HashMap<String, Object>();
		Integer ave = null;
		Character gpa = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Subject.class)
				.createAlias("student", "student")
			    .setProjection(Projections.projectionList()
			        .add(Projections.avg("grade")))
			    .add(Restrictions.eq("student.id", id));
		List<Double> raw = crit.list();
		
		if (raw.get(0) != null) {
		    ave = (int)Math.round((double)raw.get(0));
		    Criteria gpaCrit = session.createCriteria(TransTable.class)
			        .setProjection(Projections.property("equivalence"))
			        .add(Restrictions.and(
			            Restrictions.ge("upperLimit", ave),
			            Restrictions.le("lowerLimit", ave)));
		    gpa = (Character)gpaCrit.list().get(0);
		}
		gpaMap.put("Average", ave);
		gpaMap.put("GPA", gpa);
		return gpaMap;
	}

    public static void printStatistics(SessionFactory sf) {
    	System.out.println("PRINTING STATISTICS...");
    	System.out.println("ENTITY LOAD COUNT: "+sf.getStatistics().getEntityLoadCount());
    	System.out.println("ENTITY FETCH COUNT: "+sf.getStatistics().getEntityFetchCount());
    	System.out.println("ENTITY INSERT COUNT: "+sf.getStatistics().getEntityInsertCount());
    	System.out.println("ENTITY DELETE COUNT: "+sf.getStatistics().getEntityDeleteCount());
    	System.out.println("2ND LEVEL CACHE HIT COUNT: "+sf.getStatistics().getSecondLevelCacheHitCount());
    	System.out.println("QUERY CACHE HIT COUNT: "+sf.getStatistics().getQueryCacheHitCount());
    }
}
