package tech.notpaper.go.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tech.notpaper.go.pojo.Location;

@Entity
@Table(name = "moves")
@EntityListeners(AuditingEntityListener.class)

public class Move implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3812232553393960153L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Board board;
	
	@Column
	private String location;
	
	@Column
	private String color;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public Move setId(Long id) {
		this.id = id;
		return this;
	}

	public Move setBoard(Board board) {
		this.board = board;
		return this;
	}
	
	public Move setLocation(Location location) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.location = mapper.writeValueAsString(location);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Unable to serialize provided Location for storage", e);
		}
		return this;
	}
	
	public Move setColor(String color) {
		this.color = color;
		return this;
	}

	public Long getId() {
		return id;
	}

	public Board getBoard() {
		return board;
	}
	
	public Location getLocation() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(location, Location.class);
		} catch (IOException e) {
			throw new IllegalStateException("DB state out of sync with Location definition. Illegal value fetched", e);
		}
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	@JsonIgnore
	public Date getUpdatedAt() {
		return updatedAt;
	}
}
