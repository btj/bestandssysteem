package bestandssysteem;

import java.util.List;

/**
 * @invar | getOuder() == null || getOuder().getIngangen().values().contains(this)
 */
public abstract class Knoop {

	/**
	 * @invar | ouder == null || ouder.ingangen.values().contains(this)
	 * 
	 * @peerObject
	 */
	Directory ouder;

	/**
	 * @peerObject
	 */
	public Directory getOuder() { return ouder; }
	
	Knoop() {}

	public abstract Knoop zoekOp(List<String> pad);
	
}
