package tech.notpaper.go.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "games")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8700063968062543317L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private GameStatus status;
	
	@Column(length=10000)
	private Engine playerOne;
	
	@Column(length=10000)
	private Engine playerTwo;
	
	@Lob
	@Column(length=1000)
	private Board board;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
	
	public Game() {
		super();
		this.status = GameStatus.ACTIVE;
	}
	
	public Game withBoard(Board board) {
		this.board = board;
		return this;
	}
	
	public Game betweenPlayers(Engine p1, Engine p2) {
		this.playerOne = p1;
		this.playerTwo = p2;
		return this;
	}

	public Long getId() {
		return id;
	}
	
	public GameStatus getGameStatus() {
		return status;
	}

	public Engine getPlayerOne() {
		return playerOne;
	}

	public Engine getPlayerTwo() {
		return playerTwo;
	}

	public Board getBoard() {
		return board;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public enum GameStatus {
		ACTIVE, ABORTED, COMPLETE;
	}
	
	public void setStatus(GameStatus status) {
		this.status = status;
	}
}
