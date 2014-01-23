package wizard_spellbook;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class spellbook {
	private static  Hashtable<Integer, spell> ht;
	public spellbook(){
		ht = new Hashtable<Integer, spell>(30);
	}
	
	public spellbook(String s){
		ht = new Hashtable<Integer, spell>(30);
		//TODO constructors
	}
	
	/**
	 * Add the spell to the spell book.
	 * @param s The constructed spell to add.
	 * @return The spell added.
	 */
	public static spell addSpell(spell s){
		return ht.put(s.getLevel(), s);	
	}
	
	/**
	 * Remove a spell from the spell book.
	 * @param s The spell to remove
	 * @return The removed spell.
	 */
	public spell removeSpell(spell s){
		return ht.remove(s);
	}
	
	/**
	 * See spell documentation for info.
	 * Use the main class to get keywords and insert them to a LL to pass to this.
	 * {@link wizard_spellbook.spell}
	 */
	public spell createSpell(String s, int i, attack a, String t, String ra, String ta, 
			recharge r, action ac, boolean p, type ty, LinkedList<String> keys){
		
		spell sp = new spell(s, i, a, t, ra, ta, r, ac, p, ty, keys);
		return spellbook.addSpell(sp);
	}
	
	/**
	 * List all spells in the spell book.
	 * @return A really big string that contains all the powers.
	 */
	public String list(){
		String ret;
		String nl = "\r\n";
		ret = "";
		for(Entry<Integer, spell> e : ht.entrySet()){
			//construct the 'power card'
			spell sp = e.getValue();
			ret += sp.getName() + " " + sp.getType().toString() + " " + sp.getLevel() + nl; //Name (Attack/Utility) Level
			ret += sp.getRchg().toString() + " ** " + sp.getKw().toString() + nl; //Recharge ** Keywords
			ret += sp.getAct().toString() + " " + sp.getRange() + nl; //Action Range
			ret += sp.getTarget() + nl;//Target
			ret += sp.getAtk().toString() + nl;
			ret += sp.getText() + nl;
			ret += "Prepared: " + sp.isPrep() + nl + nl;
		}
		return ret;
	}
	
	/**
	 * List only the prepared spells in the spell book.
	 * @return
	 */
	public String list_prep(){
		
		return null;
	}
	
}
