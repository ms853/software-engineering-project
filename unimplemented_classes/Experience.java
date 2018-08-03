package ecourse.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Experiences")
public class Experience {
	
	//--------------------ATTRIBUTES--------------------//
	
	@Id
	@GeneratedValue
	@Column(name="experience_id")
	private long id;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date fromDate;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date toDate;
	
	@NotNull
	@Column(length=100, nullable=false)
	private String title;
	
	@NotNull
	@Column(length=2000, nullable=false)
	private String description;
	

	//--------------------GETTERS--------------------//
	
	public String getDescription() {
		return description;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Date getToDate() {
		return toDate;
	}
	

	//--------------------SETTERS--------------------//
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	// --------------------GENERAL METHODS--------------------//

	public Experience() {
	}	
	
	public Experience(String title, String description) {
		this.title = title;
		this.description = description;
	}

}
