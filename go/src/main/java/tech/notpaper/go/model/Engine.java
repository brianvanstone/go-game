package tech.notpaper.go.model;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tech.notpaper.go.keys.ApiKeyGenerator;

@Entity
@Table(name = "engines", indexes= {@Index(name = "engines_apikey_index", columnList="apiKey", unique=true)})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt", "id", "owner"}, allowGetters = true)
public class Engine implements Serializable {

	private static final long serialVersionUID = 7146285825378271516L;

	/*
	 * DB Fields
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String apiKey;
	
	@ManyToOne
	private Person owner;

	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
	
	public Engine() {
		super();
		try {
			this.apiKey = ApiKeyGenerator.generateApiKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	 * Setter methods
	 */
	public Engine setOwner(Person p) {
		this.owner = p;
		return this;
	}

	public Engine setName(String name) {
		this.name = name;
		return this;
	}

	public Engine setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public Engine setApiKey(String key) {
		this.apiKey = key;
		return this;
	}
	
	/*
	 * Getter methods
	 */
	public Long getId() {
		return id;
	}

	public String getApiKey() {
		return apiKey;
	}

	public Person getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
}
