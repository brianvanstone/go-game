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

	private static final long serialVersionUID = -4167899384475985232L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//TODO implement SGF File format and store as binary
	@Column(length=1000)
	private byte[] boardState;
	
	@Column
	private Integer size;
	
	@Column
	private Integer blackCaps;
	
	@Column
	private Integer whiteCaps;
	
	public Board() {
		super();
	}
	
	public Board setSize(int size) {
		this.size = size;
		this.boardState = (new BoardState()).toBytes();
		return this;
	}
	
	public void setBoardState(BoardState state) {
		this.size = state.getSize();
		this.blackCaps = state.getBlackCaps();
		this.whiteCaps = state.getWhiteCaps();
		this.boardState = state.toBytes();
	}
	
	public Board clear() {
		setBoardState(new BoardState());
		return this;
	}
	
	public BoardState getBoardState() {
		return BoardState.fromBytes(boardState);
	}
	
	public int getSize() {
		return size;
	}
}
