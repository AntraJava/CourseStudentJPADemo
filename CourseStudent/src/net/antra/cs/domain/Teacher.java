//package net.antra.cs.domain;
//
//import java.io.Serializable;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="teacher")
//public class Teacher implements Serializable{
//	private static final long serialVersionUID = 1811519075912415057L;
//	private Integer seqId;
//	private String name;
//	private Integer age;
//	private String inactiveInd = "N";
//	private Set<Course> courses;
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name = "seq_id")
//	public Integer getSeqId() {
//		return seqId;
//	}
//	public void setSeqId(Integer seqId) {
//		this.seqId = seqId;
//	}
//	@Column(name = "name",length=150, nullable=false)
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	@Column(name = "inactive_ind",length=1, nullable=false)
//	public String getInactiveInd() {
//		return inactiveInd;
//	}
//	public void setInactiveInd(String inactiveInd) {
//		this.inactiveInd = inactiveInd;
//	}
//	@Column(name="age",columnDefinition="int")
//	public Integer getAge() {
//		return age;
//	}
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//	@OneToMany(mappedBy="teacher")
//	public Set<Course> getCourses() {
//		return courses;
//	}
//	public void setCourses(Set<Course> courses) {
//		this.courses = courses;
//	}
//}
