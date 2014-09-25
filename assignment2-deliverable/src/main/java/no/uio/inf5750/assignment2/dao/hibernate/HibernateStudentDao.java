package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Student;

public class HibernateStudentDao implements StudentDAO {

	@Autowired
	SessionFactory sf;
    /**
     * Persists a student. An unique id is generated if the object is persisted
     * for the first time, and which is both set in the given student object and
     * returned.
     * 
     * @param student the student to add for persistence.
     * @return the id of the student.
     */
	@Override
	public int saveStudent(Student student) {
		Session session = sf.getCurrentSession();
		Integer studentID = (Integer)session.save(student);
		
		return studentID;
	}

    /**
     * Returns a student.
     * 
     * @param id the id of the student to return.
     * @return the student or null if it doesn't exist.
     */
	@Override
	public Student getStudent(int id) {
		Session session = sf.getCurrentSession();
		Student student = (Student) session.get(Student.class, id);
		
		return student;
	}

    /**
     * Returns a student with a specific name.
     * 
     * @param name the name of the student to return.
     * @return the student or null if it doesn't exist.
     */
	@Override
	public Student getStudentByName(String name) {
		Session session = sf.getCurrentSession();
		
		Criteria crit = session.createCriteria(Student.class);
		crit.add(Restrictions.eq("name", name));
		
		return (Student)crit.list();
	}

    /**
     * Returns all students.
     * 
     * @return all students.
     */
	@Override
	public Collection<Student> getAllStudents() {
		Session session = sf.getCurrentSession();
		String hql = "From Student";
		
		Query q = session.createQuery(hql);
		return q.list();
	}

    /**
     * Deletes a student.
     * 
     * @param student the student to delete.
     */
	@Override
	public void delStudent(Student student) {
		Session session = sf.getCurrentSession();
		session.delete(student);

	}

}
