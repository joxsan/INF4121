package no.uio.inf5750.assignment2.service;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.dao.DegreeDAO;
import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.impl.DefaultStudentSystem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
public class StudentSystemTest {
	
	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	DegreeDAO degreeDAO;
	
	@Autowired
	StudentDAO studentDAO;
	
	@Autowired
	DefaultStudentSystem studentSystemDAO;
	
	Course course1, course2;
	Student student1, student2;
	Degree degree1, degree2;
	
	String cName1, cName2, sName1, sName2, dName1, dName2;
	
	@Before
	public void init(){
		
		course1 = new Course("INF5150", "Uangripelige IT-systemer");
		course2 = new Course("INF5750", "Open source development");
		
		student1 = new Student("Johan Falk");
		student2 = new Student("Mira Amadeusen");
		
		degree1 = new Degree("Prognett");
		degree2 = new Degree("Nano");
		
		cName1 = "INF5150";
		cName2 = "Uangripelige IT-systemer";
		sName1 = "Johan Falk";
		dName1 = "Prognett";
	}
	
    /**
     * Adds a course.
     * 
     * @param courseCode the course code of the course to add.
     * @param name the name of the course to add.
     * @return the generated id of the added course.
     */
	@Test
	public void test_addCourse() {
			
		//int id = courseDAO.saveCourse(course1);
		//course1 = courseDAO.getCourseByCourseCode("INF5150");
		//assertEquals(id, course1.getId());
		
		int id = studentSystemDAO.addCourse(cName1, cName2);
		Course tmp = studentSystemDAO.getCourse(id);
		assertNotNull(tmp);
	}

    /**
     * Updates a course.
     * 
     * @param courseId the id of the course to update.
     * @param courseCode the course code to update.
     * @param name the name to update.
     */
	@Test
	public void test_updateCourse() {
		int id = courseDAO.saveCourse(course1);
		studentSystemDAO.updateCourse(id, "INF515", "IT-systemer uangripelige");
		
		assertTrue(course1.getCourseCode().equals("INF515"));
	}

    /**
     * Returns a course.
     * 
     * @param courseId the id of the course to return.
     * @return the course or null if it doesn't exist.
     */
	@Test
	public void test_getCourse() {
		int id = courseDAO.saveCourse(course1);
		studentSystemDAO.getCourse(id);
		assertSame(id, course1.getId());
	}

    /**
     * Returns a course with a specific course code.
     * 
     * @param courseCode the course code of the course to return.
     * @return the course code or null if it doesn't exist.
     */
	@Test
	public void test_getCourseByCourseCode() {
		courseDAO.saveCourse(course1);
		Course tmp = studentSystemDAO.getCourseByCourseCode(cName1);
		assertSame(course1, tmp);
	}

    /**
     * Returns a course with a specific name.
     * 
     * @param name the name of the course that needs to be found
     * @return the course code or null if it doesn't exist.
     */
	@Test
	public void test_getCourseByName() {
		courseDAO.saveCourse(course1);
		Course tmp = studentSystemDAO.getCourseByName(cName1);
		assertSame(course1, tmp);
	}

    /**
     * Returns all courses.
     * 
     * @return all courses or an empty Collection if no course exists.
     */
	@Test
	public void test_getAllCourses() {
		courseDAO.saveCourse(course1);
		
		Collection<Course> courses = studentSystemDAO.getAllCourses();
		
		assertTrue(courses.size()>0);
	}

    /**
     * Removes all references to the course from degrees and students and
     * deletes the course.
     * 
     * @param courseId the id of the course to delete.
     */
	@Test
	public void test_delCourse() {
		int id = courseDAO.saveCourse(course1);
		studentSystemDAO.delCourse(id);
		Course tmp = studentSystemDAO.getCourse(id);
		assertNull(tmp);
	}

    /**
     * Adds an attendant to a course and a course to a student.
     * 
     * @param courseId the id of the course.
     * @param studentId the id of the student.
     */
	@Test
	public void test_addAttendantToCourse() {
		int idc = courseDAO.saveCourse(course1);
		int ids = studentDAO.saveStudent(student1);
		
		studentSystemDAO.addAttendantToCourse(idc, ids);
		Collection<Student> s = course1.getAttendants();
		assertTrue(s.size() > 0);
	}

    /**
     * Removes an attendant from a course and a course from a student.
     * 
     * @param courseId the id of the course.
     * @param studentId the id of the student.
     */
	@Test
	public void test_removeAttendantFromCourse() {
		int idc = courseDAO.saveCourse(course1);
		int ids = studentDAO.saveStudent(student1);
		
		studentSystemDAO.addAttendantToCourse(idc, ids);
		studentSystemDAO.removeAttendantFromCourse(idc, ids);
		Collection<Student> s = course1.getAttendants();
		assertTrue(s.size() == 0);
	}

    /**
     * Adds a degree.
     * 
     * @param type the type of the degree to add.
     * @return the generated id of the added degree.
     */
	@Test
	public void test_addDegree() {
		int id = studentSystemDAO.addDegree(dName1);
		Degree tmp = studentSystemDAO.getDegree(id);
		
		assertNotNull(tmp);
		
	}

    /**
     * Updates a degree.
     * 
     * @param degreeId the id of the degree to update.
     * @param type the type to update.
     */
	@Test
	public void test_updateDegree() {
		int id = studentSystemDAO.addDegree(dName1);
		studentSystemDAO.updateDegree(id, "Informatikk");
		
		assertTrue(studentSystemDAO.getDegree(id).getType().equals("Informatikk"));
	}

    /**
     * Returns a degree.
     * 
     * @param degreeId the id of the degree to return.
     * @return the degree or null if it doesn't exist.
     */
	@Test
	public void test_getDegree() {
		int id = studentSystemDAO.addDegree(dName1);
		Degree tmp = studentSystemDAO.getDegree(id);
		
		assertNotNull(tmp);
	}

    /**
     * Returns a degree with a specific type.
     * 
     * @param type the type of the degree to return.
     * @return the degree or null if it doesn't exist.
     */
	@Test
	public void test_getDegreeByType() {
		int id = studentSystemDAO.addDegree(dName1);
		Degree tmp = studentSystemDAO.getDegreeByType(dName1);
		
		assertNotNull(tmp);
	}

	/**
     * Returns all degrees.
     * 
     * @return all degrees or an empty Collection if no degree exists.
     */
	@Test
	public void test_getAllDegrees() {
		studentSystemDAO.addDegree(dName1);
		Collection<Degree> d = studentSystemDAO.getAllDegrees();
		assertTrue(d.size() > 0);
	}

    /**
     * Removes all references to the degree from students and deletes the
     * degree.
     * 
     * @param degreeId the id of the degree to delete.
     */
	@Test
	public void test_delDegree() {
		int id = studentSystemDAO.addDegree(dName1);
		studentSystemDAO.delDegree(id);
		Degree tmp = studentSystemDAO.getDegree(id);
		assertNull(tmp);
	}

    /**
     * Adds a required course to a degree.
     * 
     * @param degreeId the id of the degree.
     * @param courseId the id of the course.
     */
	@Test
	public void test_addRequiredCourseToDegree() {
		int idd = studentSystemDAO.addDegree(dName1);
		int idc = studentSystemDAO.addCourse(cName1, cName2);
		
		studentSystemDAO.addRequiredCourseToDegree(idd, idc);
		Degree d = studentSystemDAO.getDegree(idd);
		Collection<Course> c = d.getRequiredCourses();
		assertTrue(c.size() > 0);
	}

    /**
     * Removes a required course from a degree.
     * 
     * @param degreeId the id of the degree.
     * @param courseId the id of the course.
     */
	@Test
	public void test_removeRequiredCourseFromDegree() {
		int idd = studentSystemDAO.addDegree(dName1);
		int idc = studentSystemDAO.addCourse(cName1, cName2);
		
		studentSystemDAO.addRequiredCourseToDegree(idd, idc);
		studentSystemDAO.removeRequiredCourseFromDegree(idd, idc);
		Degree d = studentSystemDAO.getDegree(idd);
		Collection<Course> c = d.getRequiredCourses();
		assertTrue(c.size() == 0);
	}

	 /**
     * Adds a student.
     * 
     * @param name the name of the student to add.
     * @return the generated id of the added student.
     */
	@Test
	public void test_addStudent() {
		int ids = studentSystemDAO.addStudent(sName1);
	}

    /**
     * Updates a student.
     * 
     * @param studentId the id of the student to update.
     * @param name the name to update.
     */
	@Test
	public void test_updateStudent() {
		int ids = studentSystemDAO.addStudent(sName1);
		studentSystemDAO.updateStudent(ids, "Johan");
		
		Student tmp = studentSystemDAO.getStudent(ids);
		assertSame(tmp.getName(), "Johan");
		
	}

    /**
     * Returns a student.
     * 
     * @param studentId the id of the student to return.
     * @return the student or null if it doesn't exist.
     */
	@Test
	public void test_getStudent() {
		int ids = studentSystemDAO.addStudent(sName1);
		Student tmp = studentSystemDAO.getStudent(ids);
		
		assertNotNull(tmp);
	}

    /**
     * Returns a student with a specific name.
     * 
     * @param name the name of the student to return.
     * @return the student or null if it doesn't exist.
     */
	@Test
	public void test_getStudentByName() {
		int ids = studentSystemDAO.addStudent(sName1);
		Student tmp = studentSystemDAO.getStudentByName(sName1);
		
		assertNotNull(tmp);
	}

    /**
     * Returns all students.
     * 
     * @return all students or an empty Collection if no student exists.
     */
	@Test
	public void test_getAllStudents() {
		int ids = studentSystemDAO.addStudent(sName1);
		Collection<Student> s = studentSystemDAO.getAllStudents();
		
		assertTrue(s.size() > 0);
	}

    /**
     * Removes all references to the student from courses and deletes the
     * student.
     * 
     * @param studentId the id of the student to delete.
     */
	@Test
	public void test_delStudent() {
		int ids = studentSystemDAO.addStudent(sName1);
		studentSystemDAO.delStudent(ids);
		
		Student tmp = studentSystemDAO.getStudent(ids);
		assertNull(tmp);
	}

	 /**
     * Adds a degree to a student.
     * 
     * @param studentId the id of the student.
     * @param degreeId the id of the degree.
     */
	@Test
	public void test_addDegreeToStudent() {
		int ids = studentSystemDAO.addStudent(sName1);
		int idd = studentSystemDAO.addDegree(dName1);
		studentSystemDAO.addDegreeToStudent(ids, idd);
		
		Student s = studentSystemDAO.getStudent(ids);
		Collection<Degree> d = s.getDegrees();
		assertTrue(d.size() > 0);
	}

    /**
     * Removes a degree from a student.
     * 
     * @param studentId the id of the student.
     * @param degreeId the id of the degree.
     */
	@Test
	public void test_removeDegreeFromStudent() {
		int ids = studentSystemDAO.addStudent(sName1);
		int idd = studentSystemDAO.addDegree(dName1);
		studentSystemDAO.addDegreeToStudent(ids, idd);
		
		Student s = studentSystemDAO.getStudent(ids);
		studentSystemDAO.removeDegreeFromStudent(ids, idd);
		Collection<Degree> d = s.getDegrees();
		assertTrue(d.size() == 0);
	}

    /**
     * Checks if a student has the required courses of a degree.
     * 
     * @param studentId the id of the student.
     * @param degreeId the id of the degree.
     * @return true/false.
     */
	@Test
	public void test_studentFulfillsDegreeRequirements() {
		
		
//		int id1 = courseDAO.saveCourse(course1);
//		int id2 = studentDAO.saveStudent(student1);
//		int id3 = degreeDAO.saveDegree(degree1);
		
		courseDAO.saveCourse(course1);
		studentDAO.saveStudent(student1);
		degreeDAO.saveDegree(degree1);
		
	
		//studentSystemDAO.addRequiredCourseToDegree(degree1.getId(), course1.getId());
		//dss.addAttendantToCourse(id1, id2);
		//dss.studentFulfillsDegreeRequirements(id1, id3);
	
	
	
/*		Student student = studentDAO.getStudent(studentId);
		Degree degree = degreeDAO.getDegree(degreeId);
		
		Set<Course> reqCourses = degree.getRequiredCourses();
		Set<Course> finCourses = student.getCourses();
		
		int totalMatches = 0;
		
		for(Course c: reqCourses){
			for(Course fc: finCourses){
				if(c.equals(fc)){
					totalMatches++;
				}
			}
		}
	
		if(totalMatches == reqCourses.size()){
			return true;
		}
		else return false;
	}
	*/
	}
}
