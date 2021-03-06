package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.dao.hibernate.HibernateCourseDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
public class CourseDAOTest {
	
	Course course1, course2, course3, coursetmp;
	String name1, name2, name3;
	
	@Autowired
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
		assertSame(coursetmp, course1);
		coursetmp = courseDAO.getCourse(-1);
		assertNull(coursetmp);
	
	}
	
	@Test
	public void test_getCourseByCourseCode(){

		courseDAO.saveCourse(course3);
		coursetmp = courseDAO.getCourseByCourseCode("INF5750");
		assertNotNull(coursetmp);
		assertEquals(coursetmp, course3);
		assertNotSame(coursetmp, course2);
		coursetmp = null;
		coursetmp = courseDAO.getCourseByCourseCode("INF404");
		assertNull(coursetmp);

	}

	@Test
	public void test_getCourseByName(){
		courseDAO.saveCourse(course3);
		coursetmp = courseDAO.getCourseByCourseCode("INF5750");
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
		int idc1 =  courseDAO.saveCourse(course2);
		int idc2 = courseDAO.saveCourse(course3);
		Course tmp1 = courseDAO.getCourse(idc1);
		Course tmp2 = courseDAO.getCourse(idc2);
		Collection<Course> courses = (Collection<Course>) courseDAO.getAllCourses();
		assertTrue(courses.size() == 2);
		int cnt = 0;
		for(Course c: courses){
			if(cnt == 0){
				Course tmp3 = courseDAO.getCourse(c.getId());
				assertSame(tmp3, course2);
			}
			if(cnt == 1){
			Course tmp3 = courseDAO.getCourse(c.getId());
			assertSame(tmp3, course3);	
			}
			cnt++;
		}

	}
	
	@Test
	public void test_delCourse(){
	
		courseDAO.delCourse(course1);
		coursetmp = courseDAO.getCourseByName(name1);
		assertNull(coursetmp);
		

	}
	
	
}
