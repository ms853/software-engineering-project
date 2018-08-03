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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="DiscussionBoards")
public class DiscussionBoard {

	// --------------------ATTRIBUTES--------------------//

	@Id
	@GeneratedValue
	@Column(name="dBoard_id")
	private long id;
	
	@NotNull
	@Column(length=2000, nullable=false)
	private String description;
	
	@OneToOne(mappedBy="discussionBoard", fetch=FetchType.LAZY)
	private Course course;

	@OneToMany(mappedBy="discussionBoard", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<BoardThread> boardThreads = new HashSet<BoardThread>();


	// --------------------GETTERS--------------------//

	public long getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public String getDescription() {
		return description;
	}

	public Set<BoardThread> getBoardThreads() {
		return boardThreads;
	}


	// --------------------SETTERS--------------------//

	public void setId(long id) {
		this.id = id;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBoardThreads(Set<BoardThread> boardThreads) {
		this.boardThreads = boardThreads;
	}


	// --------------------GENERAL METHODS--------------------//

	public DiscussionBoard() {
	}

	public DiscussionBoard(String description) {
		this.description = description;
	}

}
