package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import java.util.Collection;

//import org.hibernate.mapping.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import no.uio.inf5750.assignment2.dao.hibernate.HibernateCourseDao;
import no.uio.inf5750.assignment2.dao.hibernate.HibernateStudentDao;
import no.uio.inf5750.assignment2.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
public class StudentDAOTest {

	Student student1, student2, studenttmp;
	String name1, name2;
	
	@Autowired
	StudentDAO studentDAO;
	
	@Before
	public void init(){
		
		student1 = new Student("Donald Duck");
		student2 = new Student("Mikke Mus");
		
		name1 = "Donald Duck";
		name2 = "Mikke Mus";
	}
	
	@Test 
	public void test_saveStudent(){
	
		int studentID = studentDAO.saveStudent(student1);
		student1 = studentDAO.getStudent(studentID);
		
		assertEquals(studentID, student1.getId());
	}
	
	@Test
	public void test_getStudentByName(){
		studentDAO.saveStudent(student1);
		studenttmp = studentDAO.getStudentByName(name1);
		assertNotNull(studenttmp);
		assertEquals(studenttmp, student1);
		assertNotSame(studenttmp, student2);
		studenttmp = studentDAO.getStudentByName("Fake Fakesen");
		assertNull(studenttmp);
	}
	
	@Test 
	public void test_getAllStudents(){
	studentDAO.saveStudent(student1);
	Collection<Student> students = (Collection<Student>) studentDAO.getAllStudents();
	for(Student s: students){
	assertEquals(s,student1);
	}
	}
	
	@Test
	public void test_delStudent(){
	studentDAO.saveStudent(student1);
	studentDAO.delStudent(student1);	
	studenttmp = studentDAO.getStudentByName(name1);
	assertNull(studenttmp);
	}
	
	
	
}
