package bestandssysteem;

import java.util.List;

/**
 * @invar | getOuder() == null || getOuder().getIngangen().values().contains(this)
 */
public abstract class Knoop {

	private Directory ouder;
	
	/**
	 * @invar | getOuderInternal() == null || getOuderInternal().getIngangenInternal().values().contains(this)
	 * 
	 * @peerObject (package-level)
	 */
	Directory getOuderInternal() { return ouder; }

	/**
	 * @peerObject
	 */
	public Directory getOuder() { return getOuderInternal(); }
	
	/**
	 * @post | getOuderInternal() == null
	 */
	Knoop() {}
	
	/**
	 * @mutates | this
	 * @post | getOuderInternal() == ouder
	 */
	void setOuder(Directory ouder) { this.ouder = ouder; }

	public abstract Knoop zoekOp(List<String> pad);
	
}
