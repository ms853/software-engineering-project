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
@Table(name="BoardPosts")
public class BoardPost {
	
	//--------------------ATTRIBUTES--------------------//
	
	@Id
	@GeneratedValue
	@Column(name="bPost_id")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private BoardThread boardThread;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@NotNull
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();
	
	@NotNull
	@Column(length=4000, nullable=false)
	private String message;
	

	//--------------------GETTERS--------------------//
	
	public long getId() {
		return id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public String getMessage() {
		return message;
	}
	
	public User getUser() {
		return user;
	}

	public BoardThread getBoardThread() {
		return boardThread;
	}
	

	//--------------------SETTERS--------------------//
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setCreationDate(Date date) {
		this.creationDate = date;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setBoardThread(BoardThread boardThread) {
		this.boardThread = boardThread;
	}


	//--------------------GENERAL METHODS--------------------//

	public BoardPost() {
	}
	
	public BoardPost(BoardThread boardThread, User user, String message) {
		this.boardThread = boardThread;
		this.user = user;
		this.message = message;
	}
	
}
