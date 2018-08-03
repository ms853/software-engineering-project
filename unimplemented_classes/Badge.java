package ecourse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Badges")
public class Badge {

	//--------------------ATTRIBUTES--------------------//
	
	@Id
	@GeneratedValue
	@Column(name="badge_id")
	private long id;
	
	@NotNull
	@Column(length=80, nullable=false)
	private String name;
	
	@NotNull
	@Column(length=2000, nullable=false)
	private String description;
	
	@NotNull
	@Column(length=80, nullable=false)
	private String type;
	

	//--------------------GETTERS--------------------//
	
	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}


	//--------------------SETTERS--------------------//
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	//--------------------GENERAL METHODS--------------------//

	public Badge() {
	}

	public Badge(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

}
