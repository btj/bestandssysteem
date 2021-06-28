package bestandssysteem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logicalcollections.LogicalMap;

/**
 * @invar | getIngangen() != null
 * @invar | getIngangen().keySet().stream().allMatch(naam -> naam != null)
 * @invar | getIngangen().values().stream().allMatch(kind -> kind != null && kind.getOuder() == this)
 * @invar | getIngangen().values().stream().distinct().count() == getIngangen().size()
 */
public class Directory extends Knoop {

	/**
	 * @invar | ingangen != null
	 * @invar | ingangen.keySet().stream().allMatch(naam -> naam != null)
	 * @invar | ingangen.values().stream().allMatch(kind -> kind != null && kind.ouder == this)
	 * @invar | ingangen.values().stream().distinct().count() == ingangen.size()
	 * @representationObject
	 * @peerObjects | ingangen.values()
	 */
	Map<String, Knoop> ingangen = new HashMap<>();
	
	/**
	 * @creates | result
	 * @peerObjects | result.values()
	 */
	public Map<String, Knoop> getIngangen() { return Map.copyOf(ingangen); }
	
	/**
	 * @post | getOuder() == null
	 * @post | getIngangen().isEmpty()
	 */
	public Directory() {}

	/**
	 * @pre | naam != null
	 * @pre | kindknoop != null
	 * @pre | !getIngangen().containsKey(naam)
	 * @pre | kindknoop.getOuder() == null
	 * @mutates_properties | getIngangen(), kindknoop.getOuder()
	 * @post | getIngangen().equals(LogicalMap.plus(old(getIngangen()), naam, kindknoop))
	 * @post | kindknoop.getOuder() == this
	 */
	public void addIngang(String naam, Knoop kindknoop) {
		ingangen.put(naam, kindknoop);
		kindknoop.ouder = this;
	}
	
	/**
	 * @pre | getIngangen().containsKey(naam)
	 * @mutates_properties | getIngangen(), getIngangen().get(naam).getOuder()
	 * @post | getIngangen().equals(LogicalMap.minus(old(getIngangen()), naam))
	 * @post | old(getIngangen().get(naam)).getOuder() == null
	 */
	public void removeIngang(String naam) {
		ingangen.get(naam).ouder = null;
		ingangen.remove(naam);
	}

	@Override
	public Knoop zoekOp(List<String> pad) {
		if (pad.isEmpty())
			return this;
		Knoop kind = ingangen.get(pad.get(0));
		if (kind == null)
			return null;
		return kind.zoekOp(pad.subList(1, pad.size()));
	}
	
}
