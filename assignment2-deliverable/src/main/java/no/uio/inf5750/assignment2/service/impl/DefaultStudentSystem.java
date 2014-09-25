package no.uio.inf5750.assignment2.service.impl;

import java.util.Collection;
import java.util.Set;

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
		Course updateCourse = courseDAO.getCourse(courseId);
		updateCourse.setCourseCode(courseCode);
		updateCourse.setName(name);

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
		Course course = getCourse(courseId);
		courseDAO.delCourse(course);
	}

    /**
     * Adds an attendant to a course and a course to a student.
     * 
     * @param courseId the id of the course.
     * @param studentId the id of the student.
     */
	@Override
	public void addAttendantToCourse(int courseId, int studentId) {
		Course course = getCourse(courseId);
		Student student = getStudent(studentId);
		
		Set<Student> students = course.getAttendants();
		students.add(student);
		course.setAttendants(students);
	}

    /**
     * Removes an attendant from a course and a course from a student.
     * 
     * @param courseId the id of the course.
     * @param studentId the id of the student.
     */
	@Override
	public void removeAttendantFromCourse(int courseId, int studentId) {
		Course course = getCourse(courseId);
		Student student = getStudent(studentId);
		
		Set<Student> students = course.getAttendants();
		students.remove(student);
		course.setAttendants(students);
	}

    /**
     * Adds a degree.
     * 
     * @param type the type of the degree to add.
     * @return the generated id of the added degree.
     */
	@Override
	public int addDegree(String type) {
		Degree newDegree = new Degree(type);
		int degreeID = degreeDAO.saveDegree(newDegree);
		return degreeID;
	}

    /**
     * Updates a degree.
     * 
     * @param degreeId the id of the degree to update.
     * @param type the type to update.
     */
	@Override
	public void updateDegree(int degreeId, String type) {
		Degree updateDegree = degreeDAO.getDegree(degreeId);
		updateDegree.setType(type);
	}

    /**
     * Returns a degree.
     * 
     * @param degreeId the id of the degree to return.
     * @return the degree or null if it doesn't exist.
     */
	@Override
	public Degree getDegree(int degreeId) {
		Degree d = degreeDAO.getDegree(degreeId);
		return d;
	}

    /**
     * Returns a degree with a specific type.
     * 
     * @param type the type of the degree to return.
     * @return the degree or null if it doesn't exist.
     */
	@Override
	public Degree getDegreeByType(String type) {
		Degree d = degreeDAO.getDegreeByType(type);
		return d;
	}

	/**
     * Returns all degrees.
     * 
     * @return all degrees or an empty Collection if no degree exists.
     */
	@Override
	public Collection<Degree> getAllDegrees() {
		Collection<Degree> degrees = degreeDAO.getAllDegrees();
		return degrees;
	}

    /**
     * Removes all references to the degree from students and deletes the
     * degree.
     * 
     * @param degreeId the id of the degree to delete.
     */
	@Override
	public void delDegree(int degreeId) {
		Degree d = degreeDAO.getDegree(degreeId);
		degreeDAO.delDegree(d);
	}

    /**
     * Adds a required course to a degree.
     * 
     * @param degreeId the id of the degree.
     * @param courseId the id of the course.
     */
	@Override
	public void addRequiredCourseToDegree(int degreeId, int courseId) {
		Degree degree = degreeDAO.getDegree(degreeId);
		Course course = courseDAO.getCourse(courseId);
		
		Set<Course> reqCourses = degree.getRequiredCourses();
		reqCourses.add(course);
		degree.setRequiredCourses(reqCourses);
	}

    /**
     * Removes a required course from a degree.
     * 
     * @param degreeId the id of the degree.
     * @param courseId the id of the course.
     */
	@Override
	public void removeRequiredCourseFromDegree(int degreeId, int courseId) {
		Degree degree = degreeDAO.getDegree(degreeId);
		Course course = courseDAO.getCourse(courseId);
		
		Set<Course> reqCourses = degree.getRequiredCourses();
		reqCourses.remove(course);
		degree.setRequiredCourses(reqCourses);

	}

	 /**
     * Adds a student.
     * 
     * @param name the name of the student to add.
     * @return the generated id of the added student.
     */
	@Override
	public int addStudent(String name) {
		Student student = new Student(name);
		int studentID = studentDAO.saveStudent(student);
		return studentID;
	}

    /**
     * Updates a student.
     * 
     * @param studentId the id of the student to update.
     * @param name the name to update.
     */
	@Override
	public void updateStudent(int studentId, String name) {
		Student student = studentDAO.getStudent(studentId);
		student.setName(name);
	}

    /**
     * Returns a student.
     * 
     * @param studentId the id of the student to return.
     * @return the student or null if it doesn't exist.
     */
	@Override
	public Student getStudent(int studentId) {
		Student student = studentDAO.getStudent(studentId);
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
		Student student = studentDAO.getStudentByName(name);
		return student;
	}

    /**
     * Returns all students.
     * 
     * @return all students or an empty Collection if no student exists.
     */
	@Override
	public Collection<Student> getAllStudents() {
		Collection<Student> students = studentDAO.getAllStudents();
		return students;
	}

    /**
     * Removes all references to the student from courses and deletes the
     * student.
     * 
     * @param studentId the id of the student to delete.
     */
	@Override
	public void delStudent(int studentId) {
		Student student = getStudent(studentId);
		studentDAO.delStudent(student);
	}

	 /**
     * Adds a degree to a student.
     * 
     * @param studentId the id of the student.
     * @param degreeId the id of the degree.
     */
	@Override
	public void addDegreeToStudent(int studentId, int degreeId) {
		Student student = studentDAO.getStudent(studentId);
		Degree degree = degreeDAO.getDegree(degreeId);
		
		Set<Degree> degrees = student.getDegrees();
		degrees.add(degree);
		student.setDegrees(degrees);
	}

    /**
     * Removes a degree from a student.
     * 
     * @param studentId the id of the student.
     * @param degreeId the id of the degree.
     */
	@Override
	public void removeDegreeFromStudent(int studentId, int degreeId) {
		Student student = studentDAO.getStudent(studentId);
		Degree degree = degreeDAO.getDegree(degreeId);
		
		Set<Degree> degrees = student.getDegrees();
		degrees.remove(degree);
		student.setDegrees(degrees);
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
		Student student = studentDAO.getStudent(studentId);
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

}
