package tech.notpaper.go.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Column
	@Convert
	private BoardState boardState;
	
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
		StringBuilder emptyBoard = new StringBuilder();
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				emptyBoard.append("-");
			}
		}
		this.boardState = new BoardState();
		return this;
	}
	
	public Board placeStone() {
		return this;
	}
}
