package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import org.hibernate.mapping.Collection;
import org.junit.Before;
import org.junit.Test;

import no.uio.inf5750.assignment2.model.Student;

public class StudentDAOTest {

	Student student1, student2, studenttmp;
	String name1, name2;
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
		studenttmp = studentDAO.getStudentByName(name1);
		assertNotNull(studenttmp);
		assertEquals(studenttmp, student1);
		assertNotSame(studenttmp, student2);
		studenttmp = studentDAO.getStudentByName("Fake Fakesen");
		assertNull(studenttmp);
	}
	
	@Test 
	public void test_getAllStudents(){
	Collection students = (Collection) studentDAO.getAllStudents();
	}
	
	@Test
	public void test_delStudent(){
	studentDAO.delStudent(student1);	
	studenttmp = studentDAO.getStudentByName(name1);
	assertNull(studenttmp);
	}
	
	
	
}
