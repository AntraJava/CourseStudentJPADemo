package net.antra.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

import net.antra.cs.domain2.Course;
import net.antra.cs.domain2.Student;
import net.antra.cs.domain2.Teacher;

public class TestApp2 {
	public static void main(String[] args) {
//		addCourseAndTeacher();
//		addCourseAndStudents();
//		getCourseAndLazyTeacher();
		getCourseAndLazyTeacher2();
//		demo();
	}
	
	private static void demo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("course_student_teacher_pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Course course = em.find(Course.class, 19);
		Course course1 = em.find(Course.class, 19);
		Course course2 = em.find(Course.class, 19);
		em.getTransaction().commit();
		em.close();
	}

	public static void addCourseAndTeacher(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("course_student_teacher_pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		
		
		//create a new course
		Course course = new Course();
		course.setCredit(1);
		course.setName("Science");
		//set teacher to a coures
		Teacher teacher = new Teacher();
		teacher.setSeqId(18);
		teacher.setAge(20);
		course.setTeacher(teacher);
		
		em.merge(course);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public static void addCourseAndStudents(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("course_student_teacher_pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		
		//create a new course
		Course course = new Course();
		course.setCredit(1);
		course.setName("Art");
		//set teacher to a coures
		Set<Student> students = new HashSet<>();
		Student student1 = new Student();
		Student student2 = new Student();
		student1.setName("Student A");
		student2.setName("Student B");
		students.add(student1);
		students.add(student2);
		course.setStudents(students);
		
		
		em.persist(course);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public static void getCourseAndLazyTeacher(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("course_student_teacher_pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Course c = em.find(Course.class, 25);

		em.getTransaction().commit();
		em.close();
		System.out.println(c.getTeacher().getAge());

		emf.close();
	}
	
	public static void getCourseAndLazyTeacher2(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("course_student_teacher_pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Course c = em.find(Course.class, 25);
		//Hibernate.initialize(c.getTeacher());
//		c.getTeacher().getName();
//		Course c = em.createQuery("select c from Course c Join Fetch c.teacher where c.id = 25",Course.class)
//				   .getSingleResult();
		em.getTransaction().commit();
		em.close();
		System.out.println(c.getTeacher().getAge());
		emf.close();
	}
}
