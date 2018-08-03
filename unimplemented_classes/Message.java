package ecourse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Messages")
public class Message {

	//--------------------ATTRIBUTES--------------------//
	
	@Id
	@GeneratedValue
	@Column(name="message_id")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User sender;
	
	@NotNull
	@Column(length=2000, nullable=false)
	private String message;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Conversation conversation;
	

	//--------------------GETTERS--------------------//
	
	public long getId() {
		return id;
	}
	
	public User getSender() {
		return sender;
	}
	
	public String getMessage() {
		return message;
	}
	

	//--------------------SETTERS--------------------//
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setSender(User sender) {
		this.sender = sender;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}


	// --------------------GENERAL METHODS--------------------//

	public Message() {
	}
	
	public Message(User sender, String message) {
		this.sender = sender;
		this.message = message;
	}

}
