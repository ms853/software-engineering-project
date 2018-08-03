package ecourse.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Conversations")
public class Conversation {

	//--------------------ATTRIBUTES--------------------//
	
	@Id
	@GeneratedValue
	@Column(name="conversation_id")
	private long id;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="ConversationUsers",
		joinColumns=@JoinColumn(name="conversation_id"),
		inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<User> users = new HashSet<User>();
	
	@OneToMany(mappedBy="conversation", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Message> messages = new ArrayList<Message>();
	

	//--------------------GETTERS--------------------//
	
	public long getId() {
		return id;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	

	//--------------------SETTERS--------------------//
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	

	//--------------------GENERAL METHODS--------------------//

	public Conversation() {
	}

}
