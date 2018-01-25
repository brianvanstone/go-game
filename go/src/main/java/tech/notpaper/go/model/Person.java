package tech.notpaper.go.model;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tech.notpaper.go.keys.ApiKeyGenerator;

@Entity
@Table(name = "people")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4326263075657985252L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String apiKey;
	
	@OneToMany(mappedBy="owner")
	private Set<Engine> engines;
	
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
	
	public Person() {
		super();
		try {
			this.apiKey = ApiKeyGenerator.generateApiKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Person setApiKey(String apiKey) {
		this.apiKey = apiKey;
		return this;
	}
	
	public Person withName(String name) {
		this.name = name;
		return this;
	}
	
	public Person withBio(String bio) {
		this.bio = bio;
		return this;
	}
	
	@Deprecated
	public String getApiKey() {
		return apiKey;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBio() {
		return bio;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
}
