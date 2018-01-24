package tech.notpaper.go.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import tech.notpaper.go.pojo.BoardState;

@Entity
@Table(name = "boards")
@EntityListeners(AuditingEntityListener.class)
public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4167899384475985232L;
	
	public Board() {
		super();
		this.blackCaps = 0;
		this.whiteCaps = 0;
		this.ko = "";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length=1000)
	private byte[] boardState;
	
	@Column
	private int size;
	
	@Column
	private int blackCaps;
	
	@Column
	private int whiteCaps;
	
	@Column
	private String ko;
	
	public Board ofSize(int size) {
		this.size = size;
		this.boardState = (new BoardState()).toBytes();
		return this;
	}
	
	public Board placeStone() {
		return this;
	}
	
	public void setBoardState(BoardState state) {
		this.boardState = state.toBytes();
	}
	
	public BoardState getBoardState() {
		return BoardState.fromBytes(boardState);
	}
}
