package tech.notpaper.go.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "commands")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7441542482129709675L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private ResponseStatus success;
	
	@OneToOne
	private Command command;
	
	@Column
	private String response;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public Long getId() {
		return id;
	}

	public ResponseStatus getStatus() {
		return success;
	}

	public Command getCommand() {
		return command;
	}

	public String getResponse() {
		return response;
	}

	public String getGTPCommand() {
		if (command == null) {
			return null;
		}
		return (success == ResponseStatus.SUCCESS ? "=" : "?") + id + " " + response + "\n\n";
	}
	
	public enum ResponseStatus {
		SUCCESS, FAILURE;
	}
}
