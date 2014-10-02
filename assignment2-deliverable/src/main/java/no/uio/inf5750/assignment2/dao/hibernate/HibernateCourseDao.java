package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.model.Course;

public class HibernateCourseDao implements CourseDAO {

	@Autowired
	SessionFactory sessionFactory;
	
    /**
     * Persists a course. An unique id is generated if the object is persisted
     * for the first time, and which is both set in the given course object and
     * returned.
     * 
     * @param course the course to add for persistence.
     * @return the id of the course.
     */
	@Override
	public int saveCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		Integer courseID = (Integer) session.save(course);
		
		return courseID;
	}

    /**
     * Returns a course.
     * 
     * @param id the id of the course to return.
     * @return the course or null if it doesn't exist.
     */
	@Override
	public Course getCourse(int id) {	
		Session session = sessionFactory.getCurrentSession();
		Course course = (Course) session.get(Course.class, id);
		
		return course;
	}

    /**
     * Returns a course with a specific course code.
     * 
     * @param courseCode the course code of the course to return.
     * @return the course code or null if it doesn't exist.
     */
	@Override
	public Course getCourseByCourseCode(String courseCode) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Course.class);
		crit.add(Restrictions.eq("courseCode", courseCode));
		
		Course tmp;
		
		if(crit.list().size()>0)
		tmp = (Course) crit.list().get(0);
		
		else tmp = null;
		
		return tmp;
	}

    /**
     * Returns a course with a specific name.
     * 
     * @param courseCode the course code of the course to return.
     * @return the course code or null if it doesn't exist.
     */
	@Override
	public Course getCourseByName(String name) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Course.class);	
		criteria.add(Restrictions.eq("name", name));
		
		Course tmp;
		
		if(criteria.list().size()>0)
		tmp = (Course) criteria.list().get(0);
		
		else tmp = null;
		
		return tmp;
		
		//return (Course)criteria.list();
	}

    /**
     * Returns all courses.
     * 
     * @return all courses.
     */
	@Override
	public Collection<Course> getAllCourses() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Course";
				
		Query query = session.createQuery(hql);
		return query.list();
	}

    /**
     * Deletes a course.
     * 
     * @param course the course to delete.
     */
	@Override
	public void delCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(course);
	}

}
