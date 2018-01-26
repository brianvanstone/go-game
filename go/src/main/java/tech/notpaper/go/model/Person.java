package tech.notpaper.go.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "people")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4326263075657985252L;

	/*
	 * DB Fields
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="owner")
	private Set<Engine> engines = new LinkedHashSet<>();
	
	@Column
	private String name;
	
	@Column
	private String bio;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
	
	/*
	 * Setter methods
	 */
	
	public Person setName(String name) {
		this.name = name;
		return this;
	}
	
	public Person setBio(String bio) {
		this.bio = bio;
		return this;
	}
	
	public Person addEngine(Engine engine) {
		this.engines.add(engine);
		engine.setOwner(this);
		return this;
	}

	/*
	 * Getter methods
	 */
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBio() {
		return bio;
	}
	
	@JsonIgnore
	public Set<Engine> getEngines() {
		return engines;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
}
