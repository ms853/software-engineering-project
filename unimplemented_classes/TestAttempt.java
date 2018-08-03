package ecourse.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TestAttempts")
public class TestAttempt {
	
	//--------------------ATTRIBUTES--------------------//

	@Id
	@GeneratedValue
	@Column(name="tAttempt_id")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User learner;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Test test;
	
	@NotNull
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date attemptedDate = new Date();

	
	//--------------------GETTERS--------------------//
	
	public long getId() {
		return id;
	}
	
	public User getLearner() {
		return learner;
	}
	
	public Test getTest() {
		return test;
	}
	
	public Date getAttemptedDate() {
		return attemptedDate;
	}

	
	//--------------------SETTERS--------------------//

	public void setId(long id) {
		this.id = id;
	}
	
	public void setLearner(User learner) {
		this.learner = learner;
	}
	
	public void setTest(Test test) {
		this.test = test;
	}
	
	public void setAttemptedDate(Date attemptedDate) {
		this.attemptedDate = attemptedDate;
	}


	// --------------------GENERAL METHODS--------------------//

	public TestAttempt() {
	}
	
	public TestAttempt(User learner, Test test) {
		this.learner = learner;
		this.test = test;
	}

}
