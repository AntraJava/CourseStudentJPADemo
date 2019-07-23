package net.antra.cs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="course_student_assoc")
public class CourseStudentAssoc implements Serializable{

	private static final long serialVersionUID = 5196340810593428537L;
	private Integer seqId;
	private String inactiveInd = "N";
	private Course course;
	private Student student;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "seq_id")
	public Integer getSeqId() {
		return seqId;
	}
	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}
	@Column(name = "inactive_ind",length=1, nullable=false)
	public String getInactiveInd() {
		return inactiveInd;
	}
	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}
	@ManyToOne
	@JoinColumn(name="course_id")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@ManyToOne
	@JoinColumn(name="student_id")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}
