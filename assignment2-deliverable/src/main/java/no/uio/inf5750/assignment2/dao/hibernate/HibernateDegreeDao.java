package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import no.uio.inf5750.assignment2.dao.DegreeDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;

public class HibernateDegreeDao implements DegreeDAO {

	@Autowired
	SessionFactory sf;
	
    /**
     * Persists a degree. An unique id is generated if the object is persisted
     * for the first time, and which is both set in the given degree object and
     * returned.
     * 
     * @param degree the degree to add for persistence.
     * @return the id of the degree.
     */
	@Override
	public int saveDegree(Degree degree) {
		
		Session session = sf.getCurrentSession();
		Integer degreeID = (Integer) session.save(degree);
		
		return degreeID;
	}

    /**
     * Returns a degree.
     * 
     * @param id the id of the degree to return.
     * @return the degree or null if it doesn't exist.
     */
	@Override
	public Degree getDegree(int id) {
		Session session = sf.getCurrentSession();
		Degree degree = (Degree) session.get(Degree.class, id);
		
		return degree;
	}

    /**
     * Returns a degree with a specific type.
     * 
     * @param type the type of the degree to return.
     * @return the degree or null if it doesn't exist.
     */
	@Override
	public Degree getDegreeByType(String type) {
		Session session = sf.getCurrentSession();
		Criteria crit = session.createCriteria(Degree.class);
		crit.add(Restrictions.eq("type", type));
		
		return (Degree)crit.list();
	}

    /**
     * Returns all degrees.
     * 
     * @return all degrees.
     */
	@Override
	public Collection<Degree> getAllDegrees() {
		Session session = sf.getCurrentSession();
		String hql = "From Degree";
				
		Query query = session.createQuery(hql);
		return query.list();
	}

    /**
     * Deletes a degree.
     * 
     * @param degree the degree to delete.
     */
	@Override
	public void delDegree(Degree degree) {
		Session session = sf.getCurrentSession();
		session.delete(degree);
	}

}
