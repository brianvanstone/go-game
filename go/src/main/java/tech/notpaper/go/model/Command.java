package tech.notpaper.go.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tech.notpaper.go.pojo.Color;

@Entity
@Table(name = "commands", indexes= {@Index(name = "engine_index", columnList="engine_id", unique=true)})
@EntityListeners(AuditingEntityListener.class)
public class Command implements Serializable {
	
	private static final long serialVersionUID = 4827133150281081683L;

	/*
	 * DB Fields
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Engine engine;

	@Column
	private CommandType command;
	
	@Column
	private String args;
	
	@OneToOne
	private Response response;

	@ManyToOne
	private Game game;
	
	@Column
	private CommandStatus status;

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
	public Command setCommand(CommandType command) {
		this.command = command;
		return this;
	}
	
	public Command setStatus(CommandStatus status) {
		this.status = status;
		return this;
	}

	public Command setResponse(Response response) {
		this.response = response;
		return this;
	}
	
	public Command setCommandType(CommandType type) {
		this.command = type;
		return this;
	}
	
	public Command setArgs(List<String> args) {
		this.args = String.join(" ", args);
		return this;
	}
	
	public Command setArgs(String... args) {
		this.args = String.join(" ", args);
		return this;
	}
	
	public Command setGame(Game game) {
		this.game = game;
		return this;
	}
	
	public Command setEngine(Engine engine) {
		this.engine = engine;
		return this;
	}
	
	/*
	 * Getter methods
	 */
	public Long getId() {
		if (id == null) {
			return -1l;
		}
		
		return id;
	}
	
	public CommandType getCommandType() {
		return command;
	}
	
	public Map<String, String> getArgs() {
		if (args == null) {
			return null;
		}
		
		Map<String, String> argMap = new HashMap<>();
		Iterator<String> argsIter = Arrays.asList(args.split(" ")).iterator();
		
		while(argsIter.hasNext()) {
			argMap.put(argsIter.next(), argsIter.next());
		}
		
		return argMap;
	}
	
	public String getGTPCommand() {
		return id + " " + command + " " + args + "\n";
	}
	
	public Engine getEngine() {
		return engine;
	}

	public Response getResponse() {
		return response;
	}

	public CommandStatus getStatus() {
		return status;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	@JsonIgnore
	public Game getGame() {
		return game;
	}
	
	/*
	 * Supporting Types
	 */
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
	
	public enum CommandStatus {
		PENDING, COMPLETED, ABORTED;
	}
	
	/*
	 * Static Commands
	 */
	public static Command genmove(Color color) {
		return new Command()
				.setCommand(CommandType.GENMOVE)
				.setArgs("color", color.toString())
				.setStatus(CommandStatus.PENDING);
	}
}
