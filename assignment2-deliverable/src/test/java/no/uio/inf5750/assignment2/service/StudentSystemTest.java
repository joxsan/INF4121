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
	StudentSystem studentSystem;
	
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
		
		int id = studentSystem.addCourse(cName1, cName2);
		Course tmp = studentSystem.getCourse(id);
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
		int id = studentSystem.addCourse(cName1, cName2);
		studentSystem.updateCourse(id, "INF515", "IT-systemer uangripelige");
		Course tmp = studentSystem.getCourse(id);
		assertTrue(tmp.getCourseCode().equals("INF515"));
	}

    /**
     * Returns a course.
     * 
     * @param courseId the id of the course to return.
     * @return the course or null if it doesn't exist.
     */
	@Test
	public void test_getCourse() {
		int id = studentSystem.addCourse(cName1, cName2);
		Course tmp = studentSystem.getCourse(id);
		assertNotNull(tmp);
	}

    /**
     * Returns a course with a specific course code.
     * 
     * @param courseCode the course code of the course to return.
     * @return the course code or null if it doesn't exist.
     */
	@Test
	public void test_getCourseByCourseCode() {
		studentSystem.addCourse(cName1, cName2);
		Course tmp = studentSystem.getCourseByCourseCode(cName1);
		assertNotNull(tmp);
	}

    /**
     * Returns a course with a specific name.
     * 
     * @param name the name of the course that needs to be found
     * @return the course code or null if it doesn't exist.
     */
	@Test
	public void test_getCourseByName() {
	studentSystem.addCourse(cName1, cName2);
	Course tmp = studentSystem.getCourseByName(cName2);
	assertNotNull(tmp);
	}

    /**
     * Returns all courses.
     * 
     * @return all courses or an empty Collection if no course exists.
     */
	@Test
	public void test_getAllCourses() {
	studentSystem.addCourse(cName1, cName2);
	Collection<Course> courses = studentSystem.getAllCourses();
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
	int id = studentSystem.addCourse(cName1, cName2);
	studentSystem.delCourse(id);
	Course tmp = studentSystem.getCourse(id);
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
		int idc = studentSystem.addCourse(cName1, cName2);
		int ids = studentSystem.addStudent(sName1);
		
		studentSystem.addAttendantToCourse(idc, ids);
		Course tmp = studentSystem.getCourse(idc);
		Collection<Student> s = tmp.getAttendants();
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
		int idc = studentSystem.addCourse(cName1, cName2);
		int ids = studentSystem.addStudent(sName1);
		
		studentSystem.addAttendantToCourse(idc, ids);
		studentSystem.removeAttendantFromCourse(idc, ids);
		Course tmp = studentSystem.getCourse(idc);
		Collection<Student> s = tmp.getAttendants();
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
		int id = studentSystem.addDegree(dName1);
		Degree tmp = studentSystem.getDegree(id);
		
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
		int id = studentSystem.addDegree(dName1);
		studentSystem.updateDegree(id, "Informatikk");
		
		assertTrue(studentSystem.getDegree(id).getType().equals("Informatikk"));
	}

    /**
     * Returns a degree.
     * 
     * @param degreeId the id of the degree to return.
     * @return the degree or null if it doesn't exist.
     */
	@Test
	public void test_getDegree() {
		int id = studentSystem.addDegree(dName1);
		Degree tmp = studentSystem.getDegree(id);
		
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
		int id = studentSystem.addDegree(dName1);
		Degree tmp = studentSystem.getDegreeByType(dName1);
		
		assertNotNull(tmp);
	}

	/**
     * Returns all degrees.
     * 
     * @return all degrees or an empty Collection if no degree exists.
     */
	@Test
	public void test_getAllDegrees() {
		studentSystem.addDegree(dName1);
		Collection<Degree> d = studentSystem.getAllDegrees();
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
		int id = studentSystem.addDegree(dName1);
		studentSystem.delDegree(id);
		Degree tmp = studentSystem.getDegree(id);
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
		int idd = studentSystem.addDegree(dName1);
		int idc = studentSystem.addCourse(cName1, cName2);
		
		studentSystem.addRequiredCourseToDegree(idd, idc);
		Degree d = studentSystem.getDegree(idd);
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
		int idd = studentSystem.addDegree(dName1);
		int idc = studentSystem.addCourse(cName1, cName2);
		
		studentSystem.addRequiredCourseToDegree(idd, idc);
		studentSystem.removeRequiredCourseFromDegree(idd, idc);
		Degree d = studentSystem.getDegree(idd);
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
		int ids = studentSystem.addStudent(sName1);
		Student tmp = studentSystem.getStudent(ids);
		assertNotNull(tmp);
	}

    /**
     * Updates a student.
     * 
     * @param studentId the id of the student to update.
     * @param name the name to update.
     */
	@Test
	public void test_updateStudent() {
		int ids = studentSystem.addStudent(sName1);
		studentSystem.updateStudent(ids, "Johan");
		
		Student tmp = studentSystem.getStudent(ids);
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
		int ids = studentSystem.addStudent(sName1);
		Student tmp = studentSystem.getStudent(ids);
		
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
		int ids = studentSystem.addStudent(sName1);
		Student tmp = studentSystem.getStudentByName(sName1);
		
		assertNotNull(tmp);
	}

    /**
     * Returns all students.
     * 
     * @return all students or an empty Collection if no student exists.
     */
	@Test
	public void test_getAllStudents() {
		int ids = studentSystem.addStudent(sName1);
		Collection<Student> s = studentSystem.getAllStudents();
		
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
		int ids = studentSystem.addStudent(sName1);
		studentSystem.delStudent(ids);
		
		Student tmp = studentSystem.getStudent(ids);
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
		int ids = studentSystem.addStudent(sName1);
		int idd = studentSystem.addDegree(dName1);
		studentSystem.addDegreeToStudent(ids, idd);
		
		Student s = studentSystem.getStudent(ids);
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
		int ids = studentSystem.addStudent(sName1);
		int idd = studentSystem.addDegree(dName1);
		studentSystem.addDegreeToStudent(ids, idd);
		
		Student s = studentSystem.getStudent(ids);
		studentSystem.removeDegreeFromStudent(ids, idd);
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
		
		int id1 = studentSystem.addCourse(cName1, cName2);
		int id2 = studentSystem.addStudent(sName1);
		int id3 = studentSystem.addDegree(dName1);
	
		Degree tmp1 = studentSystem.getDegree(id3);
		Course tmp2 = studentSystem.getCourse(id1);
		studentSystem.addRequiredCourseToDegree(id3, id1);
		studentSystem.addAttendantToCourse(id1, id2);
		studentSystem.studentFulfillsDegreeRequirements(id1, id3);
	
		assertTrue(studentSystem.studentFulfillsDegreeRequirements(id2, id3));
		
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
