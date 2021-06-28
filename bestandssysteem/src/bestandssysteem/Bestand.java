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
	byte[] inhoud = {};

	/**
	 * @creates | result
	 */
	public byte[] getInhoud() { return inhoud.clone(); }
	
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
