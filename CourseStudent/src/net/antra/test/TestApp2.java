package net.antra.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.antra.cs.domain.Course;

public class TestApp2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("course_student_teacher_pu");
		EntityManager em = emf.createEntityManager();
		Course course = em.find(Course.class, 11);
		em.getTransaction().begin();
		em.remove(course);
		//em.persist(course);
		em.flush();
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
