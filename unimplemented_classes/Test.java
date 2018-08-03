package ecourse.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name="Tests")
public class Test {
	
	//--------------------ATTRIBUTES--------------------//

	@Id
	@GeneratedValue
	@Column(name="test_id")
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Course course;
	
	// TODO: Should have a collection of questions here with the max score
	// calculated from the sum of how much each question is worth instead.
	// This value should not be stored here.
	@Min(0)
	@Column(nullable=false)
	private int score;
	
	@OneToMany(mappedBy="test", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<TestAttempt> testAttempts = new HashSet<TestAttempt>();
	
	
	//--------------------GETTERS--------------------//

	public long getId() {
		return id;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public Set<TestAttempt> getTestAttempts() {
		return testAttempts;
	}
	
	public int getScore() {
		return score;
	}
	
	
	//--------------------SETTERS--------------------//
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public void setTestAttempts(Set<TestAttempt> testAttempts) {
		this.testAttempts = testAttempts;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	
	// --------------------GENERAL METHODS--------------------//

	public Test() {
	}	

	public Test(Course course, int score) {
		this.course = course;
		this.score = score;
	}

}
