package net.antra.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import net.antra.cs.domain.Course;
import net.antra.cs.domain.CourseStudentAssoc;
import net.antra.cs.domain.Student;
import net.antra.cs.domain.Teacher;

public class TestApp {
	
	static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("course_student_teacher_pu");
	public static void main(String[] args) {
//		addTeacher();
//		addCourse();
//		addStudent();
//		addCourseStudentAssoc();
		getCourse();
//		queryJPQL();
//		queryJPQL2();
//		querySQL();
//		querySQL2();
//		queryCriteria();
//		queryUsingSession();
//		queryNamedQuery();
		EMF.close();
	}

	public static void addCourse(){
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		//create a new course
		Course course = new Course();
		course.setCredit(1);
		course.setName("Math");
		//set teacher to a coures
		Teacher teacher = em.find(Teacher.class, 12);
		course.setTeacher(teacher);
		//em.persist(teacher); cascade type = all, no need to persist teacher first
		em.persist(course);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	private static void addTeacher() {
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		//create a new teacher
		Teacher teacher = new Teacher();
		teacher.setAge(25);
		teacher.setName("Rosie");
		//em.persist(teacher); cascade type = all, no need to persist teacher first
		em.persist(teacher);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
	private static void addStudent() {
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		Student student = new Student();
		student.setName("Liu");
		em.persist(student);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}	
	
	private static void addCourseStudentAssoc() {
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		CourseStudentAssoc csa = new CourseStudentAssoc();
		Course course = em.find(Course.class, 11);
		Student student = em.find(Student.class, 2);
		csa.setCourse(course);
		csa.setStudent(student);
		em.persist(csa);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
	private static void getCourse() {
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		Course course = em.find(Course.class, 11);
		course.setCredit(123);
		em.getTransaction().commit();
		em.close();
	}
	
	private static void queryJPQL() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("select c from Course c where c.teacher.seqId = :teacherId", Course.class);
		em.createNativeQuery("");
		query.setParameter("teacherId", 12);
		List<Course> list = query.getResultList();
		
		for(Course str : list){
			System.out.println(str);
			System.out.println(str.getTeacher().getName());
		}
		em.close();
	}
	//map part of the columns
	private static void queryJPQL2() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("select NEW Course(c.name, c.credit) from Course c where c.teacher.seqId = :teacherId");
		query.setParameter("teacherId", 8);
		List<Course> list = query.getResultList();
		for(Course str : list){
			System.out.println(str);
		}
		em.close();
	}
	//map native SQL
	private static void querySQL() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createNativeQuery("select * from course c join teacher t on c.teacher_id = t.seq_id where t.seq_id = :teacherId",Course.class);
		query.setParameter("teacherId", 8);
		List<Course> list = query.getResultList();
		for(Course str : list){
			System.out.println(str);
		}
		em.close();
	}
	//map native SQL2
	private static void querySQL2() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createNativeQuery("select c.course_name, t.name from course c join teacher t on c.teacher_id = t.seq_id where t.seq_id = :teacherId");
		query.setParameter("teacherId", 8);
		List<Object[]> list = query.getResultList();
		for(Object[] str : list){
			System.out.println(str[0]);
			System.out.println(str[1]);
		}
		em.close();
	}
	//JPA criteria API
	private static void queryCriteria() {
		EntityManager em = EMF.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = em.getCriteriaBuilder().createQuery(Course.class);
		Root<Course> r = cq.from(Course.class);
		cq.where(builder.equal(r.get("seqId"), 13));
		List<Course> list = em.createQuery(cq).getResultList();
		for(Course str : list){
			System.out.println(str);
		}
		em.close();
	}
	
	//hibernate session
	private static void queryUsingSession() {
		EntityManager em = EMF.createEntityManager();
		Session session = em.unwrap(Session.class);
		org.hibernate.Query q = session.createQuery("from Course c where c.seqId = :cId");
		q.setParameter("cId", 13);
		List<Course> list = q.list();
		for(Course str : list){
			System.out.println(str);
		}
		em.close();
	}
	private static void queryNamedQuery() {
		EntityManager em = EMF.createEntityManager();
		List<Course> list = em.createNamedQuery("MyQuery").getResultList();
		for(Course str : list){
			System.out.println(str);
		}
		em.close();
	}
}
