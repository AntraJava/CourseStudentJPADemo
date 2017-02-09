package net.antra.cs.domain2;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student implements Serializable{
	private static final long serialVersionUID = 2034058313623401547L;
	private Integer seqId;
	private String name;
	private String inactiveInd = "N";
	private Set<Course> courses;
	//private Set<CourseStudentAssoc> courseStudentAssoc;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "seq_id")
	public Integer getSeqId() {
		return seqId;
	}
	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}
	@Column(name = "student_name",length=150, nullable=false)
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
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="students")  
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
//	@OneToMany(mappedBy="student")
//	public Set<CourseStudentAssoc> getCourseStudentAssoc() {
//		return courseStudentAssoc;
//	}
//	public void setCourseStudentAssoc(Set<CourseStudentAssoc> courseStudentAssoc) {
//		this.courseStudentAssoc = courseStudentAssoc;
//	}
	
}
