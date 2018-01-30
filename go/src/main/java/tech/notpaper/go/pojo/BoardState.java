package tech.notpaper.go.pojo;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class BoardState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3019004117040827648L;
	
	private short blackCaps;
	private short whiteCaps;
	private short size;
	private List<List<State>> state;
	
	private enum State {
		WHITE, BLACK, EMPTY;
		
		private static byte white = Byte.parseByte("00");
		private static byte black = Byte.parseByte("01");
		private static byte empty = Byte.parseByte("11");
		
		public byte toByte() {
			switch(this) {
			case WHITE:
				return white;
			case BLACK:
				return black;
			default:
				return empty;
			}
		}

		public static State fromByte(byte b) {
			switch(b) {
			case 0b0:
				return State.WHITE;
			case 0b01:
				return State.BLACK;
			default:
				return State.EMPTY;
			}
		}
	}
	
	public BoardState() {
		blackCaps = 0;
		whiteCaps = 0;
		size = 19;
		state = empty(size);
	}
	
	private List<List<State>> empty(int size) {
		List<List<State>> l = new LinkedList<>();
		for(int i = 0; i < size; i++) {
			List<State> l2 = new LinkedList<>();
			for(int j = 0; j < size; j++) {
				l2.add(State.EMPTY);
			}
			l.add(l2);
		}
		return l;
	}
	
	public BoardState setSize(int size) {
		this.size = (short) size;
		state = empty(size);
		return this;
	}

	public int getBlackCaps() {
		return blackCaps;
	}

	public int getWhiteCaps() {
		return whiteCaps;
	}

	public int getSize() {
		return size;
	}
	
	public List<List<State>> getState() {
		return state;
	}

	public byte[] getRawState() {
		return toBytes();
	}

	public void setBlackCaps(short blackCaps) {
		this.blackCaps = blackCaps;
	}

	public void setWhiteCaps(short whiteCaps) {
		this.whiteCaps = whiteCaps;
	}
	
	public void placeStone() {
		
	}
	
	public byte[] toBytes() {
		ByteBuffer bb = ByteBuffer.allocate(1000);
		bb.putShort(size);
		bb.putShort(blackCaps);
		bb.putShort(whiteCaps);
		for(List<State> sublist : state) {
			for(State s : sublist) {
				bb.put(s.toByte());
			}
		}
		
		return bb.array();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BoardState)) {
			return false;
		}
		
		byte[] otherRaw = ((BoardState) obj).getRawState();
		byte[] thisRaw = getRawState();
		
		if (otherRaw.length != thisRaw.length) {
			return false;
		}
		
		for(int i = 0; i < otherRaw.length; i++) {
			if (otherRaw[i] != thisRaw[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	public static BoardState fromBytes(byte[] bytes) {
		BoardState bs = new BoardState();
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		bs.setSize(bb.getShort());
		bs.setBlackCaps(bb.getShort());
		bs.setWhiteCaps(bb.getShort());
		List<List<State>> state = new LinkedList<>();
		for(int i = 0; i < bs.getSize(); i++) {
			List<State> sublist = new LinkedList<>();
			for(int j = 0; j < bs.getSize(); j++) {
				sublist.add(State.fromByte(bb.get()));
			}
			state.add(sublist);
		}
		bs.state = state;
		
		return bs;
	}
}
