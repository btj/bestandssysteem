package bestandssysteem;

import java.util.Arrays;
import java.util.List;

/**
 * @invar | getInhoud() != null
 */
public class Bestand extends Knoop {
	
	/**
	 * @invar | inhoud != null
	 * @representationObject
	 */
	private byte[] inhoud = {};
	
	/**
	 * @creates | result
	 * @post | result != null
	 */
	byte[] getInhoudInternal() { return inhoud.clone(); }

	/**
	 * @creates | result
	 */
	public byte[] getInhoud() { return getInhoudInternal(); }
	
	/**
	 * @post | getOuder() == null
	 * @post | getInhoud().length == 0
	 */
	public Bestand() {}

	/**
	 * @pre | inhoud != null
	 * @mutates_properties | getInhoud()
	 * @post | Arrays.equals(getInhoud(), inhoud)
	 */
	public void setInhoud(byte[] inhoud) {
		this.inhoud = inhoud.clone();
	}
	
	@Override
	public Knoop zoekOp(List<String> pad) {
		if (pad.isEmpty())
			return this;
		return null;
	}
	
}
