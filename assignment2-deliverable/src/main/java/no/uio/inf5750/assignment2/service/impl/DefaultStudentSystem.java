package no.uio.inf5750.assignment2.service.impl;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.dao.DegreeDAO;
import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;

public class DefaultStudentSystem implements StudentSystem {

	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	DegreeDAO degreeDAO;
	
	@Autowired
	StudentDAO studentDAO;
	
    /**
     * Adds a course.
     * 
     * @param courseCode the course code of the course to add.
     * @param name the name of the course to add.
     * @return the generated id of the added course.
     */
	@Override
	public int addCourse(String courseCode, String name) {
		
		Course newCourse = new Course(courseCode, name);		
		int id = courseDAO.saveCourse(newCourse);
		
		return id;
	}

    /**
     * Updates a course.
     * 
     * @param courseId the id of the course to update.
     * @param courseCode the course code to update.
     * @param name the name to update.
     */
	@Override
	public void updateCourse(int courseId, String courseCode, String name) {
		// TODO Auto-generated method stub

	}

    /**
     * Returns a course.
     * 
     * @param courseId the id of the course to return.
     * @return the course or null if it doesn't exist.
     */
	@Override
	public Course getCourse(int courseId) {
		Course course = courseDAO.getCourse(courseId);
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
		Course course = courseDAO.getCourseByCourseCode(courseCode);
		return course;
	}

    /**
     * Returns a course with a specific name.
     * 
     * @param name the name of the course that needs to be found
     * @return the course code or null if it doesn't exist.
     */
	@Override
	public Course getCourseByName(String name) {
		Course course = courseDAO.getCourseByName(name);
		return course;
	}

    /**
     * Returns all courses.
     * 
     * @return all courses or an empty Collection if no course exists.
     */
	@Override
	public Collection<Course> getAllCourses() {
		Collection<Course> courses = courseDAO.getAllCourses();
		return courses;
	}

    /**
     * Removes all references to the course from degrees and students and
     * deletes the course.
     * 
     * @param courseId the id of the course to delete.
     */
	@Override
	public void delCourse(int courseId) {
		courseDAO.delCourse(courseId);
	}

    /**
     * Adds an attendant to a course and a course to a student.
     * 
     * @param courseId the id of the course.
     * @param studentId the id of the student.
     */
	@Override
	public void addAttendantToCourse(int courseId, int studentId) {
		// TODO Auto-generated method stub

	}

    /**
     * Removes an attendant from a course and a course from a student.
     * 
     * @param courseId the id of the course.
     * @param studentId the id of the student.
     */
	@Override
	public void removeAttendantFromCourse(int courseId, int studentId) {
		// TODO Auto-generated method stub

	}

    /**
     * Adds a degree.
     * 
     * @param type the type of the degree to add.
     * @return the generated id of the added degree.
     */
	@Override
	public int addDegree(String type) {
		// TODO Auto-generated method stub
		return 0;
	}

    /**
     * Updates a degree.
     * 
     * @param degreeId the id of the degree to update.
     * @param type the type to update.
     */
	@Override
	public void updateDegree(int degreeId, String type) {
		// TODO Auto-generated method stub

	}

    /**
     * Returns a degree.
     * 
     * @param degreeId the id of the degree to return.
     * @return the degree or null if it doesn't exist.
     */
	@Override
	public Degree getDegree(int degreeId) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Returns a degree with a specific type.
     * 
     * @param type the type of the degree to return.
     * @return the degree or null if it doesn't exist.
     */
	@Override
	public Degree getDegreeByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * Returns all degrees.
     * 
     * @return all degrees or an empty Collection if no degree exists.
     */
	@Override
	public Collection<Degree> getAllDegrees() {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Removes all references to the degree from students and deletes the
     * degree.
     * 
     * @param degreeId the id of the degree to delete.
     */
	@Override
	public void delDegree(int degreeId) {
		// TODO Auto-generated method stub

	}

    /**
     * Adds a required course to a degree.
     * 
     * @param degreeId the id of the degree.
     * @param courseId the id of the course.
     */
	@Override
	public void addRequiredCourseToDegree(int degreeId, int courseId) {
		// TODO Auto-generated method stub

	}

    /**
     * Removes a required course from a degree.
     * 
     * @param degreeId the id of the degree.
     * @param courseId the id of the course.
     */
	@Override
	public void removeRequiredCourseFromDegree(int degreeId, int courseId) {
		// TODO Auto-generated method stub

	}

	 /**
     * Adds a student.
     * 
     * @param name the name of the student to add.
     * @return the generated id of the added student.
     */
	@Override
	public int addStudent(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

    /**
     * Updates a student.
     * 
     * @param studentId the id of the student to update.
     * @param name the name to update.
     */
	@Override
	public void updateStudent(int studentId, String name) {
		// TODO Auto-generated method stub

	}

    /**
     * Returns a student.
     * 
     * @param studentId the id of the student to return.
     * @return the student or null if it doesn't exist.
     */
	@Override
	public Student getStudent(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Returns a student with a specific name.
     * 
     * @param name the name of the student to return.
     * @return the student or null if it doesn't exist.
     */
	@Override
	public Student getStudentByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Returns all students.
     * 
     * @return all students or an empty Collection if no student exists.
     */
	@Override
	public Collection<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Removes all references to the student from courses and deletes the
     * student.
     * 
     * @param studentId the id of the student to delete.
     */
	@Override
	public void delStudent(int studentId) {
		// TODO Auto-generated method stub

	}

	 /**
     * Adds a degree to a student.
     * 
     * @param studentId the id of the student.
     * @param degreeId the id of the degree.
     */
	@Override
	public void addDegreeToStudent(int studentId, int degreeId) {
		// TODO Auto-generated method stub

	}

    /**
     * Removes a degree from a student.
     * 
     * @param studentId the id of the student.
     * @param degreeId the id of the degree.
     */
	@Override
	public void removeDegreeFromStudent(int studentId, int degreeId) {
		// TODO Auto-generated method stub

	}

    /**
     * Checks if a student has the required courses of a degree.
     * 
     * @param studentId the id of the student.
     * @param degreeId the id of the degree.
     * @return true/false.
     */
	@Override
	public boolean studentFulfillsDegreeRequirements(int studentId, int degreeId) {
		// TODO Auto-generated method stub
		return false;
	}

}
