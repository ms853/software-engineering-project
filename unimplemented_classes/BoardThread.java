package ecourse.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="BoardThreads")
public class BoardThread {

	//--------------------ATTRIBUTES--------------------//
	
	@Id
	@GeneratedValue
	@Column(name="bThread_id")
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	private DiscussionBoard discussionBoard;

	@NotNull
	@Column(length=100, nullable=false)
	private String title;
	
	@OneToMany(mappedBy="boardThread", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<BoardPost> boardPosts = new ArrayList<BoardPost>();

	
	//--------------------GETTERS--------------------//
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public List<BoardPost> getBoardPosts() {
		return boardPosts;
	}
	
	public DiscussionBoard getDiscussionBoard() {
		return discussionBoard;
	}

	
	//--------------------SETTERS--------------------//
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setBoardPosts(List<BoardPost> boardPosts) {
		this.boardPosts = boardPosts;
	}
	
	public void setDiscussionBoard(DiscussionBoard discussionBoard) {
		this.discussionBoard = discussionBoard;
	}
	

	//--------------------GENERAL METHODS--------------------//

	public BoardThread() {
	}
	
	public BoardThread(DiscussionBoard discussionBoard, String title) {
		this.discussionBoard = discussionBoard;
		this.title = title;
	}

}
