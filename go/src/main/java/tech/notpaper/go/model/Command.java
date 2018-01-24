package tech.notpaper.go.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
public class Command implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4827133150281081683L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private CommandType command;
	
	@Column
	private String args;
	
	@Column
	private String gtpCommand;
	
	@OneToOne
	private Response response;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
	
	public Command(CommandType command) {
		this.command = command;
	}
	
	public List<String> getArgs() {
		return Arrays.asList(args.split(" "));
	}

	public void setArgs(List<String> args) {
		this.args = String.join(" ", args);
	}

	public void setCommand(CommandType command) {
		this.command = command;
	}

	public enum CommandType {
		PROTO_V, NAME, VERSION, KNOWN_CMD, LIST_CMDS, QUIT, BOARDSIZE, CLEAR_BOARD, KOMI, PLAY, GENMOVE;
		
		@Override
		public String toString() {
			switch(this) {
			case PROTO_V:
				return "protocol_version";
			case NAME:
				return "name";
			case VERSION:
				return "version";
			case KNOWN_CMD:
				return "known_command";
			case LIST_CMDS:
				return "list_commands";
			case QUIT:
				return "quit";
			case BOARDSIZE:
				return "boardsize";
			case CLEAR_BOARD:
				return "clear_board";
			case KOMI:
				return "komi";
			case PLAY:
				return "play";
			case GENMOVE:
				return "genmove";
				default:
					return super.toString();
			}
		}
	}
	
	public long getId() {
		return id;
	}
	
	public CommandType getCommand() {
		return command;
	}
	
	public List<String> getArguments() {
		return Arrays.asList(args);
	}
	
	public String getGTPCommand() {
		return gtpCommand;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public String getGtpCommand() {
		return gtpCommand;
	}

	public Response getResponse() {
		return response;
	}
	
	public Command withCommandType(CommandType type) {
		this.command = type;
		return this;
	}
	
	public Command withArgs(List<String> args) {
		this.args = String.join(" ", args);
		return this;
	}
	
	public Command withArgs(String... args) {
		this.args = String.join(" ", args);
		return this;
	}
}
