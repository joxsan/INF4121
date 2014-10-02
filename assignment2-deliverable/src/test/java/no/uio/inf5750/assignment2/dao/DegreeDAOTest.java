package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;
import no.uio.inf5750.assignment2.dao.hibernate.HibernateDegreeDao;
import no.uio.inf5750.assignment2.model.Degree;

import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
public class DegreeDAOTest {

	Degree degree1, degree2, degree3, degreetmp;
	String type1, type2, type3;
	
	@Autowired
	DegreeDAO degreeDAO;
	
	@Before
	public void init(){
		
		degree1 = new Degree("Programmering og nettverk");
		degree2 = new Degree("Design, bruk og interaksjon");
		degree3 = new Degree("Språk og kommunikasjon");
		
		type1 = "Programmering og nettverk";
		type2 = "Design, bruk og interaksjon";
		type3 = "Språk og kommunikasjon";
	
	}
	
	@Test
	public void test_saveDegree(){
		
		int degreeID = degreeDAO.saveDegree(degree1);
		degree1 = degreeDAO.getDegree(degreeID);
		
		assertEquals(degreeID, degree1.getId());
		
	}
	
	@Test
	public void test_getDegree(){
		int degreeID = degreeDAO.saveDegree(degree1);
		degreetmp = degreeDAO.getDegree(degreeID);
		assertNotNull(degreetmp);
		degreetmp = degreeDAO.getDegree(-1);
		assertNull(degreetmp);
	}
	
	@Test
	public void test_getDegreeByType(){
		degreeDAO.saveDegree(degree1);
		degreetmp = degreeDAO.getDegreeByType(type1);
		assertNotNull(degreetmp);
		assertEquals(degreetmp, degree1);
		assertNotSame(degreetmp, degree2);
		degreetmp = degreeDAO.getDegreeByType("Hacking og nettverk");
		assertNull(degreetmp);
	}
	
	@Test 
	public void test_getAllDegrees(){
		Collection<Degree> degrees = (Collection<Degree>) degreeDAO.getAllDegrees();
		
	}
	
	@Test
	public void test_delDegree(){
		degreeDAO.delDegree(degree1);
		degreetmp = degreeDAO.getDegreeByType(type1);
		assertNull(degreetmp);
		
	
	}
	
	
	
}
