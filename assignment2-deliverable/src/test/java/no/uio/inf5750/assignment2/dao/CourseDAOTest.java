package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import org.hibernate.mapping.Collection;
import org.junit.Before;
import org.junit.Test;

import no.uio.inf5750.assignment2.model.Course;

public class CourseDAOTest {
	
	Course course1, course2, course3, coursetmp;
	String name1, name2, name3;
	CourseDAO courseDAO;
	
	@Before 
	public void init(){
	
	course1 = new Course("INF5150", "Uangripelige IT-systemer");
	course2 = new Course("UNIK4270", "Sikkerhet i OS og SW");
	course3 = new Course("INF5750", "Open-source frameworks");
	
	name1 = "Uangripelige IT-systemer";
	name2 = "Sikkerhet i OS og SW";
	name3 = "Open-source frameworks";
		
	}
	
	@Test
	public void test_saveCourse(){
		
	int courseID = courseDAO.saveCourse(course1);
	course1 = courseDAO.getCourse(courseID);
	int courseID2 = courseDAO.saveCourse(course2);
	course2 = courseDAO.getCourse(courseID2);
	int courseID3 = courseDAO.saveCourse(course3);
	course3 = courseDAO.getCourse(courseID3);
	
	assertEquals(courseID,course1.getId());
	assertEquals(courseID2,course2.getId());
	assertEquals(courseID3,course3.getId());
	}
	
	@Test
	public void test_getCourse(){
	
		int courseID = courseDAO.saveCourse(course1);
		coursetmp = courseDAO.getCourse(courseID);
		assertNotNull(coursetmp);
		coursetmp = courseDAO.getCourse(-1);
		assertNull(coursetmp);
	
	}
	
	@Test
	public void test_getCourseByCourseCode(){
	
	coursetmp = courseDAO.getCourseByCourseCode("INF5750");
	assertNotNull(coursetmp);
	assertEquals(coursetmp, course3);
	assertNotSame(coursetmp, course2);
	coursetmp = courseDAO.getCourseByCourseCode("INF404");
	assertNull(coursetmp);
	
	}
	
	@Test
	public void test_getCourseByName(){
	coursetmp = null;
	coursetmp = courseDAO.getCourseByName(name3);
	assertEquals(coursetmp, course3);
	assertNotNull(coursetmp);
	assertNotSame(coursetmp, course1);
	coursetmp = courseDAO.getCourseByName("This is not a course");
	assertNull(coursetmp);
	}
	
	@Test
	public void test_getAllCourse(){
	Collection courses = (Collection) courseDAO.getAllCourses();
	
	
	}
	
	@Test
	public void test_delCourse(){
	
		courseDAO.delCourse(course1);
		coursetmp = courseDAO.getCourseByName(name1);
		assertNull(coursetmp);
		

	}
	
	
}
