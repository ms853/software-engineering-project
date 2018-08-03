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
@Table(name="Tickets")
public class Ticket {
	
	//--------------------ATTRIBUTES--------------------//
	
	@Id
	@GeneratedValue
	@Column(name="ticket_id")
	private long id;
	
	@NotNull
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();

	@NotNull
	@Column(length=200, nullable=false)
	private String title;

	@NotNull
	@Column(length=4000, nullable=false)
	private String description;
	
	@Column(nullable=false)
	private boolean resolved = false;
	

	//--------------------GETTERS--------------------//
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public boolean isResolved() {
		return resolved;
	}
	

	//--------------------SETTERS--------------------//

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	
	// --------------------GENERAL METHODS--------------------//

	public Ticket() {
	}
	
	public Ticket(String title, String description) {
		this.title = title;
		this.description = description;
	}

}
