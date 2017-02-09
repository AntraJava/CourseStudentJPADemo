package net.antra.cs.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
@NamedQuery(name="MyQuery",query = "select c from Course c")
public class Course implements Serializable{
	private static final long serialVersionUID = -511979213258675751L;
	private Integer seqId;
	private String name;
	private Integer credit;
	private Teacher teacher;
	private String inactiveInd = "N";
	private Set<Student> students;
//	private Set<CourseStudentAssoc> courseStudentAssoc;
	public Course() {
	}

	public Course(String name, Integer credit) {
		this.name = name;
		this.credit = credit;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "seq_id")
	public Integer getSeqId() {
		return seqId;
	}
	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}
	@Column(name = "course_name",length=150, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "inactive_ind",length=1, nullable=false)
	public String getInactiveInd() {
		return inactiveInd;
	}
	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}
	@Column(name="credit",columnDefinition="int")
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	@ManyToOne
	@JoinColumn(name="teacher_id")
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="course_student_assoc", 
    			joinColumns=@JoinColumn(name="course_id"), 
    			inverseJoinColumns=@JoinColumn(name="student_id"))
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
//	@OneToMany(mappedBy="course")
//	public Set<CourseStudentAssoc> getCourseStudentAssoc() {
//		return courseStudentAssoc;
//	}
//	public void setCourseStudentAssoc(Set<CourseStudentAssoc> courseStudentAssoc) {
//		this.courseStudentAssoc = courseStudentAssoc;
//	}

	@Override
	public String toString() {
		return "Course name:" + this.name;
	}
	
}
